package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.beh.entitiy.data.BehEntityFilter
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentBreedable : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val breedWith = BehEntityBreedWith()
        val denyParentVar = BehEntityDenyParentsVariant()
        val envVar = BehEntityEnvReq()
        val loveFilter = BehEntityFilter()
        val mutFactor = BehEntityMutFac()

        override fun getData(): MutableMap<String, Any> {
            allowSitting?.let { general["allow_sitting"] = it }
            blendAttributes?.let { general["blend_attributes"] = it }
            breedCooldown?.let { general["breed_cooldown"] = it }
            breedItems?.let { general["breed_items"] = it }
            causesPregnancy?.let { general["causes_pregnancy"] = it }
            extraBabyChance?.let { general["extra_baby_chance"] = it }
            inheritTamed?.let { general["inherit_tamed"] = it }
            requireFullHealth?.let { general["require_full_health"] = it }
            requireTame?.let { general["require_tame"] = it }
            transformToItem?.let { general["transform_to_item"] = it }

            if (breedWith.unsafe.getData().isNotEmpty()) {
                general["breeds_with"] = breedWith.unsafe.getData()
            }
            if (denyParentVar.unsafe.getData().isNotEmpty()) {
                general["deny_parents_variant"] = denyParentVar.unsafe.getData()
            }
            if (envVar.getData().isNotEmpty()) {
                general["environment_requirements"] = envVar.getData()
            }
            if(loveFilter.getData().isNotEmpty()) {
                general["love_filters"] = loveFilter.getData()
            }
            if (mutFactor.unsafe.getData().isNotEmpty()) {
                general["mutation_factor"] = mutFactor.unsafe.getData()
            }
            return general
        }
    }

    var allowSitting: Boolean? = null
    var blendAttributes: Boolean? = null
    var breedCooldown: Number? = null
    var breedItems: ArrayList<String>? = null
    var causesPregnancy: Boolean? = null
    var extraBabyChance: Float? = null
    var inheritTamed: Boolean? = null
    var requireFullHealth: Boolean? = null
    var requireTame: Boolean? = null

    var transformToItem: String? = null

    /**
     * 0..1
     *
     * @param settings The list of entity definitions that this entity can breed with.
     * @sample BehEntityBreedWith.sample
     */
    fun breedsWith(settings: BehEntityBreedWith.() -> Unit) {
        unsafe.breedWith.apply(settings)
    }

    /**
     * 0..1
     *
     * @param settings Determines how likely the baby of parents with the same variant will deny that variant and take a random variant within the given range instead.
     * @sample BehEntityDenyParentsVariant.sample
     */
    fun denyParentsVariant(settings: BehEntityDenyParentsVariant.() -> Unit) {
        unsafe.denyParentVar.apply(settings)
    }

    /**
     * 0..1
     *
     * @param settings    The list of nearby block requirements to get the entity into the 'love' state.
     * @sample BehEntityEnvReq.sample
     */
    fun environmentRequirements(settings: BehEntityEnvReq.() -> Unit) {
        unsafe.envVar.apply(settings)
    }

    /**
     * 0..1
     *
     * The filters to run when attempting to fall in love.
     */
    fun loveFilters(settings: BehEntityFilter.() -> Unit) {
        unsafe.loveFilter.apply(settings)
    }

    /**
     * 0..1
     *
     * @param settings    Determines how likely the babies are to NOT inherit one of their parent's variances.
     * Values are between 0.0 and 1.0, with a higher number meaning more likely to mutate.
     * @sample BehEntityMutFac.sample
     */
    fun mutationFactor(settings: BehEntityMutFac.() -> Unit) {
        unsafe.mutFactor.apply(settings)
    }
}

class BehEntityBreedWith : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            babyType?.let { general["baby_type"] = it }
            mateType?.let { general["mate_type"] = it }
            return general
        }
    }

    var babyType: String? = null

    var mateType: String? = null

    /**
     * 0..1
     *
     * @param event Event to run when this entity breeds.
     */
    fun breedEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if (target != null)
            data["target"] = target.toString().lowercase()
        unsafe.general["breed_event"] = data
    }

    fun sample() {
        babyType = "minecraft:zombie"
        breedEvent("spawned", Subject.SELF)
        mateType = "minecraft:zombie"
    }
}

class BehEntityMutFac : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            color?.let { general["color"] = it }
            extraVariant?.let { general["extra_variant"] = it }
            variant?.let { general["variant"] = it }
            return general
        }
    }

    var color: Float? = null
    var extraVariant: Float? = null

    var variant: Float? = null

    fun sample() {
        color = 0.2f
        extraVariant = 0.2f
        variant = 0.2f
    }
}

class BehEntityDenyParentsVariant : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            chance?.let { general["chance"] = it }
            maxVariant?.let { general["max_variant"] = it }
            minVariant?.let { general["min_variant"] = it }
            return general
        }
    }

    var chance: Float? = null
    var maxVariant: Int? = null

    var minVariant: Int? = null

    fun sample() {
        chance = 0.2f
        maxVariant = 2
        minVariant = 1
    }
}

class BehEntityEnvReq {
    val general = mutableListOf<Any>()

    /**
     * 1..*
     *
     * @param blocks The block types required nearby for the entity to breed.
     * @param count The number of the required block types nearby for the entity to breed.
     * @param radius How many blocks radius from the mob's center to search in for the required blocks. Bounded between 0 and 16.
     */
    fun requirement(blocks: ArrayList<String>, count: Int, radius: Int = 16) {
        general.add(
            mutableMapOf(
                "blocks" to blocks,
                "count" to count,
                "radius" to radius
            )
        )
    }

    fun getData(): MutableList<Any> {
        return general
    }

    fun sample() {
        requirement(blocks = arrayListOf("minecraft:sand"), count = 2, radius = 16)
    }
}