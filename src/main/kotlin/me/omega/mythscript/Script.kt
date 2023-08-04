package me.omega.mythscript

import kotlinx.serialization.Serializable
import me.omega.mythcommons.messenger.MessageType
import me.omega.mythcommons.messenger.Messenger
import me.omega.mythscript.access.ScriptableExplicitConfig
import me.omega.mythscript.types.*
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.PolyglotException
import org.slf4j.Logger
import world.cepi.kstom.serializer.SerializableUUID
import java.time.Instant
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

@Serializable
class Script(val content: String = "", val uuid: SerializableUUID = UUID.randomUUID()) {

    val result get() = ScriptManager.get(uuid)

    val running get() = ScriptManager.isRunning(uuid)

    companion object {
        val objects = mutableMapOf<String, Any>()

        lateinit var logger: Logger

        init {
            reset()
        }

        fun reset() {
            objects.clear()
            objects["Chance"] = ScriptChance
            objects["Audiences"] = ScriptAudiences
            objects["Timer"] = ScriptTimer
            objects["Pos"] = ScriptPos
            objects["Vec"] = ScriptVec
            objects["File"] = ScriptFile
            objects["Path"] = ScriptPath
        }
    }

    fun graalContext() = Context.newBuilder("js")
        .allowExperimentalOptions(true)
        .allowHostAccess(ScriptableExplicitConfig.explicitMode)
        .build()

    /**
     * Runs a set of objects with some context.
     *
     * @return If this succeeded or not.
     */
    fun run(scriptContext: ScriptContext, localObjects: Map<String, Any> = emptyMap(), debug: Boolean = false) {

        val oldCl = Thread.currentThread().contextClassLoader
        Thread.currentThread().contextClassLoader = javaClass.classLoader
        val time = System.currentTimeMillis();

        val atomicInteger = AtomicInteger()

        val currentThread = thread {
            graalContext().use { context ->
                context.getBindings("js").apply {
                    putMember("player", scriptContext.player)
                    putMember("entity", scriptContext.entity)
                    putMember("position", scriptContext.position)
                    putMember("Executor", ScriptExecutor(scriptContext)) // Executor.execute
                    objects.forEach { (key, value) -> putMember(key, value) }
                    localObjects.forEach { (key, value) -> putMember(key, value) }
                }

                try {
                    val returnValue = context.eval("js", content)
                    if (debug) {
                        if (scriptContext.player != null) {
                            Messenger.send(
                                scriptContext.player.player,
                                MessageType.SERVER,
                                "Script executed in ${System.currentTimeMillis() - time}ms [steps -> ${atomicInteger.get()}]"
                            )
                            Messenger.send(
                                scriptContext.player.player,
                                "<light_gray>Script returned: <white>${returnValue.asString()}</white></light_gray>"
                            )
                        }
                        logger.info("Script executed in ${System.currentTimeMillis() - time}ms [steps -> ${atomicInteger.get()}]")
                        logger.info("Script returned: ${returnValue.asString()}")
                    }
                } catch (e: PolyglotException) {
                    if (debug) {
                        if (scriptContext.player != null) {
                            Messenger.send(
                                scriptContext.player.player,
                                MessageType.SERVER,
                                "Script error: ${e.message} [steps -> ${atomicInteger.get()}]"
                            )
                        }
                        logger.error("Script error: ${e.message} [steps -> ${atomicInteger.get()}]")
                    }
                }

                ScriptManager.runningScripts.remove(uuid)
            }
        }

        val result = ScriptResult(Instant.now(), atomicInteger, currentThread)

        Thread.currentThread().contextClassLoader = oldCl

        ScriptManager.runningScripts[uuid] = result
    }

    fun runAsPlayer(player: Player, localObjects: Map<String, Any> = emptyMap(), debug: Boolean = false) = run(ScriptContext(
        ScriptPlayer(player),
        ScriptEntity(player),
        ScriptPos.fromPosition(player.position),
        player.instance?.let { ScriptInstance(it) }
    ), localObjects, debug)

    fun runAsEntity(entity: Entity, localObjects: Map<String, Any> = emptyMap(), debug: Boolean = false) = run(ScriptContext(
        (entity as? Player)?.let { ScriptPlayer(it) },
        ScriptEntity(entity),
        ScriptPos.fromPosition(entity.position),
        entity.instance?.let { ScriptInstance(it) }
    ), localObjects, debug)

    fun runAsConsole(localObjects: Map<String, Any> = emptyMap(), debug: Boolean = false) = run(ScriptContext(
        null,
        null,
        null,
        null
    ), localObjects, debug)

}