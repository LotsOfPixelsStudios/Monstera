package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

@Deprecated("")
class BehEntityTypes {
    val general = arrayListOf<Any>()

    /**
     * 0..n
     *
     * set a new entity type
     * @sample sampleType
     */
    fun type(value: CompEntityType.() -> Unit) {
        general.add(CompEntityType().apply(value).unsafe.getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }

    private fun sampleType() {
        type {
            maxDist = 2f
            mustSee = false
            mustForgetDuration = 3f
            sprintSpeedMultiplier = 1.2f
            walkSpeedMultiplier = 1f
            filters {

            }
        }
    }
}

class CompEntityType: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            maxDist?.let { general["max_dist"] = it }
            mustSee?.let { general["must_see"] = it }
            mustForgetDuration?.let { general["must_see_forget_duration"] = it }
            sprintSpeedMultiplier?.let { general["sprint_speed_multiplier"] = it }
            walkSpeedMultiplier?.let { general["walk_speed_multiplier"] = it }
            priority?.let { general["priority"] = it }

            return general
        }
    }



    var maxDist: Number? = null
    var mustSee: Boolean? = null
    var mustForgetDuration: Number? = null
    var sprintSpeedMultiplier: Float? = null
    var walkSpeedMultiplier: Float? = null

    var priority: Int? = null   //usage only in nearest prioritized target

    fun filters(value: BehEntityFilter.() -> Unit) {
        unsafe.general["filters"] = BehEntityFilter().apply(value).getData()
    }
}