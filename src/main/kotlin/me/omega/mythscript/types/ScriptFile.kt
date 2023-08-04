package me.omega.mythscript.types

import me.omega.mythscript.Script
import me.omega.mythscript.access.ScriptableExport
import java.io.File

/**
 * A script file that can be called from anywhere, including different scripts.
 */
data class ScriptFile(
    @get:ScriptableExport
    val content: String
) {
    @ScriptableExport
    fun runAsConsole() = Script(content).runAsConsole()
    @ScriptableExport
    fun runAsPlayer(player: ScriptPlayer) = Script(content).runAsPlayer(player.player)
    @ScriptableExport
    fun runAsEntity(entity: ScriptEntity) = Script(content).runAsEntity(entity.entity)

    companion object {

        @ScriptableExport
        fun new(content: String) = ScriptFile(content)

        @ScriptableExport
        fun new(path: ScriptPath): ScriptFile = new(File(path.path).readText())

    }

}