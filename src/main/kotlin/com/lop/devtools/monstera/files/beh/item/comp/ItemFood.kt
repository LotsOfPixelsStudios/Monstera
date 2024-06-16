package com.lop.devtools.monstera.files.beh.item.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile

class ItemFood : MonsteraRawFile() {
    @SerializedName("can_always_eat")
    @Expose
    var canAlwaysEat: Boolean? = null

    @SerializedName("nutrition")
    @Expose
    var nutrition: Number? = null

    @SerializedName("using_converts_to")
    @Expose
    var usingConvertsTo: String? = null

    @SerializedName("saturation_modifier")
    @Expose
    var saturationModifier: Saturation? = null

    @SerializedName("effects")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var effects: MutableList<Effect>? = null
        @MonsteraBuildSetter set

    /**
     * adds an effect when the item is eaten, can be called multiple times
     */
    @OptIn(MonsteraBuildSetter::class)
    fun effect(data: Effect.() -> Unit) {
        effects = (effects ?: mutableListOf()).apply { add(Effect().apply(data)) }
    }

    class Effect : MonsteraRawFile() {
        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("chance")
        @Expose
        var chance: Number? = null

        @SerializedName("duration")
        @Expose
        var duration: Number? = null

        @SerializedName("amplifier")
        @Expose
        var amplifier: Int? = null
    }
}

enum class Saturation {
    POOR,
    LOW,
    NORMAL,
    GOOD,
    MAX,
    SUPERNATURAL;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}
