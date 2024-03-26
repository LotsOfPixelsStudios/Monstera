package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Tamemount {
    @SerializedName("min_temper")
    @Expose
    var minTemper: Number? = null
        

    @SerializedName("max_temper")
    @Expose
    var maxTemper: Number? = null
        

    @SerializedName("feed_text")
    @Expose
    var feedText: String? = null
        

    @SerializedName("ride_text")
    @Expose
    var rideText: String? = null
        

    @SerializedName("feed_items")
    @Expose
    var feedItemsData: MutableList<FeedItems>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * feedItems {
     *     item = wheat
     *     temperMod = 3
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun feedItems(value: FeedItems.() -> Unit) {
        feedItemsData = (feedItemsData ?: mutableListOf()).also { it.add(FeedItems().apply(value)) }
    }

    @SerializedName("auto_reject_items")
    @Expose
    var autoRejectItemsData: MutableList<AutoRejectItems>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * autoRejectItems {
     *     item = horsearmorleather
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun autoRejectItems(value: AutoRejectItems.() -> Unit) {
        autoRejectItemsData = (autoRejectItemsData ?: mutableListOf()).also { it.add(AutoRejectItems().apply(value)) }
    }

    @SerializedName("tame_event")
    @Expose
    var tameEventData: TameEvent? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * tameEvent {
     *     event = minecraft:on_tame
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun tameEvent(value: TameEvent.() -> Unit) {
        tameEventData = (tameEventData ?: TameEvent()).apply(value)
    }

    class FeedItems {
        @SerializedName("item")
        @Expose
        var item: String? = null
            

        @SerializedName("temper_mod")
        @Expose
        var temperMod: Number? = null
            
    }

    class AutoRejectItems {
        @SerializedName("item")
        @Expose
        var item: String? = null
            
    }

    class TameEvent {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
