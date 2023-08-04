package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.kyori.adventure.audience.Audience
import world.cepi.kstom.adventure.asComponent

interface ScriptAudience {

    val audience: Audience

    /** Sends a message with the mini format to this audience */
    @ScriptableExport
    fun sendMessage(message: String) = audience.sendMessage(message.asComponent())

    @ScriptableExport
    fun sendRandomMessage(messages: List<String>) = sendMessage(messages.random())

    @ScriptableExport
    fun sendMessage(int: Int) = sendMessage(int.toString())

    @ScriptableExport
    fun sendMessage(double: Double) = sendMessage(double.toString())

    @ScriptableExport
    fun sendMessage(message: ScriptComponent) = audience.sendMessage(message.component)

    @ScriptableExport
    fun sendActionBar(message: String) = audience.sendActionBar(message.asComponent())

    @ScriptableExport
    fun sendActionBar(message: ScriptComponent) = audience.sendActionBar(message.component)

}