package com.lop.devtools.monstera.files.res.sounds

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraBuildableFile
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.files.MonsteraListFileTypeAdapter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.getMonsteraLogger
import java.lang.Error
import java.nio.file.Path

class ResSoundDefs : MonsteraBuildableFile, MonsteraRawFile() {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {
        val selPath = path ?: Addon.active?.config?.paths?.resSounds ?: run {
            getMonsteraLogger(this.javaClass.name).error("Could not Resolve a path for sound def file as no addon was initialized!")
            return Result.failure(Error("Could not Resolve a path for sound def file as no addon was initialized!"))
        }

        Addon.active?.config?.formatVersions?.resSoundDefs?.let {
            formatVersion = it
        }
        version?.let {
            formatVersion = it
        }

        val target = MonsteraBuilder.buildTo(
            selPath,
            "sound_definitions.json",
            this
        )
        return Result.success(target)
    }

    @SerializedName("format_version")
    @Expose
    var formatVersion: String = "1.14.0"

    @SerializedName("sound_definitions")
    @Expose
    @JsonAdapter(MonsteraListFileTypeAdapter::class)
    var soundDefinitions: MutableMap<String, ResSoundDef> = mutableMapOf()
        @MonsteraBuildSetter set

    /**
     * 0..1
     *
     * ```
     * newSoundDef("tp.test.drive") {
     *     category(SoundCategory.BLOCK)
     *     maxDistance(2f)
     *     minDistance(1f)
     *     //can be called multiple times
     *     sound {
     *         name("sounds/test/test1")
     *         volume(1f)
     *         pitch(1f)
     *     }
     *     sound("sounds/test/test")
     *     sound("sounds/test/test2")
     * }
     * ```
     *
     * @param soundIdentifier like "music.cd.game"
     * @param settings
     */
    fun newSoundDef(soundIdentifier: String, settings: ResSoundDef.() -> Unit) {
        if(soundDefinitions.containsKey(soundIdentifier)) {
            soundDefinitions[soundIdentifier]!!.apply(settings)
        } else {
            soundDefinitions[soundIdentifier] = ResSoundDef().apply(settings)
        }
    }
}