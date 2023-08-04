package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport

object ScriptChance {

    @ScriptableExport
    fun chance(percent: Double): Boolean = Math.random() < percent

    @ScriptableExport
    fun random() = Math.random()

}