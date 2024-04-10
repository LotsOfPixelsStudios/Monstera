package com.lop.devtools.monstera.files

import com.lop.devtools.monstera.addon.Addon
import java.io.File
import java.io.FileNotFoundException
import java.nio.ByteBuffer
import java.nio.file.Path
import java.util.*

/**
 * load a directory or a file from the resource directory
 * save to use in pipelines
 *
 * @param path the relative path within the resource directory
 * @sample getResourceSample
 */
fun getResource(path: String): File {
    return File(
        Thread.currentThread().contextClassLoader?.getResource(path)?.path
            ?: throw FileNotFoundException("This File (with path: '$path') does not exist, check spelling!")
    )
}

private fun getResourceSample() {
    getResource("entity/default_texture.png")
}

fun getValueForLangKey(lanFile: File, key: String): Result<String> {
    if (lanFile.exists()) {
        val data = lanFile.readText()
        data.split("\n").forEach {
            if (it.contains(key)) {
                return Result.success(
                    it.removePrefix("$key=").replace("\n", "").replace("\r", "")
                )
            }
        }
        return Result.failure(Error("lang key does not exist"))
    }
    return Result.failure(Error("lang file does not exist"))
}

/**
 * @param path the system path to the file
 * @param to retrive the relative path to this keyword, eg: "C:\...\res\texture\entity\test.png" and "texture" - would return "texture/entity/test.png"
 */
fun resolveRelativePath(path: Path, to: String): String {
    val sub = path.toString().split(to).last().replace("\\", "/")
    return "$to$sub"
}

/**
 * @return the file name with a unique hash, if the file is the same, the return value is the same
 */
fun getUniqueFileName(file: File): String {
    val hash = file.path.split("resource").last().hashCode()
    val buff = ByteBuffer.allocate(Int.SIZE_BYTES).putInt(hash).array()
    val enc = Base64
        .getEncoder()
        .encodeToString(buff)
        .replace("=", "")
        .replace("+", "")
        .replace("/", "")
    return enc + "_" + file.name
}

fun sanetiseFilename(filename: String, fileSuffix: String): String {
    var sanFile = filename
        .removeSuffix(".$fileSuffix")
        .replace("-", "_")
        .replace(" ", "_")
    if(Addon.active?.config?.hashFileNames == true) {
        sanFile = getUniqueBuildFileName(Addon.active!!.config.namespace, sanFile)
    }
    return "${sanFile}.$fileSuffix"
}

/**
 * create a hash out of the namespace and fileName and append it to the fileName
 * @param namespace the namespace of the project
 * @param filename the file name to make unique
 * @return a unique fileName to the namespace and fileName, this might be required if multiple addons are loaded with diffrent namespaces but same file names
 */
fun getUniqueBuildFileName(namespace: String, filename: String): String {
    val hash = namespace.hashCode() + filename.hashCode()
    val buff = ByteBuffer.allocate(Int.SIZE_BYTES).putInt(hash).array()
    val enc = Base64
        .getEncoder()
        .encodeToString(buff)
        .replace("=", "")
        .replace("+", "")
        .replace("/", "")
    return "${filename}_$enc"
}

fun File(vararg parents: String): File {
    return File(parents.joinToString(separator = File.separator))
}

fun File.createWithDirs(): File {
    this.parentFile.mkdirs()
    return this
}
