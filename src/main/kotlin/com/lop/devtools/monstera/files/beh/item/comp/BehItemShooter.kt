package com.lop.devtools.monstera.files.beh.item.comp

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafe
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class BehItemShooter: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        val ammo = mutableListOf<BehItemShooterAmmo>()

        override fun getData(): MutableMap<String, Any> {
            if(ammo.isNotEmpty())
                general["ammunition"] = ammo.map { it.unsafe.getData() }
            return general
        }
    }

    @Deprecated("", ReplaceWith("unsafe.general"))
    val general = mutableMapOf<String, Any>()

    var chargeOnDraw: Boolean = false
        set(value) {
            unsafe.general["charge_on_draw"] = value
            field = value
        }



    /**
     * Ammunition.
     */
    fun ammunition(ammunition: BehItemShooterAmmo.() -> Unit) {
        unsafe.ammo.add(BehItemShooterAmmo().apply(ammunition))
    }

    /**
     * Charge on draw? Default is set to false.
     */
    fun chargeOnDraw(chargeOnDraw: Boolean = false) {
        general.apply {
            put("charge_on_draw", chargeOnDraw)
        }
    }

    /**
     * Launch power scale. Default is set to 1.0.
     */
    fun launchPowerScale(value: Float = 1.0f) {
        unsafe.general["launch_power_scale"] = value
    }

    /**
     * Draw Duration. Default is set to 0.
     */
    fun maxDrawDuration(value: Int = 0) {
        unsafe.general["max_draw_duration"] = value
    }

    /**
     * Launch power. Default is set to 1.0.
     */
    fun maxLaunchPower(value: Float = 1.0f) {
        unsafe.general["max_launch_power"] = value
    }

    /**
     * Scale power by draw duration? Default is set to false.
     */
    fun scalePowerByDrawDuration(value: Boolean = false) {
        unsafe.general["scale_power_by_draw_duration"] = value
    }

    @Deprecated("", ReplaceWith("unsafe.getData()"))
    fun getData(): MutableMap<String, Any> = unsafe.getData()
}

class BehItemShooterAmmo: MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe: MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData() = general
    }

    var item: String = ""
        set(value) {
            unsafe.general["item"] = value
            field = value
        }

    var useOffhand: Boolean = false
        set(value) {
            unsafe.general["use_offhand"] = value
            field = value
        }

    var searchInventory: Boolean = false
        set(value) {
            unsafe.general["search_inventory"] = value
            field = value
        }

    var useInCreative: Boolean = false
        set(value) {
            unsafe.general["use_in_creative"] = value
            field = value
        }
}