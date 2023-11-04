package com.lop.devtools.monstera.addon.entity.resource

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.dev.ResourceLoader
import java.io.File
import java.io.FileOutputStream

fun generateDefaultGeo(geo: String, config: Config): File {
    //load the default model
    val resourceStream = ResourceLoader.getResourceAsStream("default_model.geo.json")

    //check if it exists
    val gson = GsonBuilder().setPrettyPrinting().create()

    //create a target File
    val target = config.paths.resModels.resolve("entity").resolve("$geo.geo.json")
    target.parent.toFile().mkdirs()

    if (!target.toFile().exists())
        target.toFile().createNewFile()

    val targetOutputStream = FileOutputStream(target.toFile(), false)

    //put the resource input stream as the outputstream from the new file (copying it)
    targetOutputStream.use { fileOutputStream ->
        resourceStream.copyTo(fileOutputStream)
    }

    //open the target file with gson
    val jsonFile = Gson().fromJson(target.toFile().readText(), JsonObject::class.java)

    //update the identifier
    jsonFile
        .getAsJsonArray("minecraft:geometry")
        .get(0)
        .asJsonObject
        .getAsJsonObject("description")
        .addProperty("identifier", geo)

    target.toFile().writeText(
        gson.toJson(jsonFile)
    )
    return target.toFile()
}

fun copyDefaultTextureTo(config: Config, defaultFileName: String = "default_texture.png") {
    val resourceStream = ResourceLoader.getResourceAsStream("default_texture.png")

    //target File
    val target = config.paths.resTextures.resolve(defaultFileName).toFile()

    //check if def texture exsit and
    if (!target.exists()) {
        target.parentFile.mkdirs()
        target.createNewFile()
        val targetOutputStream = FileOutputStream(target, false)
        targetOutputStream.use { fileOutputStream ->
            resourceStream.copyTo(fileOutputStream)
        }
    }
}
