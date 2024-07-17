package com.lop.devtools.monstera.addon

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.getMonsteraLogger
import java.io.File
import java.nio.file.Path

class TestAddon(config: Config, args: Array<String>) : Addon(config, args) {
    private val fileChecks = mutableMapOf<Path, TestFileContent.() -> Unit>()

    companion object {
        var activeTestAddon: TestAddon? = null
    }

    init {
        active = this
        activeTestAddon = this
    }

    /**
     * implement checks for a json file
     * @param checks these checks are executed after the addon was built
     */
    fun withJsonFile(filePath: Path, checks: TestFileContent.() -> Unit) {
        fileChecks[filePath] = checks
    }

    override fun build() {
        super.build()
        val logger = getMonsteraLogger("File Checker")
        fileChecks.forEach { (filePath, checks) ->
            val file = filePath.toFile()
            logger.info("Checking file ${file.name}")
            if (!file.exists()) {
                logger.warn("File ${file.name} does not exits (${file.path})")
                return@forEach
            }
            checks(TestFileContent(filePath.toFile()))
        }
    }

    class TestFileContent(file: File) {
        private val contents: JsonElement = Gson().fromJson(file.readText(), JsonElement::class.java)

        fun containsKey(key: String, ele: JsonElement = contents): Boolean {
            if (ele.isJsonObject && ele.asJsonObject.keySet().contains(key))
                return true
            if (ele.isJsonObject)
                return ele.asJsonObject.asMap().any { (_, obj) ->
                    containsKey(key, obj)
                }
            if (ele.isJsonArray)
                return ele.asJsonArray.any {
                    containsKey(key, it)
                }
            return false
        }

        fun containsKeyValue(key: String, value: Any, ele: JsonElement = contents): Boolean {
            if (ele.isJsonObject && ele.asJsonObject.has(key))
                return ele.asJsonObject[key].equals(value)
            if (ele.isJsonObject)
                return ele.asJsonObject.asMap().any { (_, obj) ->
                    containsKeyValue(key, value, obj)
                }
            if (ele.isJsonArray)
                return ele.asJsonArray.any {
                    containsKeyValue(key, value, it)
                }
            return false
        }

        fun containsKeyChain(vararg keys: String, currEle: JsonElement = contents) =
            containsKeyChain(keys.toMutableList(), currEle)

        private fun containsKeyChain(keys: MutableList<String>, currEle: JsonElement = contents): Boolean {
            if (keys.isEmpty()) {
                return true
            }
            if (currEle.isJsonArray) {
                return currEle.asJsonArray.any {
                    containsKeyChain(keys, it)
                }
            }
            val currentKey = keys[0]
            if (currEle.isJsonObject && currEle.asJsonObject.has(currentKey)) {
                keys.removeFirst()
                return containsKeyChain(keys, currEle.asJsonObject[currentKey])
            }

            return false
        }

        fun containsKeyChainValue(value: Any, vararg keys: String) =
            containsKeyChainValue(value, keys.toMutableList(), contents)

        private fun containsKeyChainValue(value: Any, keys: MutableList<String>, currEle: JsonElement): Boolean {
            if (keys.isEmpty()) {
                if(currEle.isJsonPrimitive) {
                    return currEle.asJsonPrimitive.asString == value.toString()
                }
                return currEle == value
            }
            if (currEle.isJsonArray) {
                return currEle.asJsonArray.any {
                    containsKeyChainValue(value, keys, it)
                }
            }
            val currentKey = keys[0]
            if (currEle.isJsonObject && currEle.asJsonObject.has(currentKey)) {
                val ret = ArrayList(keys)
                ret.removeFirst()
                return containsKeyChainValue(value, ret, currEle.asJsonObject[currentKey])
            }

            return false
        }
    }
}

@AddonEntry
fun testAddon(addon: TestAddon.() -> Unit) {
    (TestAddon.activeTestAddon ?: TestAddon(Config("test_prj"), arrayOf())).apply { buildToMcFolder = false }
        .apply(addon)
}

fun buildTestAddon() {
    Addon.active?.build() ?: run {
        getMonsteraLogger("Test Addon").warn("No Test Addon initialized!")
    }
}