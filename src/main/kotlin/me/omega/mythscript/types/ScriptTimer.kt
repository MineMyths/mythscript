package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport

object ScriptTimer {

    @ScriptableExport
    fun sleep(time: Long) = Thread.sleep(time)

}