package com.lop.devtools.monstera.files.beh.entitiy.description

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.DebugMarker
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.properties.EntityProperties
import com.lop.devtools.monstera.files.properties.types.GenericProperty
import com.lop.devtools.monstera.getMonsteraLogger

class BehEntityDescription {
    @SerializedName("identifier")
    @Expose
    var identifier: String? = null

    @SerializedName("is_spawnable")
    @Expose
    var isSpawnable: Boolean = true

    @SerializedName("is_summonable")
    @Expose
    var isSummonable: Boolean = true

    @SerializedName("is_experimental")
    @Expose
    var isExperimental: Boolean = false

    @SerializedName("runtime_identifier")
    @Expose
    var runtimeIdentifier: RuntimeIdentifier? = null

    @SerializedName("scripts")
    @Expose
    var scriptsData: BehEntityDescScripts? = null
        @MonsteraBuildSetter set

    /**
     * 0..1
     *
     * activate animations defined in animations()
     * @sample sampleScript
     */
    @OptIn(MonsteraBuildSetter::class)
    fun scripts(settings: BehEntityDescScripts.() -> Unit) {
        scriptsData = (scriptsData ?: BehEntityDescScripts()).apply(settings)
    }

    @SerializedName("animations")
    @Expose
    var animationData: MutableMap<String, String>? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun addAnimation(animName: String, animIdentifier: String) {
        animationData = (animationData ?: mutableMapOf()).also {
            it[animName] = animIdentifier
        }
    }

    @SerializedName("properties")
    @Expose
    var propertyData: MutableMap<String, GenericProperty<*>>? = null
        @MonsteraBuildSetter set

    /**
     * set new properties to an entity
     *
     * ```
     * enum("name") { }
     * bool("name") { }
     * int("name") { }
     * float("name") { }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun properties(data: EntityProperties.() -> Unit) {
        propertyData = (propertyData ?: mutableMapOf()).apply { putAll(EntityProperties().apply(data).propertyData) }
    }

    private fun sampleScript() {
        scripts {
            animate(arrayListOf("testCon"))
        }
    }

    @DebugMarker
    fun debugLogProperties() {
        propertyData?.filter { it.value.default == null }?.forEach { (k, _) ->
            getMonsteraLogger(this.javaClass.name).warn("No default value for property '$k' given!")
        }
        propertyData?.forEach {
            it.value.propertySpecificDebug()
        }
    }
}