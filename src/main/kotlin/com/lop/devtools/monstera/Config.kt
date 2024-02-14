package com.lop.devtools.monstera

import com.lop.devtools.monstera.addon.dev.ResourceLoader
import com.lop.devtools.monstera.addon.entity.resource.copyDefaultTextureTo
import com.lop.devtools.monstera.addon.entity.resource.generateDefaultGeo
import com.lop.devtools.monstera.files.File
import com.lop.devtools.monstera.files.getVersionAsString
import com.lop.devtools.monstera.files.lang.LangFileBuilder
import java.io.File
import java.nio.file.Path
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.io.path.Path

@DslMarker
annotation class ConfigDSL

@ConfigDSL
fun config(projectName: String, data: Config.() -> Unit): Config {
    return Config(projectName).apply(data)
}

class Config(
    val projectName: String,
    var namespace: String = "monstera",
    var projectShort: String = projectName.take(2).lowercase(),
    var description: String = "",
    var version: MutableList<Int> = mutableListOf(0, 0, 1),
    var authors: MutableList<String> = mutableListOf(),
    var world: File = File(),
    var resPath: Path = Path("build", "development_resource_packs", projectShort.uppercase() + "_RP"),
    var behPath: Path = Path("build", "development_behavior_packs", projectShort.uppercase() + "_BP"),
    var uuidSalt: String = "",
    val worldUUID: UUID = UUID.nameUUIDFromBytes("$projectName-world$uuidSalt".toByteArray())!!,
    var worldModuleUUID: UUID = UUID.nameUUIDFromBytes("$projectName-world-module$uuidSalt".toByteArray())!!,
    var behUUID: UUID = UUID.nameUUIDFromBytes("$projectName-beh$uuidSalt".toByteArray()),
    var behModUUID: UUID = UUID.nameUUIDFromBytes("$projectName-beh-mod$uuidSalt".toByteArray()),
    var behScriptUUID: UUID = UUID.nameUUIDFromBytes("$projectName-beh-mod$uuidSalt".toByteArray()),
    var resUUID: UUID = UUID.nameUUIDFromBytes("$projectName-res$uuidSalt".toByteArray()),
    var resModUUID: UUID = UUID.nameUUIDFromBytes("$projectName-res-mod$uuidSalt".toByteArray()),
    var comMojangPath: Path = Path(
        System.getenv("LOCALAPPDATA") ?: "",
        "Packages",
        "Microsoft.MinecraftUWP_8wekyb3d8bbwe",
        "LocalState",
        "games",
        "com.mojang"
    ),
    var paths: AddonPaths = AddonPaths(behPath, resPath),
    var targetMcVersion: ArrayList<Int> = arrayListOf(1, 19, 40),
    var formatVersions: FormatVersions = FormatVersions(getVersionAsString(targetMcVersion)),
    var langFileBuilder: AddonLangFileBuilders = AddonLangFileBuilders(behPath, resPath),
    var scriptEntryFile: File = File(),
    var scriptingVersion: String = "1.6.0"
) {
    init {
        behPath.toFile().deleteRecursively()
        resPath.toFile().deleteRecursively()
    }

    val defaultResource: DefaultResources = DefaultResources(this)

    val monsteraVersion: String by lazy {
        val resourceStream = ResourceLoader.getResourceAsStream("monstera_version")
        String(resourceStream.readAllBytes()).removeSuffix("\n").removeSuffix("\r") //just in case a \r is there
    }

    var packIcon: File = File()
        set(value) {
            val behIco = behPath.resolve("pack_icon.png").toFile()
            val resIco = resPath.resolve("pack_icon.png").toFile()
            value.copyTo(behIco, true)
            value.copyTo(resIco, true)

            field = value
        }

    fun String.runCommand(workingDir: File) {
        ProcessBuilder(*split(" ").toTypedArray())
            .directory(workingDir)
            .start()
            .waitFor(60, TimeUnit.SECONDS)
    }

    class AddonPaths(
        behBase: Path,
        resBase: Path,
        var behEntity: Path = behBase.resolve("entities"),
        var behAnimController: Path = behBase.resolve("animation_controllers"),
        var behAnim: Path = behBase.resolve("animations"),
        var behBlocks: Path = behBase.resolve("blocks"),
        var behItems: Path = behBase.resolve("items"),
        var lootTableEntity: Path = behBase.resolve("loot_tables").resolve("entities"),
        var lootTableBlock: Path = behBase.resolve("loot_tables").resolve("blocks"),
        var behSpawnRules: Path = behBase.resolve("spawn_rules"),
        var behTrading: Path = behBase.resolve("trading"),
        var behRecipe: Path = behBase.resolve("recipes"),
        var behTexts: Path = behBase.resolve("texts"),
        var behScripts: Path = behBase.resolve("scripts"),
        var behMcFunction: Path = behBase.resolve("functions"),
        var resAnim: Path = resBase.resolve("animations"),
        var resAnimController: Path = resBase.resolve("animation_controllers"),
        var resEntity: Path = resBase.resolve("entity"),
        var resItem: Path = resBase.resolve("items"),
        var resModels: Path = resBase.resolve("models"),
        var resMaterials: Path = resBase.resolve("materials"),
        var resParticles: Path = resBase.resolve("particles"),
        var resRenderControllers: Path = resBase.resolve("render_controllers"),
        var resSounds: Path = resBase.resolve("sounds"),
        var resTextures: Path = resBase.resolve("textures"),
        var resAttachable: Path = resBase.resolve("attachables"),
        var resTexts: Path = resBase.resolve("texts")
    )

    class AddonLangFileBuilders(
        behBase: Path,
        resBase: Path,
        var addonRes: LangFileBuilder = LangFileBuilder().config { textDir = resBase.resolve("texts").toFile() },
        var addonBeh: LangFileBuilder = LangFileBuilder().config { textDir = behBase.resolve("texts").toFile() }
    )

    class DefaultResources(parent: Config) {
        val defaultGeo: String by lazy {
            parent.paths.resModels.toFile().mkdirs()
            generateDefaultGeo("geometry.default", parent)
            "geometry.default"
        }
        val defaultTexturePath: String by lazy {
            parent.paths.resTextures.toFile().mkdirs()
            copyDefaultTextureTo(parent, "default.png")
            "textures/default"
        }
    }

    class FormatVersions(
        targetMcVersion: String,
        var behEntity: String = targetMcVersion,
        var resEntity: String = "1.8.0",
        var behItem: String = targetMcVersion,
        var behAnimation: String = "1.8.0"
    )
}

