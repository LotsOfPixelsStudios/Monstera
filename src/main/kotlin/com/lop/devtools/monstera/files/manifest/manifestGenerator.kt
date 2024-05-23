package com.lop.devtools.monstera.files.manifest

import com.google.gson.GsonBuilder
import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.files.File
import com.lop.devtools.monstera.files.lang.LangFileBuilder
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*

/**
 * provide a Prop Obj, with the name of the Project and the Project Short name for the folders.
 * The Folders are also automatically generated if they need to be generated
 *
 * usage: if you want to set up a new Project with the manifest you don't have any problems
 *        if you want to overwrite manifests/lang files you have to delete them yourself
 */
fun generateManifest(
    version: MutableList<Int> = arrayListOf(1, 0, 0),
    config: Config,
    minEnginVersion: MutableList<Int> = config.targetMcVersion,
    scriptEntryFile: File = File()
): Pair<UUID, UUID> {
    val logger = LoggerFactory.getLogger("Manifest")
    val enableScripting = scriptEntryFile.exists() && scriptEntryFile.isFile

    if (enableScripting && config.targetMcVersion[0] == 1 && config.targetMcVersion[1] < 20) {
        logger.warn("Scripting is only available in version 1.20 or higher! Update your targetMcVersion!")
    }

    //Manifest
    val manifestBehMap = mutableMapOf<String, Any>()
    val manifestResMap = mutableMapOf<String, Any>()

    manifestBehMap["format_version"] = 2
    manifestBehMap["header"] = mutableMapOf(
        "name" to "pack.name",
        "description" to "pack.description",
        "uuid" to config.behUUID,
        "version" to version,
        "min_engine_version" to minEnginVersion
    )
    manifestBehMap["modules"] = mutableListOf(
        mutableMapOf(
            "type" to "data",
            "uuid" to config.behModUUID,
            "version" to arrayListOf(1, 0, 0)
        )
    ).apply {
        if (enableScripting) {
            add(
                mutableMapOf(
                    "type" to "script",
                    "uuid" to config.behScriptUUID,
                    "version" to arrayListOf(1, 0, 0),
                    "language" to "javascript",
                    "entry" to "scripts/${scriptEntryFile.name}"
                )
            )
        }
    }
    manifestBehMap["dependencies"] = mutableListOf(
        mutableMapOf(
            "uuid" to config.resUUID,
            "version" to version
        )
    ).apply {
        if (enableScripting) {
            logger.info("Scripting enabled with version '${config.scriptingVersion}'!")

            add(
                mutableMapOf(
                    "module_name" to "@minecraft/server",
                    "version" to config.scriptingVersion
                )
            )
        }
    }

    config.authors.add("Monstera by Lots of Pixels Studios")
    manifestBehMap["metadata"] = mutableMapOf(
        "authors" to config.authors,
    )

    manifestResMap["format_version"] = 2
    manifestResMap["header"] = mutableMapOf(
        "name" to "pack.name",
        "description" to "pack.description",
        "uuid" to config.resUUID,
        "version" to version,
        "min_engine_version" to minEnginVersion
    )
    manifestResMap["modules"] = arrayOf(
        mutableMapOf(
            "type" to "resources",
            "uuid" to config.resModUUID,
            "version" to arrayListOf(1, 0, 0)
        )
    )
    config.behPath.toFile().mkdirs()
    config.resPath.toFile().mkdirs()

    val gson = GsonBuilder().setPrettyPrinting().create()
    /*
    create the files
     */
    config.behPath.resolve("manifest.json").toFile().writeText(gson.toJson(manifestBehMap))
    config.resPath.resolve("manifest.json").toFile().writeText(gson.toJson(manifestResMap))

    //Lang Files
    generateLangFiles(config, config.langFileBuilder.addonBeh)
    generateLangFiles(config, config.langFileBuilder.addonRes)

    return Pair(config.resUUID, config.behUUID)
}

private fun generateLangFiles(properties: Config, builder: LangFileBuilder) {
    builder.add("pack.name", properties.projectName)
    builder.add("pack.description", properties.description)
}
