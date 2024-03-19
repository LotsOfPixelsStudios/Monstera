package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.animcontroller.AnimController
import com.lop.devtools.monstera.files.animcontroller.AnimStateComponent

interface AnimationControllerExtensions {
    /**
     * Create an Animation Controller and decide when it should activate with a query
     *
     * @param name the name of the anim controller
     * @param query the query when the controller should be active
     * @param data the controller data, caution: wierd nesting!
     */
    fun AnimStateComponent.controller(name: String, query: Query = Query.True, data: AnimController.() -> Unit)

    /**
     * Create an Animation Controller and decide when it should activate with a query
     *
     * @param name the name of the anim controller
     * @param query the query when the controller should be active
     * @param data the controller data, caution: wierd nesting!
     */
    fun AnimStateComponent.controller(
        name: String,
        query: () -> Query = { Query.True },
        data: AnimController.() -> Unit
    )
}