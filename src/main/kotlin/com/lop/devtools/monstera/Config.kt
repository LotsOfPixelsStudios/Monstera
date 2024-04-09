@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera

import com.google.gson.Gson
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
@Deprecated("Use a config file, this function does not correcly apply config data!",
    ReplaceWith("loadConfig()", "com.lop.devtools.monstera.Config")
)
fun config(projectName: String, data: Config.() -> Unit): Config {
    return Config(projectName).apply(data)
}

@ConfigDSL
fun loadConfig(
    conf: String = "${System.getProperty("user.dir")}/monstera.json",
    local: String = "${System.getProperty("user.dir")}/monstera-local.json"
) = loadConfig(File(conf), File(local))

@ConfigDSL
fun loadConfig(
    conf: File,
    local: File
): Result<Config> {
    if (!conf.exists() || !local.exists()) {
        return Result.failure(Error("Could not find config file!"))
    }

    try {
        val gson = Gson()
        val monsteraConfig: MonsteraConfig = gson.fromJson(conf.readText(), MonsteraConfig::class.java)
        val monsteraLocalConfig: MonsteraLocalConfig =
            gson.fromJson(local.readText(), MonsteraLocalConfig::class.java)

        val behPath = Path(
            System.getProperty("user.dir"),
            monsteraLocalConfig.buildPath,
            "development_behavior_packs",
            monsteraConfig.projectShort.uppercase() + "_BP"
        )
        val resPath = Path(
            System.getProperty("user.dir"),
            monsteraLocalConfig.buildPath,
            "development_resource_packs",
            monsteraConfig.projectShort.uppercase() + "_BP"
        )

        val config = Config(
            projectName = monsteraConfig.name,
            namespace = monsteraConfig.namespace,
            projectShort = monsteraConfig.projectShort,
            description = monsteraConfig.description,
            version = monsteraConfig.version,
            authors = monsteraConfig.authors,
            worldUUID = UUID.fromString(monsteraConfig.worldUUID),
            worldModuleUUID = UUID.fromString(monsteraConfig.worldModuleUUID),
            behUUID = UUID.fromString(monsteraConfig.behUUID),
            behModUUID = UUID.fromString(monsteraConfig.behModuleUUID),
            behScriptUUID = UUID.fromString(monsteraConfig.scriptUUID),
            resUUID = UUID.fromString(monsteraConfig.resUUID),
            resModUUID = UUID.fromString(monsteraConfig.resModuleUUID),
            targetMcVersion = monsteraConfig.targetMcVersion,
            behPath = behPath,
            resPath = resPath,

            comMojangPath = monsteraLocalConfig.minecraftDir?.let { Path(it) }
                ?: Path(
                    System.getenv("LOCALAPPDATA") ?: "",
                    "Packages",
                    "Microsoft.MinecraftUWP_8wekyb3d8bbwe",
                    "LocalState",
                    "games",
                    "com.mojang"
                ),
            paths = Config.AddonPaths(
                behBase = behPath,
                resBase = resPath,
                behEntity = behPath.resolve(monsteraConfig.minecraftPaths.behEntity),
                behAnimController = behPath.resolve(monsteraConfig.minecraftPaths.behAnimController),
                behAnim = behPath.resolve(monsteraConfig.minecraftPaths.behAnim),
                behRecipe = behPath.resolve(monsteraConfig.minecraftPaths.behRecipes),
                behBlocks = behPath.resolve(monsteraConfig.minecraftPaths.behBlocks),
                behItems = behPath.resolve(monsteraConfig.minecraftPaths.behItems),
                behSpawnRules = behPath.resolve(monsteraConfig.minecraftPaths.behSpawnRules),
                behTrading = behPath.resolve(monsteraConfig.minecraftPaths.behTrading),
                behTexts = behPath.resolve(monsteraConfig.minecraftPaths.behTexts),
                behScripts = behPath.resolve(monsteraConfig.minecraftPaths.behScripts),
                behMcFunction = behPath.resolve(monsteraConfig.minecraftPaths.behMcFunction),
                resAnim = resPath.resolve(monsteraConfig.minecraftPaths.resAnim),
                resItem = resPath.resolve(monsteraConfig.minecraftPaths.resItem),
                resModels = resPath.resolve(monsteraConfig.minecraftPaths.resModels),
                resMaterials = resPath.resolve(monsteraConfig.minecraftPaths.resMaterials),
                resParticles = resPath.resolve(monsteraConfig.minecraftPaths.resParticles),
                resRenderControllers = resPath.resolve(monsteraConfig.minecraftPaths.resRenderControllers),
                resSounds = resPath.resolve(monsteraConfig.minecraftPaths.resSounds),
                resTextures = resPath.resolve(monsteraConfig.minecraftPaths.resTextures),
                resAttachable = resPath.resolve(monsteraConfig.minecraftPaths.resAttachable),
                resTexts = resPath.resolve(monsteraConfig.minecraftPaths.resTexts),
            ),
            formatVersions = Config.FormatVersions(
                getVersionAsString(monsteraConfig.targetMcVersion),
                behEntity = monsteraConfig.minecraftFormatVersions.behEntitty,
                resEntity = monsteraConfig.minecraftFormatVersions.resEntity,
                resItem = monsteraConfig.minecraftFormatVersions.resItem,
                behItem = monsteraConfig.minecraftFormatVersions.behItem,
                behAnimation = monsteraConfig.minecraftFormatVersions.behAnim,
                behBlock = monsteraConfig.minecraftFormatVersions.behBlock,
                behRecipe = monsteraConfig.minecraftFormatVersions.behRecipe,
                behSpawnRules = monsteraConfig.minecraftFormatVersions.behSpawnRule,
                behAnimController = monsteraConfig.minecraftFormatVersions.behAnimController,
                resAnimController = monsteraConfig.minecraftFormatVersions.resAnimController,
                resAttachable = monsteraConfig.minecraftFormatVersions.resAttachable,
                resSoundDefs = monsteraConfig.minecraftFormatVersions.resSoundDefs,
                resRendercontroller = monsteraConfig.minecraftFormatVersions.resRenderController,
            )
        )
        return Result.success(config)
    } catch (e: Exception) {
        return Result.failure(e)
    }
}

