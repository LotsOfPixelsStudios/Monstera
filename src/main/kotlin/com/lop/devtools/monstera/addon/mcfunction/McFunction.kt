package com.lop.devtools.monstera.addon.mcfunction

import org.slf4j.LoggerFactory

class McFunction(var name: String) {
    private val logger = LoggerFactory.getLogger("McFunction")
    var entries: ArrayList<String> = arrayListOf()
    var execEveryTick = false

    internal fun build(): McFunction {
        if (entries.isEmpty()) {
            logger.warn("Function $name has no body to execute")
        } else {
            entries.forEach {
                if (it.startsWith("/")) {
                    logger.warn("Function entry is in a command format, expected '' but found '/' ($it), removing /")
                    it.removePrefix("/")
                }
            }
        }

        //we can take for granted that name is not null as it should be the hashcode if not given
        if (!name.endsWith(".mcfunction")) {
            name += ".mcfunction"
        }

        return this
    }
}