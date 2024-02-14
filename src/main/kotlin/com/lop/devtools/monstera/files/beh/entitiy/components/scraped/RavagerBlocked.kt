package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class RavagerBlocked {
    @SerializedName("knockback_strength")
    @Expose
    var knockbackStrength: Number? = null
        

    @SerializedName("reaction_choices")
    @Expose
    var reactionChoicesData: MutableList<ReactionChoices>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * reactionChoices {
     *     weight = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun reactionChoices(value: ReactionChoices.() -> Unit) {
        reactionChoicesData = (reactionChoicesData ?: mutableListOf()).also { it.add(ReactionChoices().apply(value)) }
    }

    class ReactionChoices {
        @SerializedName("weight")
        @Expose
        var weight: Number? = null
            

        @SerializedName("value")
        @Expose
        var valueData: Value? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * value {
         *     event = minecraft:become_stunned
         *     target = self
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun value(value: Value.() -> Unit) {
            valueData = (valueData ?: Value()).apply(value)
        }
    }

    class Value {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
