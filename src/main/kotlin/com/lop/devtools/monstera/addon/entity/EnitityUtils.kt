package com.lop.devtools.monstera.addon.entity

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.lop.devtools.monstera.getMonsteraLogger
import java.io.File
import kotlin.math.abs

/**
 * return the geometry id from a given json file, empty if parser failed
 */
fun getGeoId(file: File): String {
    fun logger() = getMonsteraLogger("Geometry")
    val jsonString = file.readText()
    val geoFile = Gson().fromJson(jsonString, JsonObject::class.java)

    try {
        val gen = geoFile.getAsJsonArray("minecraft:geometry")

        if(gen == null) {
            val entries = geoFile.entrySet()
            entries.forEach {
                if(it.key.contains("geometry.")) {
                    return it.key
                }
            }
        } else {
            //older format version
            for (id in 0 until gen.size()) {
                val description = gen.get(id).asJsonObject
                return description
                    .getAsJsonObject("description")
                    .getAsJsonPrimitive("identifier")
                    .asString

            }
        }
    } catch (e: Exception) {
        logger().error("No geometry identifier found in file: ${file.name}!")
    }
    return ""
}

/**
 * extract all animation identifier from a given animation file (resource)
 */
fun extractAnimationIdsFromFile(file: File): ArrayList<String> {
    val entries = arrayListOf<String>()
    val jsonString = file.readText()
    val geoFile = Gson().fromJson(jsonString, JsonObject::class.java)

    val animations = geoFile.getAsJsonObject("animations")
    animations.entrySet().forEach {
        entries.add(it.key)
    }
    return entries
}

/**
 * hashes the name of a layer, used within the textures/geometries to get a unique layer name
 */
fun hashLayerName(files: ArrayList<File>, query: String): String {
    var hash = 0
    files.forEach { hash += it.name.hashCode() }
    hash += query.hashCode()
    return "layer_${abs(hash)}"
}

/**
 * hashes the name of a layer, used within the textures/geometries to get a unique layer name
 */
fun hashLayerNameByIds(ids: ArrayList<String>, query: String): String {
    var hash = 0
    ids.forEach { hash += it.hashCode() }
    hash += query.hashCode()
    return "layer_${abs(hash)}"
}