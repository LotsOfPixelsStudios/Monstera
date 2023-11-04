package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import org.slf4j.LoggerFactory

class ComponentAttackDamage : MonsteraFile {
    override val unsafe = Unsafe()
    private val logger = LoggerFactory.getLogger("Component Attack Damage")

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            if (!iKnowWhatImDoing)
                logger.warn("You probably mean attack { } not attackDamage { }. set 'iKnowWhatImDoing = true' to remove this warning!")
            value?.let { general["value"] = it }
            return general
        }
    }

    var value: Int? = null
    var iKnowWhatImDoing = false
}