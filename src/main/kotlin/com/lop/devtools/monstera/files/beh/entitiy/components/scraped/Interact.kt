package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter

class Interact {

    @SerializedName("interactions")
    @Expose
    var interactionsData: MutableList<Interactions>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * interactions {
     *     giveItem = true
     *     takeItem = true
     *     interactText = action.interact.allay
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun interactions(value: Interactions.() -> Unit) {
        interactionsData = (interactionsData ?: mutableListOf()).also { it.add(Interactions().apply(value)) }
    }

    class Interactions {

        @SerializedName("on_interact")
        @Expose
        var onInteractData: OnInteract? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * onInteract {
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun onInteract(value: OnInteract.() -> Unit) {
            onInteractData = (onInteractData ?: OnInteract()).apply(value)
        }

        @SerializedName("give_item")
        @Expose
        var giveItem: Boolean? = null
            

        @SerializedName("take_item")
        @Expose
        var takeItem: Boolean? = null
            

        @SerializedName("interact_text")
        @Expose
        var interactText: String? = null
            
    }

    class OnInteract {

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
}