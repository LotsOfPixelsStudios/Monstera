package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.getMonsteraLogger
import kotlin.system.measureTimeMillis

@DslMarker
annotation class AddonEntry

class BasicAddon(config: Config, args: Array<String>) : Addon(config, args) {
    init {
        active = this
    }
}


@AddonEntry
fun addon(config: Config, args: Array<String> = arrayOf(), addon: Addon.() -> Unit): Config {
    val logger = getMonsteraLogger("Monstera")
    logger.info("Building Addon ...")
    val buildTime = measureTimeMillis {
        BasicAddon(config, args).apply(addon).build()
    }
    logger.info("Finished building Addon in ${buildTime / 1000}.${buildTime % 1000}s")
    return config
}

@AddonEntry
fun addon(projectName: String, args: Array<String> = arrayOf(), conf: Config.() -> Unit = {}, addon: Addon.() -> Unit): Config {
    return addon(Config(projectName).apply(conf), args, addon)
}

@AddonEntry
fun testAddon(addon: Addon.() -> Unit) {
    (Addon.active ?: BasicAddon(Config("test_prj"), arrayOf())).apply { buildToMcFolder = false }.apply(addon)
}

fun buildTestAddon() {
    Addon.active?.build() ?: run {
        getMonsteraLogger("Test Addon").warn("No Test Addon initilized!")
    }
}