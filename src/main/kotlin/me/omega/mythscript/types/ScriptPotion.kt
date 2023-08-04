package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.potion.Potion
import net.minestom.server.potion.PotionEffect

data class ScriptPotion(
    @get:ScriptableExport
    val potion: Potion
) {

    companion object {

        @ScriptableExport
        fun new(effect: PotionEffect, amplifier: Byte, duration: Int): ScriptPotion {
            return new(effect, amplifier, duration, 0)
        }

        @ScriptableExport
        fun new(effect: PotionEffect, amplifier: Byte, duration: Int, flags: Byte): ScriptPotion {
            return ScriptPotion(Potion(effect, amplifier, duration, flags))
        }

    }

}