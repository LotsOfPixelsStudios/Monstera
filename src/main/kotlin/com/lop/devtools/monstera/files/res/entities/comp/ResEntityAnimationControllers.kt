package com.lop.devtools.monstera.files.res.entities.comp

class ResEntityAnimationControllers {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    val unsafe = Unsafe()

    inner class Unsafe {
        /**
         * access to all defined animations
         */
        val general = arrayListOf<Any>()

        fun getData(): ArrayList<Any> {
            return unsafe.general
        }
    }

    /**
     * 1..*
     *
     * @param identifier: callable from within the entity file (servers no real purpose)
     * @param anim: identifier defined in the controller file like "controller.animation.pig.setup"
     */
    fun animation(identifier: String, anim: String) {
        var animMod = anim

        if (!anim.contains("controller.animation.")) {
            animMod = "controller.animation.$anim"
        }

        val animConList = mutableMapOf<String, String>()
        animConList.apply {
            put(identifier, animMod)
        }

        unsafe.general.apply {
            add(animConList)
        }
    }
}