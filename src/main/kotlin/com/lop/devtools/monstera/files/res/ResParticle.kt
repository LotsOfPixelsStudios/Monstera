package com.lop.devtools.monstera.files.res

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.lop.devtools.monstera.addon.Addon
import org.slf4j.LoggerFactory
import java.io.File

fun particleTextureList(addon: Addon) {
    val particleDir = addon.config.paths.resParticles.toFile()

    if(particleDir.exists() && particleDir.isDirectory) {
        particleDir.walk().filter { it.isFile }.forEach {
            particleGetTexturePath(it).onSuccess { path ->
                TextureIndex.instance(addon).textures.add(path)
            }
        }
    }
}

fun particleGetTexturePath(file: File): Result<String> {
    val logger = LoggerFactory.getLogger("Particle")
    val jsonString = file.readText()
    val particleFile = Gson().fromJson(jsonString, JsonObject::class.java)

    return try {
        Result.success(particleFile
            .getAsJsonObject("particle_effect")
            .getAsJsonObject("description")
            .getAsJsonObject("basic_render_parameters")
            .get("texture").asString)

    } catch (e: NullPointerException) {
        logger.warn("${file.absolutePath} has no texture / texture path")
        Result.failure(e)
    }
}
