package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.Config

@DslMarker
annotation class AddonEntry

class BasicAddon(config: Config) : AddonImpl(config) {
    init {
        Addon.active = this
    }
}


@AddonEntry
fun addon(config: Config, addon: Addon.() -> Unit): Config {
    BasicAddon(config).apply(addon).build()
    return config
}

@AddonEntry
fun addon(projectName: String, conf: Config.() -> Unit = {}, addon: Addon.() -> Unit): Config {
    return addon(Config(projectName).apply(conf), addon)
}
