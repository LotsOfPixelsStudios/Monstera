package com.lop.devtools.monstera.addon.dev

import com.google.gson.GsonBuilder
import com.lop.devtools.monstera.Config
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.nio.file.Path

fun buildToMcFolder(config: Config) = runBlocking {
    val logger = LoggerFactory.getLogger("Mc Directory Builder")

    if (!config.comMojangPath.toFile().exists()) {
        logger.error("MC folder not found, tried with path: ${config.comMojangPath}")
        return@runBlocking
    }

    val mojangBeh =
        config.comMojangPath.resolve("development_behavior_packs").resolve(config.projectShort.uppercase() + "_BP")
    val mojangRes =
        config.comMojangPath.resolve("development_resource_packs").resolve(config.projectShort.uppercase() + "_RP")

    val deleteBehJob = async {
        mojangBeh.toFile().deleteRecursively()
    }
    val deleteResJob = async {
        mojangRes.toFile().deleteRecursively()
    }
    deleteBehJob.join()
    deleteResJob.join()

    val buildBehJob = async {
        config.behPath.toFile().copyRecursively(mojangBeh.toFile(), true)
    }
    val buildResJob = async {
        config.resPath.toFile().copyRecursively(mojangRes.toFile(), true)
    }

    buildBehJob.join()
    buildResJob.join()

    if (config.world.exists()) {
        try {
            val worldFolderPath = config.comMojangPath.resolve("minecraftWorlds").resolve(config.projectShort + "_world")
            worldFolderPath.toFile().deleteRecursively()
            config.world.copyRecursively(worldFolderPath.toFile())

            addAddonDependenciesToWorldFolder(worldFolderPath, config)

        } catch (e: Exception) {
            logger.error("Couldn't push world to MC folder, restart Minecraft. Skipping world")
            return@runBlocking
        }
    }

    logger.trace("Project pushed to MC folder")
}

fun addAddonDependenciesToWorldFolder(worldPath: Path, properties: Config) {
    val gson = GsonBuilder().create()
    val dependOnBehPack = gson.toJson(
        arrayListOf(
            mutableMapOf(
                "pack_id" to properties.behUUID,
                "version" to properties.version
            )
        )
    )
    val dependOnResPack = gson.toJson(
        arrayListOf(
            mutableMapOf(
                "pack_id" to properties.resUUID,
                "version" to properties.version
            )
        )
    )
    worldPath.resolve("world_behavior_packs.json").toFile().writeText(dependOnBehPack)
    worldPath.resolve("world_resource_packs.json").toFile().writeText(dependOnResPack)
}