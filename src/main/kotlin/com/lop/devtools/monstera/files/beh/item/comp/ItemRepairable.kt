@file:OptIn(MonsteraBuildSetter::class)

package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.molang.Molang

open class ItemRepairable {
    @SerializedName("on_repaired")
    @Expose
    var onRepaired: String? = null

    @SerializedName("repair_items")
    @Expose
    var repairItems: MutableList<RepairItem>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun repairItem(data: RepairItem.() -> Unit) {
        repairItems = (repairItems ?: mutableListOf()).apply { add(RepairItem().apply(data)) }
    }

    open class RepairItem {
        @SerializedName("items")
        @Expose
        var items: MutableList<String>? = null
            @MonsteraBuildSetter set

        fun items(vararg item: String) {
            items = (items ?: mutableListOf()).apply { addAll(item) }
        }

        @SerializedName("repair_amount")
        @Expose
        var repairAmountData: Any? = null
            @MonsteraBuildSetter set

        @OptIn(MonsteraBuildSetter::class)
        fun repairAmount(value: Number) {
            repairAmountData = value
        }

        @OptIn(MonsteraBuildSetter::class)
        fun repairAmount(value: Molang) {
            repairAmountData = value.data
        }
    }
}