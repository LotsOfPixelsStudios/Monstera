package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class Transformation {
    @SerializedName("into")
    @Expose
    var into: String? = null
        

    @SerializedName("transformation_sound")
    @Expose
    var transformationSound: String? = null
        

    @SerializedName("keep_level")
    @Expose
    var keepLevel: Boolean? = null
        

    @SerializedName("drop_equipment")
    @Expose
    var dropEquipment: Boolean? = null
        

    @SerializedName("delay")
    @Expose
    var delayData: Delay? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * delay {
     *     value = 15
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun delay(value: Delay.() -> Unit) {
        delayData = (delayData ?: Delay()).apply(value)
    }

    @SerializedName("drop_inventory")
    @Expose
    var dropInventory: Boolean? = null
        

    @SerializedName("preserve_equipment")
    @Expose
    var preserveEquipment: Boolean? = null
        

    @SerializedName("begin_transform_sound")
    @Expose
    var beginTransformSound: String? = null
        

    class Delay {
        @SerializedName("value")
        @Expose
        var value: Number? = null
            
    }
}
