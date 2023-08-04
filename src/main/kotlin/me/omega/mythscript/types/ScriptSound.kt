package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.kyori.adventure.sound.Sound
import net.minestom.server.potion.Potion
import net.minestom.server.potion.PotionEffect
import net.minestom.server.sound.SoundEvent

data class ScriptSound(
    @get:ScriptableExport
    val sound: Sound,
) {

    companion object {

        @JvmOverloads
        @ScriptableExport
        fun new(sound: SoundEvent, volume: Float = 1f, pitch: Float = 1f): ScriptSound {
            return ScriptSound(Sound.sound().type(sound).volume(volume).pitch(pitch).build())
        }

    }

}