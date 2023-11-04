package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentSpawnEntities {
    val general = arrayListOf<Any>()

    /**
     * 1..*
     */
    fun spawnEntity(
        value: CompSpawnEntity.() -> Unit
    ) {
        general.add(CompSpawnEntity().apply(value).getData())
    }

    fun getData(): MutableMap<String, Any> {
        return mutableMapOf(
            "entities" to general
        )
    }
}

class CompSpawnEntity {
    val general = mutableMapOf<String, Any>()

    var minWaitTime: Number? = null
    var maxWaitTime: Number? = null
    var spawnEvent: String? = null
    var spawnEntity: String? = null
    var singleUse: Boolean? = null
    var numToSpawn: Int? = null
    var shouldLeash: Boolean? = null
    var spawnItem: String? = null
    var spawnSound: String? = null
    var spawnMethod: String? = null

    fun filters(value: BehEntityFilter.() -> Unit) {
        general["filters"] = BehEntityFilter().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        minWaitTime?.let { general["min_wait_time"] = it }
        maxWaitTime?.let { general["max_wait_time"] = it }
        spawnEvent?.let { general["spawn_event"] = it }
        spawnEntity?.let { general["spawn_entity"] = it }
        singleUse?.let { general["single_use"] = it }
        numToSpawn?.let { general["num_to_spawn"] = it }
        shouldLeash?.let { general["should_leash"] = it }
        spawnItem?.let { general["spawn_item"] = it }
        spawnSound?.let { general["spawn_sound"] = it }
        spawnMethod?.let { general["spawn_method"] = it }
        return general
    }
}