package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class OnTargetEscape : MonsteraRawFile() {
    @SerializedName("event")
    @Expose
    var event: String? = null
        
    @SerializedName("target")
    @Expose
    var target: Subject? = null
        
    @SerializedName("filters")
    @Expose
    var filtersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     * com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.com.lop.devtools.monstera.files.beh.entitiy.components.scraped.Filters allow data objects to specify test criteria which allows their use.
     * ```
     * filters {
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun filters(value: BehEntityFilter.() -> Unit) {
        filtersData = (filtersData ?: BehEntityFilter()).apply(value)
    }
}
