package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class Dweller {
    @SerializedName("dwelling_type")
    @Expose
    var dwellingType: String? = null
        

    @SerializedName("dweller_role")
    @Expose
    var dwellerRole: String? = null
        

    @SerializedName("update_interval_base")
    @Expose
    var updateIntervalBase: Number? = null
        

    @SerializedName("update_interval_variant")
    @Expose
    var updateIntervalVariant: Number? = null
        

    @SerializedName("can_find_poi")
    @Expose
    var canFindPoi: Boolean? = null
        

    @SerializedName("can_migrate")
    @Expose
    var canMigrate: Boolean? = null
        

    @SerializedName("first_founding_reward")
    @Expose
    var firstFoundingReward: Number? = null
        

    @SerializedName("preferred_profession")
    @Expose
    var preferredProfession: String? = null
        
}