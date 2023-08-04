package me.omega.mythscript.access

import org.graalvm.polyglot.HostAccess

object ScriptableExplicitConfig {

    val explicitMode: HostAccess = HostAccess.newBuilder()
        .allowAccessAnnotatedBy(ScriptableExport::class.java)
        .allowImplementationsAnnotatedBy(ScriptableImplementation::class.java)
        .allowImplementationsAnnotatedBy(FunctionalInterface::class.java)
        .methodScoping(true)
        .build()

}