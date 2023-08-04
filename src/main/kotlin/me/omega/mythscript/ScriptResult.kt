package me.omega.mythscript

import java.time.Instant
import java.util.concurrent.atomic.AtomicInteger

class ScriptResult(val created: Instant, val steps: AtomicInteger, val thread: Thread) {

    fun interrupt() = thread.interrupt()

    override fun toString(): String {
        return "ScriptResult(created=$created, steps=$steps, thread=$thread)"
    }

}