package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentNavigation {
    val general = mutableMapOf<String, Any>()

    var avoidPortals: Boolean? = null
    var avoidSun: Boolean? = null
    var avoidWater: Boolean? = null
    var canBreakDoors: Boolean? = null
    var canOpenDoors: Boolean? = null
    var canOpenIronDoors: Boolean? = null
    var canPassDoors: Boolean? = null
    var canPathFromAir: Boolean? = null
    var canPathOverWater: Boolean? = null
    var canPathOverLava: Boolean? = null
    var canSink: Boolean? = null
    var canWalkInLava: Boolean? = null
    var isAmphibious: Boolean? = null
    var canSwim: Boolean? = null
    var canWalk: Boolean? = null
    var avoidDamageBlocks: Boolean? = null
    var canBreach: Boolean? = null
    var canFloat: Boolean? = null
    var blocksToAvoid: ArrayList<String>? = null

    fun getData(): MutableMap<String, Any> {
        avoidPortals?.let { general["avoid_portals"] = it }
        avoidSun?.let { general["avoid_sun"] = it }
        avoidWater?.let { general["avoid_water"] = it }
        canBreakDoors?.let { general["can_break_doors"] = it }
        canOpenDoors?.let { general["can_open_iron_doors"] = it }
        canOpenIronDoors?.let { general["can_open_iron_doors"] = it }
        canPassDoors?.let { general["can_pass_doors"] = it }
        canPathFromAir?.let { general["can_path_from_air"] = it }
        canPathOverWater?.let { general["can_path_over_water"] = it }
        canPathOverLava?.let { general["can_path_over_lava"] = it }
        canSink?.let { general["can_sink"] = it }
        canWalkInLava?.let { general["can_walk_in_lava"] = it }
        isAmphibious?.let { general["is_amphibious"] = it }
        canSwim?.let { general["can_swim"] = it }
        canWalk?.let { general["can_walk"] = it }
        avoidDamageBlocks?.let { general["avoid_damage_blocks"] = it }
        canBreach?.let { general["can_breach"] = it }
        canFloat?.let { general["can_float"] = it }
        blocksToAvoid?.let {
            val convBlocks = arrayListOf<Any>()
            it.forEach { block ->
                convBlocks.add(mutableMapOf("name" to block))
            }
            general["blocks_to_avoid"] = convBlocks
        }
        return general
    }
}