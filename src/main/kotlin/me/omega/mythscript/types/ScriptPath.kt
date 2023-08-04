package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import java.net.URI

/**
 * A script file that can be called from anywhere, including different scripts.
 */
data class ScriptPath(
    @get:ScriptableExport
    val path: URI
) {
    companion object {
        @ScriptableExport
        fun new(path: String) = ScriptPath(URI(path))

    }

}