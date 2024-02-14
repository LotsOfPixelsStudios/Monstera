package com.lop.devtools.monstera.harvest

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import com.lop.devtools.monstera.files.File
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import kotlin.io.path.Path

private val components = mutableMapOf<String, JsonObject>()
private val primitveComponents = mutableMapOf<String, JsonPrimitive>()
private val componentKtFiles = mutableMapOf<String, String>()
private val doc =
    File("C:\\Users\\Matthias\\Downloads\\bedrock-samples-1.20.50.3\\bedrock-samples-1.20.50.3\\documentation\\Entities.html").readText()

fun main() {
    val files =
        File("C:\\Users\\Matthias\\Downloads\\bedrock-samples-1.20.50.3\\bedrock-samples-1.20.50.3\\behavior_pack\\entities").walk()
            .maxDepth(1).filter { it.name != "entities" }

    val componentsFile = Path("src", "test", "resources", "com/lop/devtools/monstera/files/beh/entitiy/components/Components.kt").toFile()
    componentsFile.createNewFile()
    componentsFile.appendText(
        """
        import com.google.gson.annotations.Expose
        import com.google.gson.annotations.SerializedName
        import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
        
        class Components {
        
    """.trimIndent()
    )

    files.forEach {
        val entity = JsonParser.parseString(it.readText()).asJsonObject
        val entObj = entity.asJsonObject["minecraft:entity"]

        addComponents(entObj?.asJsonObject?.get("components")?.asJsonObject, it.name)
        entObj?.asJsonObject?.get("component_groups")?.asJsonObject?.entrySet()?.forEach { (_, v) ->
            addComponents(v.asJsonObject, it.name)
        }
    }

    println(components.keys)
    println(primitveComponents.keys)

    primitveComponents.forEach {
        componentsFile.appendText(formatPrimitiveField(it.key, it.value))
        componentsFile.appendText("\n")
    }

    components.forEach {
        val toPrint = formatValueField(it.key, it.value)
        if (toPrint.isNotEmpty()) {
            componentsFile.appendText(toPrint)
            componentsFile.appendText("\n")
        } else {
            val serialName = it.key.formatKey()
            val className = serialName.replaceFirstChar { c -> c.uppercase() }
            val fileName = "$className.kt"

            val complexFile = formatComplexField(it.key, it.value)
            componentsFile.appendText(complexFile.first)
            componentsFile.appendText("\n")

            val cFile = Path("src", "test", "resources", "components", fileName).toFile()
            cFile.appendText(
                """
                import com.google.gson.annotations.Expose
                import com.google.gson.annotations.SerializedName
                import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
                
            """.trimIndent()
            )
            cFile.appendText(complexFile.second)
        }
    }

    componentsFile.appendText(
        """
            @DslMarker
            annotation class MonsteraComponentMarker

            @DslMarker
            annotation class VanillaComponentMarker
        }
        """.trimIndent()
    )
}

fun addComponents(entityComponents: JsonObject?, fileName: String) {
    entityComponents?.entrySet()?.forEach { (k, v) ->
        if (v.isJsonPrimitive) {
            primitveComponents[k] = v.asJsonPrimitive
            return
        }
        try {
            if (components.containsKey(k))
                components[k] = mergeJsonObjects(components[k]!!, v.asJsonObject)
            else
                components[k] = v.asJsonObject
        } catch (e: Exception) {
            println("component: $k")
            println("file: $fileName")
        }
    }
}

fun mergeJsonObjects(obj1: JsonObject, obj2: JsonObject): JsonObject {
    val mergedObject = JsonObject()

    // Add keys from obj1
    for ((key, value) in obj1.entrySet()) {
        mergedObject.add(key, value)
    }

    // Add keys from obj2 that are not already in obj1
    for ((key, value) in obj2.entrySet()) {
        if (!mergedObject.has(key)) {
            mergedObject.add(key, value)
        }
    }

    return mergedObject
}

fun formatPrimitiveField(key: String, value: JsonPrimitive): String {
    val type = value.getTypeAsKotlinString()

    val serialName = key.formatKey()

    return """
        @SerializedName("$key")
        @Expose
        var $serialName: $type? = null
            @MonsteraBuildSetter set
    """.trimIndent()
}

fun formatPrimitveArray(key: String, value: JsonPrimitive): String {
    val type = value.getTypeAsKotlinString()

    val serialName = key.formatKey()

    return """
        @SerializedName("$key")
        @Expose
        var ${serialName}Data: MutableList<$type>? = null
            @MonsteraBuildSetter set
            
        @OptIn(MonsteraBuildSetter::class)
        @VanillaComponentMarker
        fun ${serialName}(vararg value: $type) {
            ${serialName}Data = (${serialName}Data ?: mutableListOf()).also { it.addAll(value.toList()) }
        }
    """.trimIndent()
}

