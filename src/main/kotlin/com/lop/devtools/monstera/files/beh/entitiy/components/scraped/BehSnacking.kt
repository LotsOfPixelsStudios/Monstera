package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class BehSnacking {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("snacking_cooldown")
    @Expose
    var snackingCooldown: Number? = null
        

    @SerializedName("snacking_cooldown_min")
    @Expose
    var snackingCooldownMin: Number? = null
        

    @SerializedName("snacking_stop_chance")
    @Expose
    var snackingStopChance: Number? = null
        

    @SerializedName("items")
    @Expose
    var itemsData: MutableList<String>? = null
        

    fun items(vararg value: String) {
        itemsData = (itemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}