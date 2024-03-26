package com.lop.devtools.monstera.files.res.entities.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.MonsteraRawFile

class ResEntityScripts : MonsteraRawFile() {
    @SerializedName("animate")
    @Expose
    var animateData: MutableList<Any>? = null
        @MonsteraBuildSetter set

    /**
     * animate a animation controller
     */
    @OptIn(MonsteraBuildSetter::class)
    fun animate(animationRef: String, condition: Molang = Query.True) {
        animateData = (animateData ?: mutableListOf()).apply {
            if (condition == Query.True)
                add(animationRef)
            else
                add(mutableMapOf(animationRef to condition.data))
        }
    }

    @SerializedName("initialize")
    @Expose
    var initializeData: MutableList<Any>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun initializeEntry(entry: String) {
        initializeData = (initializeData ?: mutableListOf()).apply {
            add(entry)
        }
    }

    @SerializedName("pre_animation")
    @Expose
    var preAnimationData: MutableList<Any>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun preAnimationEntry(entry: String) {
        preAnimationData = (preAnimationData ?: mutableListOf()).apply {
            add(entry)
        }
    }

    @SerializedName("scale")
    @Expose
    var scale: String? = null

    @SerializedName("scaleX")
    @Expose
    var scaleX: Any? = null

    @SerializedName("scaleY")
    @Expose
    var scaleY: Any? = null
}