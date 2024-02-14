package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class ComponentGenetics {
    val general = arrayListOf<Any>()

    /**
     * 1..*
     */
    fun gene(value: GeneComp.() -> Unit) {
        general.add(GeneComp().apply(value).getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class GeneComp {
    val general = mutableMapOf<String, Any>()

    var name: String? = null
    var useSimplifiedBreeding: Boolean? = null

    /**
     * 0..1
     *
     * Upper bound of the allele values for this gene, min: Lower bound of the allele values for this gene.
     */
    fun alleleRange(min: Int, max: Int) {
        general["allele_range"] = mutableMapOf(
            "range_min" to min,
            "range_max" to max
        )
    }

    fun geneticVariants(value: GenericVars.() -> Unit) {
        general["name"] = GenericVars().apply(value).getData()
    }

    fun getData(): MutableMap<String, Any> {
        name?.let { general["name"] = it }
        useSimplifiedBreeding?.let { general["use_simplified_breeding"] = it }
        return general
    }
}

class GenericVars {
    val general = arrayListOf<Any>()

    /**
     * 1..n
     */
    fun genVar(value: GeneticVar.() -> Unit) {
        general.add(GeneticVar().apply(value).getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class GeneticVar {
    val general = mutableMapOf<String, Any>()

    /**
     * 0..1
     *
     * Event to run when this mob is created and matches the above allele conditions.
     */
    fun birthEvent(event: String, target: Subject? = null) {
        val data = mutableMapOf("event" to event)
        if(target != null)
            data["target"] = target.toString().lowercase()
        general["birth_event"] = data
    }

    /**
     * 0..1
     *
     * If this value is non-negative, compare the mob's main allele with this value for a match. Can also be a range of integers.
     */
    fun mainAllele(min: Int, max: Int = min) {
        general["main_allele"] = mutableMapOf("range_min" to min, "range_max" to max)
    }

    /**
     * 0..1
     *
     * If this value is non-negative, compare the mob's hidden allele with this value for a match. Can also be a range of integers.
     */
    fun hiddenAllele(min: Int, max: Int = min) {
        general["hidden_allele"] = mutableMapOf("range_min" to min, "range_max" to max)
    }

    /**
     * 0..1
     *
     * If this value is non-negative, compare both the mob's main and hidden alleles with this value for a match with either. Can also be a range of integers.
     */
    fun eitherAllele(min: Int, max: Int = min) {
        general["either_allele"] = mutableMapOf("range_min" to min, "range_max" to max)
    }

    fun getData(): MutableMap<String, Any> {
        return general
    }
}