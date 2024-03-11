@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.lang

import com.google.gson.GsonBuilder
import com.lop.devtools.monstera.addon.Addon
import java.io.File

class LangFileBuilder {
    private val config = Config()
    val keys = arrayListOf<LangKey>()

    /**
     * modify the target & languages
     * ```
     * config {
     *     textDir = File("")
     *     languages = arrayListOf("en_US") //(default)
     * }
     * ```
     */
    fun config(data: Config.() -> Unit): LangFileBuilder {
        config.apply(data)
        return this
    }

    fun sort(): LangFileBuilder {
        keys.sortBy { it.key }
        return this
    }

    fun add(key: String, value: String, languageHint: String? = null): LangFileBuilder {
        keys.add(LangKey(key, value, languageHint))
        return this
    }

    fun addOrReplace(key: String, value: String, languageHint: String? = null): LangFileBuilder {
        keys
            .find { it.key == key && it.languageHint == languageHint }
            ?.run { this.value = value }
            ?: add(key, value, languageHint)
        return this
    }

    /**
     * iterates all lang keys and formats them to a .lang file
     * @param language only put in the lang keys of this specific language, null for all lang keys
     */
    fun getAsString(language: String? = null): String {
        val strBuilder = StringBuilder()
        keys.forEach {
            if (language == null || it.languageHint == null || language == it.languageHint) {
                strBuilder.append("${it.key}=${it.value}\n")
            }
        }
        strBuilder.removeSuffix("\n")
        return strBuilder.toString()
    }

    fun build() {
        config.textDir.mkdirs()
        config.languages.forEach {
            val target = File(config.textDir, "$it.lang")
            target.createNewFile()
            target.writeText(getAsString(it))
        }
        val def = File(config.textDir, "languages.json")
        def.createNewFile()
        def.writeText(
            GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(config.languages)
        )
    }

    inner class Config {
        lateinit var textDir: File
        var languages: ArrayList<String> = arrayListOf("en_US")

        fun putAllLanguages() {
            languages = arrayListOf(
                "en_US", "en_GB", "de_DE", "es_ES", "es_MX", "fr_FR",
                "fr_CA", "it_IT", "ja_JP", "ko_KR", "pt_BR", "pt_PT",
                "ru_RU", "zh_CN", "zh_TW", "nl_NL", "bg_BG", "cs_CZ",
                "da_DK", "el_GR", "fi_FI", "hu_HU", "id_ID", "nb_NO",
                "pl_PL", "sk_SK", "sv_SE", "tr_TR", "uk_UA",
            )
        }
    }

    inner class LangKey(
        val key: String,
        var value: String,
        val languageHint: String? = null
    )
}

/**
 * add a new lang key
 */
fun langKey(key: String, value: String, languageHint: String? = null) {
    Addon.active?.config?.langFileBuilder?.addonRes?.add(key, value, languageHint)
        ?: error("Lang keys can only be added with an active addon!")
}