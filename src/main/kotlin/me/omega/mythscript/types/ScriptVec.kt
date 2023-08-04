package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.coordinate.Vec

class ScriptVec(
    x: Double,
    y: Double,
    z: Double
) : ScriptPoint(x, y, z) {

    companion object {

        @ScriptableExport
        fun new(x: Double, y: Double, z: Double) =
            ScriptVec(x, y, z)


        fun fromVec(vec: Vec): ScriptVec =
            ScriptVec(
                vec.x(),
                vec.y(),
                vec.z()
            )
    }

    override fun toString() = "ScriptPos(x=$x, y=$y, z=$z)"

}