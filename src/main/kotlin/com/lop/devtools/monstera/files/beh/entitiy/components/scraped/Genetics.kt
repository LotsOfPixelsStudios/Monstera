package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject

class Genetics {
    @SerializedName("mutation_rate")
    @Expose
    var mutationRate: Number? = null
        

    @SerializedName("genes")
    @Expose
    var genesData: MutableList<Genes>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * genes {
     *     name = goat_variant
     *     useSimplifiedBreeding = true
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun genes(value: Genes.() -> Unit) {
        genesData = (genesData ?: mutableListOf()).also { it.add(Genes().apply(value)) }
    }

    class Genes {
        @SerializedName("name")
        @Expose
        var name: String? = null
            

        @SerializedName("use_simplified_breeding")
        @Expose
        var useSimplifiedBreeding: Boolean? = null
            

        @SerializedName("allele_range")
        @Expose
        var alleleRangeData: AlleleRange? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * alleleRange {
         *     rangeMin = 1
         *     rangeMax = 100
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun alleleRange(value: AlleleRange.() -> Unit) {
            alleleRangeData = (alleleRangeData ?: AlleleRange()).apply(value)
        }

        @SerializedName("genetic_variants")
        @Expose
        var geneticVariantsData: MutableList<GeneticVariants>? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * geneticVariants {
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun geneticVariants(value: GeneticVariants.() -> Unit) {
            geneticVariantsData =
                (geneticVariantsData ?: mutableListOf()).also { it.add(GeneticVariants().apply(value)) }
        }
    }

    class AlleleRange {

        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null
            

        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
            
    }

    class GeneticVariants {

        @SerializedName("main_allele")
        @Expose
        var mainAlleleData: MainAllele? = null
            @MonsteraBuildSetter set

        /**
         *
         * ```
         * mainAllele {
         *     rangeMin = 1
         *     rangeMax = 2
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun mainAllele(value: MainAllele.() -> Unit) {
            mainAlleleData = (mainAlleleData ?: MainAllele()).apply(value)
        }

        @SerializedName("birth_event")
        @Expose
        var birthEventData: BirthEvent? = null
            

        /**
         *
         * ```
         * birthEvent {
         *     event = minecraft:born_screamer
         *     target = self
         * }
         *```
         */
        @OptIn(MonsteraBuildSetter::class)
        @Components.VanillaComponentMarker
        fun birthEvent(value: BirthEvent.() -> Unit) {
            birthEventData = (birthEventData ?: BirthEvent()).apply(value)
        }
    }

    class MainAllele {

        @SerializedName("range_min")
        @Expose
        var rangeMin: Number? = null
            

        @SerializedName("range_max")
        @Expose
        var rangeMax: Number? = null
            
    }

    class BirthEvent {

        @SerializedName("event")
        @Expose
        var event: String? = null
            

        @SerializedName("target")
        @Expose
        var target: Subject? = null
            
    }
}
