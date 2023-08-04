package me.omega.mythscript

import java.util.*

object ScriptManager {

    val runningScripts = mutableMapOf<UUID, ScriptResult>()

    fun interrupt(uuid: UUID) {
        runningScripts[uuid]?.interrupt()
        runningScripts.remove(uuid)
    }

    fun get(uuid: UUID) = runningScripts[uuid]

    fun get(script: Script) = get(script.uuid)

    fun isRunning(uuid: UUID) = runningScripts.containsKey(uuid)

    fun isRunning(script: Script) = isRunning(script.uuid)

}