class MonsteraConfig(
    var name: String,
    var projectShort: String,
    var description: String,
    var namespace: String,
    var version: MutableList<Int>,
    var authors: MutableList<String>,
    var worldUUID: String,
    var worldModuleUUID: String,
    var behUUID: String,
    var behModuleUUID: String,
    var scriptUUID: String,
    var resUUID: String,
    var resModuleUUID: String,
    var targetMcVersion: MutableList<Int>,
    var scriptingVersion: String,
    var minecraftPaths: MinecraftAddonPaths,
    var minecraftFormatVersions: MinecraftFormatVersions,
)

class MinecraftAddonPaths(
    var behEntity: String,
    var behAnimController: String,
    var behAnim: String,
    var behBlocks: String,
    var behItems: String,
    var lootTableEntity: String,
    var lootTableBlock: String,
    var behSpawnRules: String,
    var behTrading: String,
    var behRecipes: String,
    var behTexts: String,
    var behScripts: String,
    var behMcFunction: String,
    var resAnim: String,
    var resAnimController: String,
    var resEntity: String,
    var resItem: String,
    var resModels: String,
    var resMaterials: String,
    var resParticles: String,
    var resRenderControllers: String,
    var resSounds: String,
    var resTextures: String,
    var resAttachable: String,
    var resTexts: String,
)

class MinecraftFormatVersions(
    var behEntitty: String,
    var behItem: String,
    var behAnim: String,
    var behBlock: String,
    var behRecipe: String,
    var behSpawnRule: String,
    var behAnimController: String,
    var resEntity: String,
    var resItem: String,
    var resAnimController: String,
    var resAttachable: String,
    var resSoundDefs: String,
    var resRenderController: String,
)

class MonsteraLocalConfig(
    var projectPath: String?,
    var buildPath: String,
    var minecraftDir: String?
)

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
    var targetMcVersion: MutableList<Int> = arrayListOf(1, 20, 70),
    var formatVersions: FormatVersions = FormatVersions(getVersionAsString(targetMcVersion)),
    var langFileBuilder: AddonLangFileBuilders = AddonLangFileBuilders(behPath, resPath),
    var scriptEntryFile: File = File(),
    var scriptingVersion: String = "1.8.0"
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
        var behTrading: Path = behBase.resolve("trading").resolve("economy_trades"),
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
        var resEntity: String = "1.10.0",
        var resItem: String = "1.10.0",
        var behItem: String = "1.20.50",
        var behAnimation: String = "1.8.0",
        var behBlock: String = targetMcVersion,
        var behRecipe: String = "1.17.41",
        var behSpawnRules: String = "1.8.0",
        var behAnimController: String = "1.10.0",
        var resAnimController: String = "1.10.0",
        var resAttachable: String = "1.10.0",
        var resSoundDefs: String = "1.14.0",
        var resRendercontroller: String = "1.10.0"
    )
}
