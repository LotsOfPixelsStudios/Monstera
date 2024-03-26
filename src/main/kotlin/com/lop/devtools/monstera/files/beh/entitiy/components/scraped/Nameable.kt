package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Nameable {
    @SerializedName("always_show")
    @Expose
    var alwaysShow: Boolean? = null
        

    @SerializedName("allow_name_tag_renaming")
    @Expose
    var allowNameTagRenaming: Boolean? = null
        

    @SerializedName("default_trigger")
    @Expose
    var defaultTriggerData: DefaultTrigger? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * defaultTrigger {
     *     event = minecraft:stop_johnny
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun defaultTrigger(value: DefaultTrigger.() -> Unit) {
        defaultTriggerData = (defaultTriggerData ?: DefaultTrigger()).apply(value)
    }

    @SerializedName("name_actions")
    @Expose
    var nameActionsData: MutableList<NameActions>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * nameActions {
     *     nameFilter = Johnny
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun nameActions(value: NameActions.() -> Unit) {
        nameActionsData = (nameActionsData ?: mutableListOf()).also { it.add(NameActions().apply(value)) }
    }

    class DefaultTrigger {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }

    class NameActions {
        @SerializedName("name_filter")
        @Expose
        var nameFilter: String? = null
            

        @SerializedName("on_named")
        @Expose
        var onNamedData: OnNamed? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * onNamed {
         *     event = minecraft:start_johnny
         *     target = self
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun onNamed(value: OnNamed.() -> Unit) {
            onNamedData = (onNamedData ?: OnNamed()).apply(value)
        }
    }

    class OnNamed {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
