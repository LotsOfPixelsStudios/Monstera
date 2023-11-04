package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentRangedAttack {
    val general = mutableMapOf<String, Any>()

    var priority: Int? = null
    var burstShots: Int? = null
    var burstInterval: Float? = null
    var chargeChargedTrigger: Float? = null
    var chargeShootTrigger: Float? = null
    var attackIntervalMin: Float? = null
    var attackIntervalMax: Float? = null
    var attackRadius: Float? = null


    fun getData(): MutableMap<String, Any> {
        priority?.let { general["priority"] = it }
        burstShots?.let { general["burst_shots"] = it }
        burstInterval?.let { general["burst_interval"] = it }
        chargeChargedTrigger?.let { general["charge_charged_trigger"] = it }
        chargeShootTrigger?.let { general["charge_shoot_trigger"] = it }
        attackIntervalMin?.let { general["attack_interval_min"] = it }
        attackIntervalMax?.let { general["attack_interval_max"] = it }
        attackRadius?.let { general["attack_radius"] = it }
        return general
    }
}