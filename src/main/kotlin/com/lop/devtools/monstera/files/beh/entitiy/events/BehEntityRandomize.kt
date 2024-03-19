package com.lop.devtools.monstera.files.beh.entitiy.events

class BehEntityRandomize(private val parent: BehEntityEvents) {
    /**
     * unsafe to use variables, used for plugins/ libraries
     */
    val unsafe = Unsafe()

    inner class Unsafe {
        /**
         * access to all defined data
         */
        val general = arrayListOf<Any>()

        fun getData(): ArrayList<Any> {
            return unsafe.general
        }
    }

    /**
     * 1..*
     *
     * @sample sampleRandomComp
     */
    fun randomComp(settings: BehEntityRandomComp.() -> Unit) {
        unsafe.general.add(BehEntityRandomComp(parent).apply(settings).getData())
    }

    private fun sampleRandomComp() {
        randomComp {
            weight = 1
            add {  }
            remove {  }
            randomize {  }
        }
    }
}