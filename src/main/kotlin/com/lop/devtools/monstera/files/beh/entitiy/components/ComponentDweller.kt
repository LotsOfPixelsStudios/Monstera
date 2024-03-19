package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentDweller {
    val general = mutableMapOf<String, Any>()

    var dwellingType: String? = null
    var dwellerRole: String? = null
    var updateIntervalBase: Number? = null
    var updateIntervalVariant: Number? = null
    var canFindPoi: Boolean? = null
    var canMigrate: Boolean? = null
    var firstFoundingReward: Number? = null

    fun getData(): MutableMap<String, Any> {
        dwellingType?.let { general["dwelling_type"] = it }
        dwellerRole?.let { general["dweller_role"] = it }
        updateIntervalBase?.let { general["update_interval_base"] = it }
        updateIntervalVariant?.let { general["update_interval_variant"] = it }
        canFindPoi?.let { general["can_find_poi"] = it }
        canMigrate?.let { general["can_migrate"] = it }
        firstFoundingReward?.let { general["first_founding_reward"] = it }
        return general
    }
}