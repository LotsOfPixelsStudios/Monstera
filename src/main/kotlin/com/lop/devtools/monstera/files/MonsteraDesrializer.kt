package com.lop.devtools.monstera.files

import com.google.gson.*
import java.lang.reflect.Type

open class MonsteraRawFile {
    var additionalKeys: Map<String, Any>? = null
}

class MonsteraRawFileTypeAdapter : JsonSerializer<MonsteraRawFile> {
    override fun serialize(src: MonsteraRawFile?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        if (src == null) return JsonNull.INSTANCE
        val obj = context?.serialize(src) ?: JsonObject()
        if (obj is JsonObject) {
            src.additionalKeys?.forEach { (k, v) ->
                obj.add(k, context?.serialize(v))
            }
        }
        return obj
    }
}

class MonsteraMapFileTypeAdapter : JsonSerializer<Map<String, MonsteraRawFile>> {
    override fun serialize(
        src: Map<String, MonsteraRawFile>?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        if (src == null) return JsonNull.INSTANCE
        val obj = context?.serialize(src) ?: JsonObject()
        if (obj is JsonObject) {
            src.forEach { (mapKey, mapValue) ->
                mapValue.additionalKeys?.forEach { (addKey, addValue) ->
                    if (obj[mapKey] == null) {
                        obj.add(mapKey, JsonObject())
                    }
                    (obj[mapKey] as JsonObject).add(addKey, context?.serialize(addValue))
                }
            }
        }
        return obj
    }
}

class MonsteraListFileTypeAdapter : JsonSerializer<List<MonsteraRawFile>> {
    override fun serialize(
        src: List<MonsteraRawFile>?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        if (src == null) return JsonNull.INSTANCE
        val arr = JsonArray()
        src.forEach { listItem ->
            val obj = context?.serialize(listItem) ?: JsonObject()
            if (obj is JsonObject) {
                listItem.additionalKeys?.forEach { (k, v) ->
                    obj.add(k, context?.serialize(v))
                }
            }
            arr.add(obj)
        }
        return arr
    }
}
