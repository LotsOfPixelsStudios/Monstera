package com.lop.devtools.monstera.files.beh.entitiy.components

class ComponentRailSensor {
    val general = mutableMapOf<String, Any>()

    var checkBlockTypes: Boolean? = null
    var ejectOnActivate: Boolean? = null
    var ejectOnDeactivate: Boolean? = null
    var tickCommandBlockOnActivate: Boolean? = null
    var tickCommandBlockOnDeactivate: Boolean? = null
    var onActivate: String? = null
    var onDeactivate: String? = null

    fun getData(): MutableMap<String, Any> {
        checkBlockTypes?.let { general["check_block_types"] = it }
        ejectOnActivate?.let { general["eject_on_activate"] = it }
        ejectOnDeactivate?.let { general["eject_on_deactivate"] = it }
        tickCommandBlockOnActivate?.let { general["tick_command_block_on_activate"] = it }
        tickCommandBlockOnDeactivate?.let { general["tick_command_block_on_deactivate"] = it }
        onActivate?.let { general["on_activate"] = it }
        onDeactivate?.let { general["on_deactivate"] = it }
        return general
    }
}