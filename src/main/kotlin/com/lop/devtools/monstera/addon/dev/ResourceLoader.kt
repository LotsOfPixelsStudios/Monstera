package com.lop.devtools.monstera.addon.dev

import com.lop.devtools.monstera.addon.Addon
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import kotlin.io.path.Path


@Suppress("unused")
object ResourceLoader {
    fun getResourceAsStream(filePath: String): InputStream {
        return ResourceLoader::class.java.classLoader.getResourceAsStream(filePath)
            ?: throw FileNotFoundException("could not find $filePath")
    }

    fun getDefaultTexture(): File {
        val buildPath = Addon.active?.config?.buildPath ?: Path(
            System.getProperty("user.dir"),
            "build",
        )

        val tmp = buildPath.resolve("tmp").resolve("monstera").resolve("default_texture.png").toFile()
        if(!tmp.exists()) {
            tmp.parentFile.mkdirs()
            tmp.createNewFile()
            tmp.writeBytes(getResourceAsStream("default_texture.png").readBytes())
        }
        return tmp
    }

    fun getDefaultModel(): File {
        val buildPath = Addon.active?.config?.buildPath ?: Path(
            System.getProperty("user.dir"),
            "build",
        )

        val tmp = buildPath.resolve("tmp").resolve("monstera").resolve("default_model.geo.json").toFile()
        if(!tmp.exists()) {
            tmp.parentFile.mkdirs()
            tmp.createNewFile()
            tmp.writeBytes(getResourceAsStream("default_model.geo.json").readBytes())
        }
        return tmp
    }

    /**
     * load the file content from a resource stream
     *
     * @param resourcePath the resource to load, this may be only within the build files (library jars etc)
     * @param targetFile a copy to actually work with
     * @return returns the target file
     */
    fun loadResourceWithStreamTo(resourcePath: String, targetFile: File): File {
        targetFile.parentFile.mkdirs()
        targetFile.writeBytes(getResourceAsStream(resourcePath).readBytes())
        return targetFile
    }
}
