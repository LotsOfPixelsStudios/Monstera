package com.lop.devtools.monstera.files.beh.entitiy.events

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehEntityEvents: MonsteraFile {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    override val unsafe = Unsafe(this)

    inner class Unsafe(parent: BehEntityEvents): MonsteraUnsafeMap {
        /**
         * access to all defined data
         */
        val general = mutableMapOf<String, Any>()

        val debugAddedGroups = arrayListOf<String>()

        val defaultBornEvent = BehEntityEvent(parent)
        val defaultSpawnedEvent = BehEntityEvent(parent)
        val defaultTransformedEvent = BehEntityEvent(parent)
        val defaultOnPrimeEvent = BehEntityEvent(parent)

        override fun getData(): MutableMap<String, Any> {
            if (unsafe.defaultBornEvent.unsafe.getData().isNotEmpty())
                unsafe.general["minecraft:entity_born"] = unsafe.defaultBornEvent.unsafe.getData()
            if (unsafe.defaultSpawnedEvent.unsafe.getData().isNotEmpty())
                unsafe.general["minecraft:entity_spawned"] = unsafe.defaultSpawnedEvent.unsafe.getData()
            if (unsafe.defaultTransformedEvent.unsafe.getData().isNotEmpty())
                unsafe.general["minecraft:entity_transformed"] = unsafe.defaultTransformedEvent.unsafe.getData()
            if (unsafe.defaultOnPrimeEvent.unsafe.getData().isNotEmpty())
                unsafe.general["minecraft:on_prime"] = unsafe.defaultOnPrimeEvent.unsafe.getData()

            return unsafe.general
        }
    }

    /**
     * access the unsafe context, alternatively use "unsafe."
     */
    fun unsafe(data: Unsafe.() -> Unit) {
        unsafe.apply(data)
    }

    /**
     * 1..*
     *
     * more than one event possible
     * [event][BehEntityEvent]
     * @sample sampleEvent
     */
    fun event(name: String, settings: BehEntityEvent.() -> Unit) {
        val event = BehEntityEvent(this).apply { settings(this) }
        unsafe.general[name] = event.unsafe.getData()
    }

    private fun sampleEvent() {
        event("test") {
            add { }
            remove { }
            sequence { }
            randomize { }
            setProperty("myProp", true, Addon.active!!)
        }
    }

    /**
     * 0..1
     *
     * Event called on an entity that is spawned through two entities breeding.
     * @sample sampleEvent
     */
    fun defaultBornEvent(settings: BehEntityEvent.() -> Unit) {
        unsafe.defaultBornEvent.apply(settings)
    }

    /**
     * 0..1
     *
     * Event called on an entity that is placed in the level.
     * @sample sampleEvent
     */
    fun defaultSpawnedEvent(settings: BehEntityEvent.() -> Unit) {
        unsafe.defaultSpawnedEvent.apply(settings)
    }

    /**
     * 0..1
     *
     * Event called on an entity that transforms into another entity.
     * @sample sampleEvent
     */
    fun defaultTransformedEvent(settings: BehEntityEvent.() -> Unit) {
        unsafe.defaultTransformedEvent.apply(settings)
    }

    /**
     * 0..1
     *
     * 	Event called on an entity whose fuse is lit and is ready to explode.
     * 	@sample sampleEvent
     */
    fun defaultOnPrimeEvent(settings: BehEntityEvent.() -> Unit) {
        unsafe.defaultOnPrimeEvent.apply(settings)
    }
}