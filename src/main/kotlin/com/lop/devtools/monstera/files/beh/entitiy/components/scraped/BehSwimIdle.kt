package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehSwimIdle : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null

    @SerializedName("idle_time")
    @Expose
    var idleTime: Number? = null

    @SerializedName("success_rate")
    @Expose
    var successRate: Number? = null
}