fun formatComplexArray(key: String, value: JsonObject): Pair<String, String> {
    val serialName = key.formatKey()
    val className = serialName.replaceFirstChar { it.uppercase() }
    val inner = formatInnerObject(value)
    val componentDoc = getComponentDescription(key)

    var fileContent = """
        class $className {
            ${inner.currentCode}
        }
    """.trimIndent()

    var javaDocContent = """
        /**
         * ${componentDoc.description}
         * ```
         * $serialName {
         
    """.trimIndent()

    inner.additionalClassCode.forEach {
        fileContent += "\n"
        fileContent += it
    }

    inner.possibleKeysWithDoc.forEach {
        javaDocContent += " *     ${it.key} ${it.value}\n"
    }

    javaDocContent += " * }\n *```\n */"

    return """
        @SerializedName("$key")
        @Expose
        var ${serialName}Data: MutableList<$className>? = null
            @MonsteraBuildSetter set
        
        $javaDocContent
        @OptIn(MonsteraBuildSetter::class)
        @VanillaComponentMarker
        fun ${serialName}(value: $className.() -> Unit) {
            ${serialName}Data = (${serialName}Data ?: mutableListOf()).also { it.add($className().apply(value)) }
        }
    """.trimIndent() to fileContent
}

fun formatValueField(key: String, value: JsonObject): String {
    if (value.keySet().size != 1)
        return ""
    if (!value.keySet().contains("value"))
        return ""
    if (!value["value"].isJsonPrimitive)
        return ""

    val serialName = key.formatKey()
    val serialType = value["value"].asJsonPrimitive.getTypeAsKotlinString()

    return """
        @SerializedName("$key")
        @Expose
        var ${serialName}Data: ComponentValue? = null
            @MonsteraBuildSetter set
        
        @OptIn(MonsteraBuildSetter::class)
        var ${serialName}: $serialType? = null
            set(value) {
                ${serialName}Data = ComponentValue().also { it.value = value }
                field = value
            }
        
        @OptIn(MonsteraBuildSetter::class)
        @VanillaComponentMarker
        fun ${serialName}(value: ComponentValue.() -> Unit) {
            ${serialName}Data = (${serialName}Data ?: ComponentValue()).apply(value)
        }
    """.trimIndent()
}

fun formatComplexField(key: String, value: JsonObject): Pair<String, String> {
    val serialName = key.formatKey()
    val className = serialName.replaceFirstChar { it.uppercase() }
    val inner = formatInnerObject(value)
    val componentDoc = getComponentDescription(key)

    var fileContent = """
        class $className {
            ${inner.currentCode}
        }
    """.trimIndent()

    var javaDocContent = """
        /**
         * ${componentDoc.description}
         * ```
         * $serialName {
         
    """.trimIndent()

    inner.additionalClassCode.forEach {
        fileContent += "\n"
        fileContent += it
    }

    inner.possibleKeysWithDoc.forEach {
        javaDocContent += " *     ${it.key} ${it.value}\n"
    }

    javaDocContent += " * }\n *```\n */"

    return """
        @SerializedName("$key")
        @Expose
        var ${serialName}Data: $className? = null
            @MonsteraBuildSetter set
        
        $javaDocContent
        @OptIn(MonsteraBuildSetter::class)
        @VanillaComponentMarker
        fun ${serialName}(value: $className.() -> Unit) {
            ${serialName}Data = (${serialName}Data ?: $className()).apply(value)
        }
    """.trimIndent() to fileContent
}

fun formatInnerObject(value: JsonObject): ObjectData {
    var currentClass = ""
    val additionalClasses = mutableListOf<String>()
    val possibleKeysWithDoc = mutableMapOf<String, String>()

    value.entrySet().forEach { (k, v) ->
        if (v.isJsonPrimitive) {
            currentClass += "\n"
            currentClass += formatPrimitiveField(k, v.asJsonPrimitive)
            possibleKeysWithDoc[k.formatKey()] = "= ${v.asJsonPrimitive.getValueAsString()}"
        } else if (v.isJsonObject) {
            val complexObj = formatComplexField(k, v.asJsonObject)
            currentClass += "\n"
            currentClass += complexObj.first
            additionalClasses.add(complexObj.second)
        } else if (v.isJsonArray) {
            if(v.asJsonArray.all { it.isJsonPrimitive }) {
                currentClass += "\n"
                currentClass += formatPrimitveArray(k, v.asJsonArray.first().asJsonPrimitive)
            } else {
                val sampleObj = v.asJsonArray.first { it.isJsonObject }
                val complexObj = formatComplexArray(k, sampleObj.asJsonObject)
                currentClass += "\n"
                currentClass += complexObj.first
                additionalClasses.add(complexObj.second)
            }
        }
    }

    return ObjectData(currentClass, additionalClasses, possibleKeysWithDoc)
}

data class ObjectData(
    val currentCode: String,
    val additionalClassCode: MutableList<String>,
    val possibleKeysWithDoc: MutableMap<String, String>
)


fun JsonPrimitive.getTypeAsKotlinString(): String {
    return if (this.isNumber)
        "Number"
    else if (this.isBoolean)
        "Boolean"
    else
        "String"
}

fun JsonPrimitive.getValueAsString(): String {
    return if (this.isNumber)
        this.asNumber.toString()
    else if (this.isBoolean)
        this.asBoolean.toString()
    else
        this.asString
}

fun String.formatKey(): String = this.removePrefix("minecraft:")
    .replace("behavior", "beh")
    .snakeToCamelCase()

fun String.snakeToCamelCase(): String {
    val pattern = "[_:.][a-z]".toRegex()
    return replace(pattern) { it.value.last().uppercase() }
}

fun getComponentDescription(key: String): ComponentDescription {
    val doc: Document = Jsoup.parse(doc)

    // Extracting description based on the key
    val description =
        doc.select("p[id=$key]").first()?.parent()?.nextSibling()?.toString()?.trim()
            ?: return ComponentDescription("")

    return ComponentDescription(description)
}

data class ComponentDescription(
    val description: String
)