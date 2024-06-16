package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class Inventory : MonsteraRawFile() {
    @SerializedName("inventory_size")
    @Expose
    var inventorySize: Number? = null

    @SerializedName("container_type")
    @Expose
    var containerType: String? = null

    @SerializedName("can_be_siphoned_from")
    @Expose
    var canBeSiphonedFrom: Boolean? = null

    @SerializedName("additional_slots_per_strength")
    @Expose
    var additionalSlotsPerStrength: Number? = null

    @SerializedName("private")
    @Expose
    var private: Boolean? = null
}