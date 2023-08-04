package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.Tickable

abstract class ScriptTickable(val tickable: Tickable) {

    @ScriptableExport
    fun tick(time: Long) = tickable.tick(time)

}