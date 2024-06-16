package com.lop.devtools.monstera.files.beh.entitiy.description

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.MonsteraRawFile

class BehEntityDescScripts : MonsteraRawFile() {
    @SerializedName("animate")
    @Expose
    var animateData: MutableList<Any>? = null
        @MonsteraBuildSetter set

    /**
     * 0..1
     *
     * @param anim: List of Strings that activate Animations defined in animations
     * @ sample animate(listOf("...","..."))
     */
    @OptIn(MonsteraBuildSetter::class)
    fun animate(anim: ArrayList<String>) {
        animateData = (animateData ?: mutableListOf()).also { it.addAll(anim) }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun animate(animation: String, query: Molang = Query.True) {
        animateData = if (query == Query.True)
            (animateData ?: mutableListOf()).also { it.add(animation) }
        else
            (animateData ?: mutableListOf()).also { it.add(mutableMapOf(animation to query.data)) }
    }
}