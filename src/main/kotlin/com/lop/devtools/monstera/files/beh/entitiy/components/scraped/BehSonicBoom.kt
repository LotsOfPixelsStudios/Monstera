package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehSonicBoom : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("duration")
    @Expose
    var duration: Number? = null
        
    @SerializedName("speed_multiplier")
    @Expose
    var speedMultiplier: Number? = null
        
    @SerializedName("attack_damage")
    @Expose
    var attackDamage: Number? = null
        
    @SerializedName("attack_range_horizontal")
    @Expose
    var attackRangeHorizontal: Number? = null
        
    @SerializedName("attack_range_vertical")
    @Expose
    var attackRangeVertical: Number? = null
        
    @SerializedName("attack_cooldown")
    @Expose
    var attackCooldown: Number? = null
        
    @SerializedName("knockback_vertical_strength")
    @Expose
    var knockbackVerticalStrength: Number? = null
        
    @SerializedName("knockback_horizontal_strength")
    @Expose
    var knockbackHorizontalStrength: Number? = null
        
    @SerializedName("knockback_height_cap")
    @Expose
    var knockbackHeightCap: Number? = null
        
    @SerializedName("duration_until_attack_sound")
    @Expose
    var durationUntilAttackSound: Number? = null
        
    @SerializedName("charge_sound")
    @Expose
    var chargeSound: String? = null
        
    @SerializedName("attack_sound")
    @Expose
    var attackSound: String? = null
}