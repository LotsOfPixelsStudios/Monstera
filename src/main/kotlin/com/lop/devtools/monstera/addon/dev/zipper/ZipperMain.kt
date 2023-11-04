package com.lop.devtools.monstera.addon.dev.zipper

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.dev.addAddonDependenciesToWorldFolder
import com.lop.devtools.monstera.files.File
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.io.path.Path

fun zipWorld(
    config: Config,
    targetName: String = config.projectName
) {
    val zipVersions = File("zipVersions")
    zipVersions.mkdir()
    //copy resource + behavior in world
    pushToWorldFile(config, config.world)
    addAddonDependenciesToWorldFolder(config.world.toPath(), config)

    Zipper.zipDirectory(config.world, File(zipVersions, "$targetName.mcworld"))
}

fun zipAddon(targetName: String, config: Config) {
    val logger = LoggerFactory.getLogger("Zip Addon")

    val zipVersions = File("zipVersions")
    zipVersions.mkdir()

    val targetN = "$targetName.mcaddon"

    //clean
    val targetZip = File(zipVersions, targetN)
    targetZip.delete()

    val path = "build/mcaddon"
    val buildDir = File(path)
    if (buildDir.exists()) {
        buildDir.deleteRecursively()
    }
    buildDir.mkdir()

    val behBuildDir = File(
        "build",
        "mcaddon",
        "behavior_packs",
        config.projectShort.uppercase() + "_BP"
    )
    val resBuildDir = File(
        "build",
        "mcaddon",
        "resource_packs",
        config.projectShort.uppercase() + "_RP"
    )
    behBuildDir.mkdir()
    resBuildDir.mkdir()

    config.behPath.toFile().copyRecursively(behBuildDir)
    config.resPath.toFile().copyRecursively(resBuildDir)

    Zipper.zipDirectory(inputDirectory = buildDir, outputZipFile = targetZip)
    logger.trace("Created mcaddon file ${targetZip.name}")
}

fun pushToWorldFile(config: Config, mcWorldFile: File) {
    val logger = LoggerFactory.getLogger("World Builder")

    val worldBehPath = Path("behavior_packs", config.projectShort.uppercase() + "_BP")
    val worldResPath = Path("resource_packs", config.projectShort.uppercase() + "_RP")

    val targetBehFile = mcWorldFile.toPath().resolve(worldBehPath).toFile()
    val targetResFile = mcWorldFile.toPath().resolve(worldResPath).toFile()

    targetBehFile.deleteRecursively()
    targetResFile.deleteRecursively()

    config.behPath.toFile().copyRecursively(targetBehFile)
    config.resPath.toFile().copyRecursively(targetResFile)
    logger.trace("Added addon to world file")
}