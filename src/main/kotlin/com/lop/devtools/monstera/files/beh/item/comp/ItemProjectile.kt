package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class ItemProjectile : MonsteraRawFile() {
    @SerializedName("minimum_critical_power")
    @Expose
    var minimumCriticalPower: Number? = null

    @SerializedName("projectile_entity")
    @Expose
    var projectileEntity: String? = null
}