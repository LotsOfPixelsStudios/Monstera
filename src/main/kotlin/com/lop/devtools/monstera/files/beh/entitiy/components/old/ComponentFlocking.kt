package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentFlocking {
    val general = mutableMapOf<String, Any>()

    var inWater: Boolean? = null
    var matchVariants: Boolean? = null
    var useCenterOfMass: Boolean? = null
    var lowFlockLimit: Number? = null
    var highFlockLimit: Number? = null
    var goalWeight: Number? = null
    var lonerChance: Number? = null
    var influenceRadius: Number? = null
    var breachInfluence: Number? = null
    var separationWeight: Number? = null
    var separationThreshold: Number? = null
    var cohesionWeight: Number? = null
    var cohesionThreshold: Number? = null
    var innerCohesionThreshold: Number? = null
    var minHeight: Number? = null
    var maxHeight: Number? = null
    var blockDistance: Number? = null
    var blockWeight: Number? = null

    fun getData(): MutableMap<String, Any> {
        inWater?.let { general["in_water"] = it }
        matchVariants?.let { general["match_variants"] = it }
        useCenterOfMass?.let { general["use_center_of_mass"] = it }
        lowFlockLimit?.let { general["low_flock_limit"] = it }
        highFlockLimit?.let { general["high_flock_limit"] = it }
        goalWeight?.let { general["goal_weight"] = it }
        lonerChance?.let { general["loner_chance"] = it }
        influenceRadius?.let { general["influence_radius"] = it }
        breachInfluence?.let { general["breach_influence"] = it }
        separationWeight?.let { general["separation_weight"] = it }
        separationThreshold?.let { general["separation_threshold"] = it }
        cohesionWeight?.let { general["cohesion_weight"] = it }
        cohesionThreshold?.let { general["cohesion_threshold"] = it }
        innerCohesionThreshold?.let { general["innner_cohesion_threshold"] = it }
        minHeight?.let { general["min_height"] = it }
        maxHeight?.let { general["max_height"] = it }
        blockDistance?.let { general["block_distance"] = it }
        blockWeight?.let { general["block_weight"] = it }
        return general
    }
}