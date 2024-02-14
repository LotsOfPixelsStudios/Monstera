package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Giveable {

    @SerializedName("triggers")
    @Expose
    var triggersData: Triggers? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * triggers {
     *     cooldown = 3.0
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun triggers(value: Triggers.() -> Unit) {
        triggersData = (triggersData ?: Triggers()).apply(value)
    }

    class Triggers {

        @SerializedName("cooldown")
        @Expose
        var cooldown: Number? = null
            

        @SerializedName("items")
        @Expose
        var itemsData: MutableList<String>? = null
            

        fun items(vararg value: String) {
            itemsData = (itemsData ?: mutableListOf()).also { it.addAll(value.toList()) }
        }

        @SerializedName("on_give")
        @Expose
        var onGiveData: OnGive? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * onGive {
         *     event = minecraft:on_calm
         *     target = self
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun onGive(value: OnGive.() -> Unit) {
            onGiveData = (onGiveData ?: OnGive()).apply(value)
        }
    }

    class OnGive {

        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
