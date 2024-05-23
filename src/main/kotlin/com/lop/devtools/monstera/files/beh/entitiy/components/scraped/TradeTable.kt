package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class TradeTable : MonsteraRawFile() {
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
        
    @SerializedName("table")
    @Expose
    var table: String? = null
        
    @SerializedName("convert_trades_economy")
    @Expose
    var convertTradesEconomy: Boolean? = null
}