package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentExplode {
    val general = mutableMapOf<String, Any>()

    var fuseLit: Boolean? = null
    var power: Float? = null
    var causeFire: Boolean? = null
    var destroyAffectedByGriefing: Boolean? = null
    var breakBlocks: Boolean? = null
    var fireAffectedByGriefing: Boolean? = null
    var maxResistance: Float? = null

    /**
     * 0..1
     *
     * The range for the random amount of time the fuse will be lit before exploding, a negative value means the explosion will be immediate.
     */
    fun fuseLength(min: Float, max: Float = min) {
        general["fuse_length"] = arrayListOf(min, max)
    }

    fun getData(): MutableMap<String, Any> {
        fuseLit?.let { general["fuse_lit"] = it }
        power?.let { general["power"] = it }
        causeFire?.let { general["causes_fire"] = it }
        destroyAffectedByGriefing?.let { general["destroy_affected_by_griefing"] = it }
        breakBlocks?.let { general["breaks_blocks"] = it }
        fireAffectedByGriefing?.let { general["fire_affected_by_griefing"] = it }
        maxResistance?.let { general["max_resistance"] = it }
        return general
    }
}