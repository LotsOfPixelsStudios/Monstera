package com.lop.devtools.monstera.files.properties

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.properties.types.*
import com.lop.devtools.monstera.getMonsteraLogger

class EntityProperties {
    val propertyData = mutableMapOf<String, GenericProperty<*>>()

    private fun logger() = getMonsteraLogger(this.javaClass.name)

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
    fun enum(name: String, data: EnumProperty.() -> Unit) {
        if (propertyData.containsKey(name)) {
            if (propertyData[name] is EnumProperty) {
                (propertyData[name] as EnumProperty).apply(data)
                return
            }
            logger().warn("Property '$name' is defined twice with a different Type! (Overwriting)")
        }
        propertyData[idWithNameSpace(name)] = EnumProperty().apply(data)
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
    fun bool(name: String, data: BoolProperty.() -> Unit) {
        if (propertyData.containsKey(name)) {
            if (propertyData[name] is BoolProperty) {
                (propertyData[name] as BoolProperty).apply(data)
                return
            }
            logger().warn("Property '$name' is defined twice with a different Type! (Overwriting)")
        }
        propertyData[idWithNameSpace(name)] = BoolProperty().apply(data)
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
    fun int(name: String, data: IntProperty.() -> Unit) {
        if (propertyData.containsKey(name)) {
            if (propertyData[name] is IntProperty) {
                (propertyData[name] as IntProperty).apply(data)
                return
            }
            logger().warn("Property '$name' is defined twice with a different Type! (Overwriting)")
        }
        propertyData[idWithNameSpace(name)] = IntProperty().apply(data)
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
    fun float(name: String, data: FloatProperty.() -> Unit) {
        if (propertyData.containsKey(name)) {
            if (propertyData[name] is FloatProperty) {
                (propertyData[name] as FloatProperty).apply(data)
                return
            }
            logger().warn("Property '$name' is defined twice with a different Type! (Overwriting)")
        }
        propertyData[idWithNameSpace(name)] = FloatProperty().apply(data)
    }

    private fun idWithNameSpace(name: String): String {
        val namespace = Addon.active?.config?.namespace ?: "undefined"
        return if (name.startsWith(namespace)) name else "${namespace}:$name"
    }
}