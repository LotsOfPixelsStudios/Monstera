package com.lop.devtools.monstera.files.properties.types

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.DebugMarker
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.getMonsteraLogger

class IntProperty: NumberProperty<Int> {
    @SerializedName("default")
    @Expose
    override var default: Any? = null

    @SerializedName("type")
    @Expose
    var typeData: String = "int"
        @MonsteraBuildSetter set

    @SerializedName("client_sync")
    @Expose
    override var clientSync: Boolean? = null

    @DebugMarker
    override fun propertySpecificDebug() {
        if(rangeData.isNullOrEmpty())
            getMonsteraLogger(this.javaClass.name).warn("Range of Int property is empty, property will be ignored!")
    }

    @SerializedName("range")
    @Expose
    var rangeData: MutableList<Number>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    override var range: Pair<Int, Int> = 0 to 0
        set(value) {
            rangeData = mutableListOf(value.first, value.second)
            field = value
        }

    override fun default(value: Number) {
        this.default = value
    }

    override fun default(value: Molang) {
        this.default = value.data
    }
}