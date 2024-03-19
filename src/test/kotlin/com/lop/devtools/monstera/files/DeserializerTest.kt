package com.lop.devtools.monstera.files

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import kotlin.test.Test

class DeserializerTest {
    @Test
    fun testDeserializer() {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(MonsteraRawFile::class.java, MonsteraRawFileTypeAdapter())
            .create()

        // Your class to be serialized
        val myClass = MyClass()
        myClass.additionalKeys = mapOf("key1" to "value1", "key2" to "value2")
        myClass.clazzInner.additionalKeys = mapOf("key1" to "value1", "key2" to "value2")

        val json = gson.toJson(myClass, MonsteraRawFile::class.java)
        println(json)
    }

    class MyClass : MonsteraRawFile() {
        @Expose
        var test = "anc"

        @Expose
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var clazzInner = MyClass2()
    }

    class MyClass2 : MonsteraRawFile() {
        @Expose
        var inner = "abc"
    }
}