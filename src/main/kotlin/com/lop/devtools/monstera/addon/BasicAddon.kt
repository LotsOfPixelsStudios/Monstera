package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.getMonsteraLogger
import kotlin.system.measureTimeMillis

@DslMarker
annotation class AddonEntry

class BasicAddon(config: Config) : Addon(config) {
    init {
        active = this
    }
}


@AddonEntry
fun addon(config: Config, addon: Addon.() -> Unit): Config {
    val logger = getMonsteraLogger("Monstera")
    logger.info("Building Addon ...")
    val buildTime = measureTimeMillis {
        BasicAddon(config).apply(addon).build()
    }
    logger.info("Finished building Addon in ${buildTime / 1000}.${buildTime % 1000}s")
    return config
}

@AddonEntry
fun addon(projectName: String, conf: Config.() -> Unit = {}, addon: Addon.() -> Unit): Config {
    return addon(Config(projectName).apply(conf), addon)
}
