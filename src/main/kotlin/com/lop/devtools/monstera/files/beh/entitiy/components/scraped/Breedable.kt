package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Breedable : MonsteraRawFile() {
    @SerializedName("require_tame")
    @Expose
    var requireTame: Boolean? = null
        
    @SerializedName("breed_items")
    @Expose
    var breedItems: String? = null
        
    @SerializedName("transform_to_item")
    @Expose
    var transformToItem: String? = null
        
    @SerializedName("breeds_with")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var breedsWithData: BreedsWith? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * breedsWith {
     *     mateType = minecraft:axolotl
     *     babyType = minecraft:axolotl
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun breedsWith(value: BreedsWith.() -> Unit) {
        breedsWithData = (breedsWithData ?: BreedsWith()).apply(value)
    }

    @SerializedName("mutation_factor")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var mutationFactorData: MutationFactor? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * mutationFactor {
     *     variant = 0.00083
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun mutationFactor(value: MutationFactor.() -> Unit) {
        mutationFactorData = (mutationFactorData ?: MutationFactor()).apply(value)
    }

    @SerializedName("require_full_health")
    @Expose
    var requireFullHealth: Boolean? = null
        
    @SerializedName("allow_sitting")
    @Expose
    var allowSitting: Boolean? = null
        
    @SerializedName("parent_centric_attribute_blending")
    @Expose
    var parentCentricAttributeBlendingData: MutableList<String>? = null
        
    fun parentCentricAttributeBlending(vararg value: String) {
        parentCentricAttributeBlendingData =
            (parentCentricAttributeBlendingData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("inherit_tamed")
    @Expose
    var inheritTamed: Boolean? = null
        
    @SerializedName("causes_pregnancy")
    @Expose
    var causesPregnancy: Boolean? = null
        
    @SerializedName("love_filters")
    @Expose
    var loveFiltersData: BehEntityFilter? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * loveFilters {
     *     test = has_component
     *     subject = self
     *     operator = !=
     *     value = minecraft:attack_cooldown
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun loveFilters(value: BehEntityFilter.() -> Unit) {
        loveFiltersData = (loveFiltersData ?: BehEntityFilter()).apply(value)
    }

    @SerializedName("mutation_strategy")
    @Expose
    var mutationStrategy: String? = null
        
    @SerializedName("random_variant_mutation_interval")
    @Expose
    var randomVariantMutationIntervalData: MutableList<Number>? = null
        
    fun randomVariantMutationInterval(vararg value: Number) {
        randomVariantMutationIntervalData =
            (randomVariantMutationIntervalData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("random_extra_variant_mutation_interval")
    @Expose
    var randomExtraVariantMutationIntervalData: MutableList<Number>? = null
        
    fun randomExtraVariantMutationInterval(vararg value: Number) {
        randomExtraVariantMutationIntervalData =
            (randomExtraVariantMutationIntervalData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    @SerializedName("deny_parents_variant")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var denyParentsVariantData: DenyParentsVariant? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * denyParentsVariant {
     *     chance = 0.00098
     *     minVariant = 0
     *     maxVariant = 1
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun denyParentsVariant(value: DenyParentsVariant.() -> Unit) {
        denyParentsVariantData = (denyParentsVariantData ?: DenyParentsVariant()).apply(value)
    }

    @SerializedName("blend_attributes")
    @Expose
    var blendAttributes: Boolean? = null
        
    @SerializedName("environment_requirements")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var environmentRequirementsData: EnvironmentRequirements? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * environmentRequirements {
     *     blocks = bamboo
     *     count = 8
     *     radius = 5
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun environmentRequirements(value: EnvironmentRequirements.() -> Unit) {
        environmentRequirementsData = (environmentRequirementsData ?: EnvironmentRequirements()).apply(value)
    }

    class BreedsWith : MonsteraRawFile() {
        @SerializedName("mate_type")
        @Expose
        var mateType: String? = null
            
        @SerializedName("baby_type")
        @Expose
        var babyType: String? = null
            
        @SerializedName("breed_event")
        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var breedEventData: BreedEvent? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * breedEvent {
         *     event = minecraft:entity_born
         *     target = baby
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun breedEvent(value: BreedEvent.() -> Unit) {
            breedEventData = (breedEventData ?: BreedEvent()).apply(value)
        }
    }

    class BreedEvent : MonsteraRawFile() {
        @SerializedName("event")
        @Expose
        var event: String? = null
            
        @SerializedName("target")
        @Expose
        var target: Subject? = null
    }

    class MutationFactor : MonsteraRawFile() {
        @SerializedName("variant")
        @Expose
        var variant: Number? = null
            
    }

    class DenyParentsVariant : MonsteraRawFile() {
        @SerializedName("chance")
        @Expose
        var chance: Number? = null
            
        @SerializedName("min_variant")
        @Expose
        var minVariant: Number? = null
            
        @SerializedName("max_variant")
        @Expose
        var maxVariant: Number? = null
            
    }

    class EnvironmentRequirements : MonsteraRawFile() {
        @SerializedName("blocks")
        @Expose
        var blocks: String? = null
            
        @SerializedName("count")
        @Expose
        var count: Number? = null
            
        @SerializedName("radius")
        @Expose
        var radius: Number? = null
    }
}
