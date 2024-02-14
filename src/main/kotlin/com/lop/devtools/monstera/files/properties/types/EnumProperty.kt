package com.lop.devtools.monstera.files.properties.types

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.*
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.files.beh.entitiy.description.BehEntityDescription
import com.lop.devtools.monstera.getMonsteraLogger
import org.slf4j.LoggerFactory

@Suppress("MemberVisibilityCanBePrivate")
class EnumProperty: GenericProperty<String> {
    private fun logger() = getMonsteraLogger("Enum Property")

    @SerializedName("default")
    @Expose
    override var default: Any? = null

    @SerializedName("type")
    @Expose
    var typeData: String = "enum"
        @MonsteraBuildSetter set

    @SerializedName("values")
    @Expose
    var values: MutableList<String>? = null
        set(value) {
            if((values?.size ?: 0) > 16) {
                logger().warn("max entries for enum values is 16!")
            }
            if(values.isNullOrEmpty()) {
                logger().warn("entries are empty, enum property will be ignored!")
            }
            values?.filter { it.length > 32 || it.isEmpty() }?.forEach {
                logger().warn("entry '$it' is invalid, length must be between 1 and 32, length was ${it.length}!")
            }
            field = value
        }

    @SerializedName("client_sync")
    @Expose
    override var clientSync: Boolean? = null

    @DebugMarker
    override fun propertySpecificDebug() {
        if(values == null)
            logger().warn("values are empty, enum property will be ignored!")
    }

    override fun default(value: String) {
        this.default = value
    }

    override fun default(value: Molang) {
        this.default = value.data
    }
}