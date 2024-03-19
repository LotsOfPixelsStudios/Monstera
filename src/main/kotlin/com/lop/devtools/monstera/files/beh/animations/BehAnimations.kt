package com.lop.devtools.monstera.files.beh.animations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.getMonsteraLogger
import java.lang.Error
import java.nio.file.Path

class BehAnimations : MonsteraBuildableFile, MonsteraRawFile() {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val sanFile = filename
            .removeSuffix(".json")
            .replace("-", "_")
            .replace(" ", "_")
        version?.let { formatVersion = it }

        val buildPath = path ?: Addon.active?.config?.paths?.behAnim ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for animation file '$sanFile' as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for animation file '$sanFile' as no addon was initialized!"))
        }

        val target = MonsteraBuilder.buildTo(buildPath, "$sanFile.json", this)
        return Result.success(target)
    }

    /**
     * returns true if no animations where defined
     */
    fun isEmpty() = animData.isNullOrEmpty()

    @SerializedName("format_version")
    @Expose
    var formatVersion: String = Addon.active?.config?.formatVersions?.behAnimation ?: "1.8.0"

    @SerializedName("animations")
    @Expose
    var animData: MutableMap<String, BehAnimation>? = null
        @MonsteraBuildSetter set

    /**
     * 1..*
     *
     * @param name: name of the animation, naming convention: "animation.<entityName>.<animName>"
     * notice: you can leave out "animation." you only have to put <entityName>.<animName>
     * @sample sample
     */
    @OptIn(MonsteraBuildSetter::class)
    fun animation(name: String, settings: BehAnimation.() -> Unit) {
        val key = if (!name.contains("animation.")) {
            "animation.$name"
        } else {
            name
        }
        animData = (animData ?: mutableMapOf()).also {
            it[key]?.apply(settings) ?: run {
                it[key] = BehAnimation().apply(settings)
            }
        }
    }

    /**
     * Sample
     */
    private fun sample() {
        animation("animation.player.timer_backpack") {
            timeline { /*...*/ }
            animLength = 0.2f
        }
    }
}