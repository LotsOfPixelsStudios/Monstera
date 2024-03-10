package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class BehEatBlock {
    @SerializedName("priority")
    @Expose
    var priority: Number? = null
        

    @SerializedName("success_chance")
    @Expose
    var successChance: String? = null
        

    @SerializedName("time_until_eat")
    @Expose
    var timeUntilEat: Number? = null
        

    @SerializedName("eat_and_replace_block_pairs")
    @Expose
    var eatAndReplaceBlockPairsData: MutableList<EatAndReplaceBlockPairs>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * eatAndReplaceBlockPairs {
     *     eatBlock = grass
     *     replaceBlock = dirt
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun eatAndReplaceBlockPairs(value: EatAndReplaceBlockPairs.() -> Unit) {
        eatAndReplaceBlockPairsData =
            (eatAndReplaceBlockPairsData ?: mutableListOf()).also { it.add(EatAndReplaceBlockPairs().apply(value)) }
    }

    @SerializedName("on_eat")
    @Expose
    var onEatData: OnEat? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * onEat {
     *     event = minecraft:on_eat_block
     *     target = self
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun onEat(value: OnEat.() -> Unit) {
        onEatData = (onEatData ?: OnEat()).apply(value)
    }

    class EatAndReplaceBlockPairs {
        @SerializedName("eat_block")
        @Expose
        var eatBlock: String? = null
            

        @SerializedName("replace_block")
        @Expose
        var replaceBlock: String? = null
            
    }

    class OnEat {
        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
