package com.lop.devtools.monstera.files.beh.spawnrules.conditions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter

class SpawnBiomeFilter {
    private val filterEntries = mutableListOf<SpawnBiomeFilter>()

    @OptIn(MonsteraBuildSetter::class)
    fun filterEntry(value: BiomeTag, operator: String = "==") {
        this.operator = operator
        this.value = value

        filterEntries.add(SpawnBiomeFilter().apply {
            this.operator = operator
            this.value = value
        })
    }

    @OptIn(MonsteraBuildSetter::class)
    fun anyOf(data: SpawnBiomeFilter.() -> Unit) {
        test = null
        operator = null
        value = null

        anyOfData = (anyOfData ?: mutableListOf()).apply { addAll(SpawnBiomeFilter().apply(data).filterEntries) }
    }


    @OptIn(MonsteraBuildSetter::class)
    fun allOf(data: SpawnBiomeFilter.() -> Unit) {
        test = null
        operator = null
        value = null

        allOfData = (allOfData ?: mutableListOf()).apply { addAll(SpawnBiomeFilter().apply(data).filterEntries) }
    }

    @SerializedName("test")
    @Expose
    var test: String? = "has_biome_tag"
        @MonsteraBuildSetter set

    @SerializedName("operator")
    @Expose
    var operator: String? = null
        @MonsteraBuildSetter set

    @SerializedName("value")
    @Expose
    var value: BiomeTag? = null
        @MonsteraBuildSetter set

    @SerializedName("any_of")
    @Expose
    var anyOfData: MutableList<SpawnBiomeFilter>? = null

    @SerializedName("all_of")
    @Expose
    var allOfData: MutableList<SpawnBiomeFilter>? = null
}