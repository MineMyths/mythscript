package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.potion.Potion
import net.minestom.server.potion.PotionEffect

data class ScriptPotion(
    @get:ScriptableExport
    val potion: Potion
) {

    companion object {

        @JvmOverloads
        @ScriptableExport
        fun new(effect: PotionEffect, amplifier: Byte, duration: Int, flags: Byte = 0): ScriptPotion {
            return ScriptPotion(Potion(effect, amplifier, duration, flags))
        }

    }

}