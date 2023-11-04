package com.lop.devtools.monstera.files.properties.types

import com.lop.devtools.monstera.addon.molang.Molang

interface GenericProperty <T> {
    /**
     * Set the default value of the property
     *
     * @param value the default value of the property
     */
    fun default(value: T)
    /**
     * Set the default value of the property with the help of a query/molang expr
     *
     * @param value the default value of the property with an expression
     */
    fun default(value: Molang)

    /**
     * indicates if the property should be available in the resource pack
     */
    var clientSync: Boolean
}