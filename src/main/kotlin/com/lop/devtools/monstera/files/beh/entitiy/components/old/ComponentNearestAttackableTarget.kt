package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentNearestAttackableTarget {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var mustSee: Boolean? = null
    var reselectTargets: Boolean? = null
    var withinRadius: Float? = null
    var mustSeeForgetDuration: Float? = null
    var mustReach: Boolean? = null
    var persistTime: Float? = null
    var scanInterval: Int? = null
    var setPersistent: Boolean? = null
    var targetSearchHeight: Float? = null
    var attackInterval: Number? = null
    var attackIntervalMin: Number? = null

    /**
     * 0..1
     *
     * the entity types to attack
     */
    fun entityTypes(entityTypes: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(entityTypes).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        mustSee?.let { general["must_see"] = it }
        reselectTargets?.let { general["reselect_targets"] = it }
        withinRadius?.let { general["within_radius"] = it }
        mustSeeForgetDuration?.let { general["must_see_forget_duration"] = it }
        mustReach?.let { general["must_reach"] = it }
        persistTime?.let { general["persist_time"] = it }
        scanInterval?.let { general["scan_interval"] = it }
        setPersistent?.let { general["set_persistent"] = it }
        targetSearchHeight?.let { general["target_search_height"] = it }
        attackInterval?.let { general["attack_interval"] = it }
        attackIntervalMin?.let { general["attack_interval_min"] = it }
        return general
    }
}