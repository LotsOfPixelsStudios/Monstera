package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class ComponentStalkAndPounceOnTarget {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var stalkSpeed: Float? = null
    var maxStalkDistance: Float? = null
    var leapHeight: Float? = null
    var leapDistance: Float? = null
    var pounceMaxDist: Float? = null
    var interestTime: Float? = null
    var stuckTime: Float? = null
    var strikeDist: Float? = null
    var setPersistent: Boolean? = null

    /**
     * 0..1
     *
     * block filter
     */
    fun struckBlocks(filter: BehEntityFilter.() -> Unit) {
        general["stuck_blocks"] = BehEntityFilter().apply(filter).getData()
    }

    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        stalkSpeed?.let { general["stalk_speed"] = it }
        maxStalkDistance?.let { general["max_stalk_dist"] = it }
        leapHeight?.let { general["leap_height"] = it }
        leapDistance?.let { general["leap_dist"] = it }
        pounceMaxDist?.let { general["pounce_max_dist"] = it }
        interestTime?.let { general["interest_time"] = it }
        stuckTime?.let { general["stuck_time"] = it }
        strikeDist?.let { general["strike_dist"] = it }
        setPersistent?.let { general["set_persistent"] = it }
        return general
    }
}