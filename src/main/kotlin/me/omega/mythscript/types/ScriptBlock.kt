package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.instance.block.Block

class ScriptBlock(val block: Block) {

    @get:ScriptableExport
    val id get() = block.id()

}