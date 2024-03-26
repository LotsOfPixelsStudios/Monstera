package com.lop.devtools.monstera.files

import com.google.gson.*
import java.lang.reflect.Type

open class MonsteraRawFile {
    var additionalKeys: Map<String, Any>? = null
}

class MonsteraRawFileTypeAdapter : JsonSerializer<MonsteraRawFile> {
    override fun serialize(src: MonsteraRawFile?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        if(src == null) return JsonNull.INSTANCE
        val obj = context?.serialize(src) ?: JsonObject()
        if(obj is JsonObject) {
            src.additionalKeys?.forEach { (k, v) ->
                obj.add(k, context?.serialize(v))
            }
        }
        return obj
    }
}