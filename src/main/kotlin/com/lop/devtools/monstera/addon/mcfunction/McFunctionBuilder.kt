package com.lop.devtools.monstera.addon.mcfunction

import com.google.gson.GsonBuilder
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Path

/**
 * creates mc function files and overwrites tick.json
 */
fun buildMcFunctions(functionPath: Path, functions: MutableList<McFunction>) {
    val logger = LoggerFactory.getLogger("McFunction Builder")
    val tickFun = arrayListOf<String>()
    val funDir = functionPath.toFile()
    funDir.mkdir()

    functions.forEach {
        with(it.build()) {
            name?.let { fileName ->
                val target = File(funDir, fileName)
                entries.forEach { entry ->
                    target.appendText(entry + "\n")
                }
                if (execEveryTick)
                    tickFun.add(fileName.removeSuffix(".mcfunction"))
            }
        } ?: run {
            logger.warn("Found mc function with no name, can't build!")
        }
    }

    if (tickFun.isNotEmpty()) {
        val tickFile = File(funDir, "tick.json")
        val gson = GsonBuilder().setPrettyPrinting().create()
        tickFile.writeText(gson.toJson(mutableMapOf("values" to tickFun)))
    }
}