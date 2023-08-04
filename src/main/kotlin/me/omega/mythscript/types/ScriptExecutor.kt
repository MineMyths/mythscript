package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import world.cepi.kstom.Manager

class ScriptExecutor(val context: ScriptContext) {

    @ScriptableExport
    fun execute(command: String) = context.player?.player?.let { Manager.command.execute(it, command) }

}