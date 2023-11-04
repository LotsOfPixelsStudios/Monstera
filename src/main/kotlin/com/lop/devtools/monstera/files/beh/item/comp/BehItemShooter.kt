package com.lop.devtools.monstera.files.beh.item.comp

class BehItemShooter {
    val general = mutableMapOf<String, Any>()

    /**
     * Ammunition.
     */
    fun ammunition(ammunition: String) {
        general.apply {
            put("ammunition", ammunition)
        }
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
        general.apply {
            put("launch_power_scale", value)
        }
    }

    /**
     * Draw Duration. Default is set to 0.
     */
    fun maxDrawDuration(value: Int = 0) {
        general.apply {
            put("max_draw_duration", value)
        }
    }

    /**
     * Launch power. Default is set to 1.0.
     */
    fun maxLaunchPower(value: Float = 1.0f) {
        general.apply {
            put("max_launch_power", value)
        }
    }

    /**
     * Scale power by draw duration? Default is set to false.
     */
    fun scalePowerByDrawDuration(value: Boolean = false) {
        general.apply {
            put("scale_power_by_draw_duration", value)
        }
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }
}