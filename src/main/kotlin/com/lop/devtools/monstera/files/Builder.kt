package com.lop.devtools.monstera.files

import com.google.gson.GsonBuilder
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Path

fun getVersionAsString(version: Collection<Int>) = version.joinToString(".") { it.toString() }

object MonsteraBuilder {
    private val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create()

    /**
     * @param path to the build file
     * @param fileName the filename
     */
    fun buildTo(path: Path, fileName: String, data: Any, ignoreFileExt: Boolean = false): Path {
        val logger = LoggerFactory.getLogger("File Builder")

        var filename = fileName
        path.toFile().mkdirs()

        //check for json file format
        if (!fileName.endsWith(".json") && !ignoreFileExt) {
            filename = "$fileName.json"
        }

        val outputPath = path.resolve(filename)
        val outputFile = outputPath.toFile()

        if (fileName.length > 30) {
            logger.error("Filename '${fileName}' is too long!")
        }

        outputFile.createNewFile()
        outputFile.writeText(gson.toJson(data))
        return outputPath
    }

    /**
     * read a json file
     *
     * @param file the json file to read
     * @param targetObj indicates the type of the object
     * @return the specified class with the data defined in file as a Result
     */
    fun <T> readFrom(file: File, targetObj: Class<T>): Result<T> {
        try {
            val raw = file.readText()
            return Result.success(gson.fromJson(raw, targetObj))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}