package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class BehLookAtEntity : MonsteraRawFile() {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        
    @SerializedName("look_distance")
    @Expose
    var lookDistance: Number? = null
        
    @SerializedName("filters")
    @Expose
    var filtersData: BehEntityFilter? = null
        
    /**
     * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
     * ```
     * filters {
     *     test = is_family
     *     subject = other
     *     value = mob
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun filters(value: BehEntityFilter.() -> Unit) {
        filtersData = (filtersData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("angle_of_view_horizontal")
    @Expose
    var angleOfViewHorizontal: Number? = null
        
    @SerializedName("probability")
    @Expose
    var probability: Number? = null
}