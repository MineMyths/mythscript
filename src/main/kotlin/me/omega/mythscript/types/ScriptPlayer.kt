package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.entity.Player

/**
 * Wrapper class for a player in a script object
 */
class ScriptPlayer(val player: Player) : ScriptEntity(player), ScriptAudience {

    override val audience = player

    @get:ScriptableExport
    val latency = player.latency

    @get:ScriptableExport
    @set:ScriptableExport
    var flying: Boolean
        get() = player.isFlying
        set(value) {
            player.isFlying = value
        }

    @get:ScriptableExport
    @set:ScriptableExport
    var respawnPoint: ScriptPos
        get() = ScriptPos.fromPosition(player.respawnPoint)
        set(value) {
            player.respawnPoint = value.toPosition()
        }


    @get:ScriptableExport
    val username: String = player.username

    @ScriptableExport
    fun playSound(sound: ScriptSound) {
        player.playSound(sound.sound)
    }

    @ScriptableExport
    fun playSound(sound: ScriptSound, position: ScriptPos) {
        player.playSound(sound.sound, position.toPosition())
    }

    @ScriptableExport
    fun stopSound(sound: ScriptSound) {
        player.stopSound(sound.sound)
    }

    @ScriptableExport
    override fun toString() = "ScriptPlayer(uuid=${entity.uuid})"

}