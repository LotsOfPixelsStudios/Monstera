package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentNearestPrioritizedAttackableTarget {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var attackInterval: Number? = null
    var reselectTargets: Boolean? = null
    var targetSearchHeight: Float? = null
    var mustReach: Boolean? = null
    var mustSee: Boolean? = null
    var mustSeeForgetDuration: Float? = null
    var persistTime: Float? = null
    var setPersistent: Boolean? = null
    var scanInterval: Int? = null
    var withinRadius: Float? = null
    var attackIntervalMin: Number? = null

    /**
     * 0..1
     *
     * entities to attack
     */
    fun entityTypes(value: BehEntityTypes.() -> Unit) {
        general["entity_types"] = BehEntityTypes().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        attackInterval?.let { general["attack_interval"] = it }
        reselectTargets?.let { general["reselect_targets"] = it }
        targetSearchHeight?.let { general["target_search_height"] = it }
        mustReach?.let { general["must_reach"] = it }
        mustSee?.let { general["must_see"] = it }
        mustSeeForgetDuration?.let { general["must_see_forget_duration"] = it }
        persistTime?.let { general["persist_time"] = it }
        setPersistent?.let { general["set_persistent"] = it }
        scanInterval?.let { general["scan_interval"] = it }
        withinRadius?.let { general["within_radius"] = it }
        attackIntervalMin?.let { general["attack_interval_min"] = it }
        return general
    }
}