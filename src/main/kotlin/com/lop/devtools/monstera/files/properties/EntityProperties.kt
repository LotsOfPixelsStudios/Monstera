package com.lop.devtools.monstera.files.properties

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.properties.types.*
import com.lop.devtools.monstera.getMonsteraLogger

class EntityProperties : MonsteraFile {
    override val unsafe = Unsafe()
    private val logger = getMonsteraLogger("Property")

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, GenericProperty<*>>()
        override fun getData(): MutableMap<String, Any> = general.map { (k, v) ->
            v as MonsteraFile
            val dataMap = (v.unsafe as MonsteraUnsafeMap).getData()
            if(!dataMap.containsKey("default")) {
                logger.warn("No default value for property given!")
            }
            k to dataMap
        }.toMap().toMutableMap()
    }

    /**
     * Set a property where you can save an enum.
     * The default value can always be a Molang expression
     *
     * ```
     * enum("sample") {
     *      default("a")
     *      values = arrayListOf("a", "b")
     *      clientSync = true   //available in resource packs
     * }
     * ```
     */
    fun enum(name: String, propertyData: EnumProperty.() -> Unit) {
        if (unsafe.general.containsKey(name)) {
            if (unsafe.general[name] is EnumProperty) {
                (unsafe.general[name] as EnumProperty).apply(propertyData)
                return
            }
            logger.warn("Property '$name' is defined twice with a different Type! (Overwriting)")
        }
        unsafe.general[idWithNameSpace(name)] = EnumProperty().apply(propertyData)
    }

    /**
     * Set a property where you can save a bool.
     * The default value can always be a Molang expression
     *
     * ```
     * enum("sample") {
     *      default(ture)
     *      clientSync = true   //available in resource packs
     * }
     * ```
     */
    fun bool(name: String, propertyData: BoolProperty.() -> Unit) {
        if (unsafe.general.containsKey(name)) {
            if (unsafe.general[name] is BoolProperty) {
                (unsafe.general[name] as BoolProperty).apply(propertyData)
                return
            }
            logger.warn("Property '$name' is defined twice with a different Type! (Overwriting)")
        }
        unsafe.general[idWithNameSpace(name)] = BoolProperty().apply(propertyData)
    }

    /**
     * Set a property where you can save an int.
     * The default value can always be a Molang expression
     *
     * ```
     * enum("sample") {
     *      default(1)
     *      range = 1 to 10
     *      clientSync = true   //available in resource packs
     * }
     * ```
     */
    fun int(name: String, propertyData: IntProperty.() -> Unit) {
        if (unsafe.general.containsKey(name)) {
            if (unsafe.general[name] is IntProperty) {
                (unsafe.general[name] as IntProperty).apply(propertyData)
                return
            }
            logger.warn("Property '$name' is defined twice with a different Type! (Overwriting)")
        }
        unsafe.general[idWithNameSpace(name)] = IntProperty().apply(propertyData)
    }

    /**
     * Set a property where you can save a float.
     * The default value can always be a Molang expression
     *
     * ```
     * enum("sample") {
     *      default(1f)
     *      range = 1f to 10f
     *      clientSync = true   //available in resource packs
     * }
     * ```
     */
    fun float(name: String, propertyData: FloatProperty.() -> Unit) {
        if (unsafe.general.containsKey(name)) {
            if (unsafe.general[name] is FloatProperty) {
                (unsafe.general[name] as FloatProperty).apply(propertyData)
                return
            }
            logger.warn("Property '$name' is defined twice with a different Type! (Overwriting)")
        }
        unsafe.general[idWithNameSpace(name)] = FloatProperty().apply(propertyData)
    }

    private fun idWithNameSpace(name: String): String {
        val namespace = Addon.active?.config?.namespace ?: "undefined"
        return if (name.startsWith(namespace)) name else "${namespace}:$name"
    }
}