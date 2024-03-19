package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentSneeze {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var probability: Float? = null
    var cooldownTime: Float? = null
    var withinRadius: Float? = null
    var dropItemChance: Float? = null
    var lootTable: String? = null
    var prepareSound: String? = null
    var prepareTime: Float? = null
    var sound: String? = null

    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        probability?.let { general["probability"] = it }
        cooldownTime?.let { general["cooldown_time"] = it }
        withinRadius?.let { general["within_radius"] = it }
        dropItemChance?.let { general["drop_item_chance"] = it }
        lootTable?.let { general["loot_table"] = it }
        prepareSound?.let { general["prepare_sound"] = it }
        prepareTime?.let { general["prepare_time"] = it }
        sound?.let { general["sound"] = it }
        return general
    }
}