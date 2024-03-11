@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.files.res.rendercontrollers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.getMonsteraLogger

class ResRenConUvAnim {
    @SerializedName("offset")
    @Expose
    var offsetData: MutableList<Any>? = null
        @MonsteraBuildSetter set

    @SerializedName("scale")
    @Expose
    var scaleData: MutableList<Any>? = null
        @MonsteraBuildSetter set

    private fun get2ElList(from: Any, to: Any): MutableList<Any> {
        val ls = mutableListOf<Any>()
        when (from) {
            is Molang -> ls.add(from.data)
            is Number -> ls.add(from)
            is String -> ls.add(from)

            else -> getMonsteraLogger(this.javaClass.name).warn(
                "type can't be parsed: invalid type '${from::class.java.name}'"
            )
        }
        when (to) {
            is Molang -> ls.add(to.data)
            is Number -> ls.add(to)
            is String -> ls.add(to)

            else -> getMonsteraLogger(this.javaClass.name).warn(
                "type can't be parsed: '${to::class.java.name}'"
            )
        }

        return ls
    }

    @OptIn(MonsteraBuildSetter::class)
    fun offset(from: Any, to: Any = from) {
        offsetData = get2ElList(from, to)
    }

    @OptIn(MonsteraBuildSetter::class)
    fun scale(from: Any, to: Any = from) {
        scaleData = get2ElList(from, to)
    }
}