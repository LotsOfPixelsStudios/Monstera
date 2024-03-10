package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class EconomyTradeTable {
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
        

    @SerializedName("table")
    @Expose
    var table: String? = null
        

    @SerializedName("new_screen")
    @Expose
    var newScreen: Boolean? = null
        

    @SerializedName("persist_trades")
    @Expose
    var persistTrades: Boolean? = null
        

    @SerializedName("cured_discount")
    @Expose
    var curedDiscountData: MutableList<Number>? = null
        

    fun curedDiscount(vararg value: Number) {
        curedDiscountData = (curedDiscountData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("max_cured_discount")
    @Expose
    var maxCuredDiscountData: MutableList<Number>? = null
        

    fun maxCuredDiscount(vararg value: Number) {
        maxCuredDiscountData = (maxCuredDiscountData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }
}