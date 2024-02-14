package com.lop.devtools.monstera.files

import com.google.gson.GsonBuilder
import org.slf4j.LoggerFactory
import java.nio.file.Path

fun getVersionAsString(version: Collection<Int>) = version.joinToString(".") { it.toString() }

object MonsteraBuilder {
    /**
     * @param path to the build file
     * @param fileName the filename
     */
    fun buildTo(path: Path, fileName: String, data: Any, ignoreFileExt: Boolean = false) {
        val logger = LoggerFactory.getLogger("File Builder")

        var filename = fileName
        path.toFile().mkdirs()

        //check for json file format
        if (!fileName.endsWith(".json") && !ignoreFileExt) {
            filename = "$fileName.json"
        }

        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create()
        val outputFile = path.resolve(filename).toFile()

        if (fileName.length > 30) {
            logger.error("Filename '${fileName}' is too long!")
        }

        outputFile.createNewFile()
        outputFile.writeText(gson.toJson(data))
    }
}