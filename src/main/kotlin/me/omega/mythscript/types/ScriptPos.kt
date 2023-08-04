package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.coordinate.Pos

class ScriptPos(
    x: Double,
    y: Double,
    z: Double,
    @get:ScriptableExport
    val yaw: Float,
    @get:ScriptableExport
    val pitch: Float
) : ScriptPoint(x, y, z) {

    fun toPosition() = Pos(x, y, z, yaw, pitch)

    companion object {

        @JvmOverloads
        @ScriptableExport
        fun new(x: Double, y: Double, z: Double, yaw: Float = 0f, pitch: Float = 0f) =
            ScriptPos(x, y, z, yaw, pitch)


        @ScriptableExport
        fun fromPosition(position: Pos): ScriptPos =
            ScriptPos(
                position.x(),
                position.y(),
                position.z(),
                position.yaw(),
                position.pitch()
            )
    }

    @ScriptableExport
    override fun toString() = "ScriptPos(x=$x, y=$y, z=$z, pitch=$pitch, yaw=$yaw)"

}