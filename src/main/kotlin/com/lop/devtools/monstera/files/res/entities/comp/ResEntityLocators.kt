package com.lop.devtools.monstera.files.res.entities.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile

class ResEntityLocators : MonsteraRawFile() {
    @SerializedName("lead")
    @Expose
    var leadData: MutableMap<String, MutableList<Number>>? = null
        @MonsteraBuildSetter set

    /**
     * can be called multiple times
     *
     * @sample sample
     */
    @OptIn(MonsteraBuildSetter::class)
    fun lead(locationName: String, location: ArrayList<Number>) {
        leadData = (leadData ?: mutableMapOf()).apply {
            put(locationName, location)
        }
    }

    private fun sample() {
        lead("head", arrayListOf(0.0f, 0.0f, 0.0f))
    }
}