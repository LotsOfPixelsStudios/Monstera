package com.lop.devtools.monstera.files.properties.types

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.DebugMarker
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.molang.Molang

class BoolProperty: GenericProperty<Boolean> {
    @SerializedName("default")
    @Expose
    override var default: Any? = null

    @SerializedName("type")
    @Expose
    var typeData: String = "bool"
        @MonsteraBuildSetter set

    @SerializedName("client_sync")
    @Expose
    override var clientSync: Boolean? = null

    @DebugMarker
    override fun propertySpecificDebug() {}

    override fun default(value: Boolean) {
        this.default = value
    }

    override fun default(value: Molang) {
        this.default = value.data
    }
}