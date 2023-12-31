package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.entity.Entity
import net.minestom.server.potion.PotionEffect

open class ScriptEntity(val entity: Entity) : ScriptTickable(entity) {

    @ScriptableExport
    fun teleport(position: ScriptPos) {
        entity.teleport(position.toPosition())
    }

    @get:ScriptableExport
    val isActive get() = entity.isActive

    @get:ScriptableExport
    @set:ScriptableExport
    var position
        get() = ScriptPos.fromPosition(entity.position)
        set(value) {
            entity.teleport(value.toPosition())
        }

    @ScriptableExport
    fun hasVelocity() = entity.hasVelocity()

    @ScriptableExport
    fun hasNoGravity() = entity.hasNoGravity()

    @get:ScriptableExport
    @set:ScriptableExport
    var velocity
        get() = ScriptVec.fromVec(entity.velocity)
        set(value) {
            entity.velocity = value.toVec()
        }

    @get:ScriptableExport
    @set:ScriptableExport
    var silent
        get() = entity.isSilent
        set(value) {
            entity.isSilent = true
        }

    @get:ScriptableExport
    @set:ScriptableExport
    var sneaking
        get() = entity.isSneaking
        set(value) {
            entity.isSneaking = value
        }

    @get:ScriptableExport
    val onGround get() = entity.isOnGround

    @get:ScriptableExport
    var onFire: Boolean
        get() = entity.isOnFire
        set(value) {
            entity.isOnFire = value
        }


    @get:ScriptableExport
    @set:ScriptableExport
    var glowing
        get() = entity.isGlowing
        set(value) {
            entity.isGlowing = value
        }

    @ScriptableExport
    fun remove() = entity.remove()

    @get:ScriptableExport
    @set:ScriptableExport
    var instance: ScriptInstance?
        get() = entity.instance?.let { ScriptInstance(it) }
        set(value) {
            entity.setInstance(value!!.instance)
        }

    @get:ScriptableExport
    val isOnGround get() = entity.isOnGround

    @get:ScriptableExport
    val passengers: Set<ScriptEntity>
        get() = entity.passengers.map { ScriptEntity(it) }.toSet()

    @ScriptableExport
    fun addPassenger(passenger: ScriptEntity) {
        entity.addPassenger(passenger.entity)
    }

    @ScriptableExport
    fun removePassenger(passenger: ScriptEntity) {
        entity.removePassenger(passenger.entity)
    }

    @get:ScriptableExport
    val vehicle: ScriptEntity?
        get() = entity.vehicle?.let { ScriptEntity(it) }

    @JvmOverloads
    @ScriptableExport
    fun nearestEntities(distanceCap: Double = Double.MAX_VALUE): Set<ScriptEntity>? =
        entity.instance?.entities
            ?.asSequence()
            ?.filter { it != entity }
            ?.filter { it.getDistance(entity) <= distanceCap }
            ?.sortedBy { it.getDistance(entity) }?.map { ScriptEntity(it) }
            ?.toSet()

    @JvmOverloads
    @ScriptableExport
    fun nearestEntity(distanceCap: Double = Double.MAX_VALUE): ScriptEntity? =
        nearestEntities(distanceCap)?.first()

    @ScriptableExport
    fun applyPotionEffect(potion: ScriptPotion) {
        entity.addEffect(potion.potion)
    }

    @ScriptableExport
    fun removePotionEffect(effect: PotionEffect) {
        entity.removeEffect(effect)
    }

    override fun toString() = "ScriptEntity(path=${entity.entityType.namespace().path}, uuid=${entity.uuid})"

}