package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.coordinate.Vec
import kotlin.math.pow
import kotlin.math.sqrt

abstract class ScriptPoint(
    @get:ScriptableExport
    val x: Double = 0.0,
    @get:ScriptableExport
    val y: Double = 0.0,
    @get:ScriptableExport
    val z: Double = 0.0
) {

    fun toVec() = Vec(x, y, z)

    @ScriptableExport
    fun distanceSquared(x: Double, y: Double, z: Double): Double {
        return (this.x - x).pow(2) + (this.y - y).pow(2) + (this.z - z).pow(2)
    }

    @ScriptableExport
    fun distanceSquared(point: ScriptPoint): Double {
        return distanceSquared(point.x, point.y, point.z)
    }

    @ScriptableExport
    fun distance(x: Double, y: Double, z: Double): Double {
        return sqrt(distanceSquared(x, y, z))
    }

    @ScriptableExport
    fun distance(point: ScriptPoint) = distance(point.x, point.y, point.z)
}