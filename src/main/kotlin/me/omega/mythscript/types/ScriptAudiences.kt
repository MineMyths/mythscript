package me.omega.mythscript.types

import me.omega.mythscript.access.ScriptableExport
import net.minestom.server.adventure.audience.Audiences

object ScriptAudiences {

    @ScriptableExport
    fun all() = object : ScriptAudience {
        override val audience = Audiences.all()
    }

    @ScriptableExport
    fun players() = object : ScriptAudience {
        override val audience = Audiences.players()
    }

    @ScriptableExport
    fun near(instance: ScriptInstance, position: ScriptPoint, distance: Double) = object : ScriptAudience {
        override val audience =  Audiences.players { it.instance == instance.instance && it.position.distance(position.toVec()) <= distance }
    }

    @ScriptableExport
    fun near(entity: ScriptEntity, distance: Double) = object : ScriptAudience {
        override val audience =  Audiences.players { it.instance == entity.instance?.instance && it.position.distance(entity.position.toVec()) <= distance }
    }

    @ScriptableExport
    fun console() = object : ScriptAudience {
        override val audience = Audiences.console()
    }
}