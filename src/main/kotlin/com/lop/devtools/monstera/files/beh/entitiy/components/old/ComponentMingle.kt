package com.lop.devtools.monstera.files.beh.entitiy.components.old

class ComponentMingle {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var speedMultiplier: Float? = null
    var duration: Float? = null
    var cooldownTime: Float? = null
    var minglePartnerType: ArrayList<String>? = null
    var mingleDistance: Float? = null

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        speedMultiplier?.let { general["speed_multiplier"] = it }
        duration?.let { general["duration"] = it }
        cooldownTime?.let { general["cooldown_time"] = it }
        minglePartnerType?.let { general["mingle_partner_type"] = it }
        mingleDistance?.let { general["mingle_distance"] = it }
        return general
    }
}