package com.lop.devtools.monstera.files.beh.entitiy.events

class BehEntitySequence(private val parent: BehEntityEvents) {
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
     * 0..1 instances of remove allowed
     *
     * @ sample sequence { remove { componentGroup("testSeq1") } remove { componentGroup("testSeq2") } }
     * @see BehEntityAddRemove
     */
    fun remove(settings: BehEntityAddRemove.() -> Unit) {
        unsafe.general.add(BehEntityAddRemove().apply(settings).unsafe.getData())
    }

    /**
     * 0..1 instances of add allowed
     *
     * @ sample sequence { add { componentGroup("testSeq1") } add { componentGroup("testSeq2") } }
     * @see BehEntityAddRemove
     */
    fun add(settings: BehEntityAddRemove.() -> Unit) {
        val obj = BehEntityAddRemove().apply(settings)

        //### debugger ###
        parent.unsafe.debugAddedGroups.addAll(obj.unsafe.general)
        //################

        val data = obj.unsafe.getData()
        unsafe.general.add(mutableMapOf("add" to data))
    }

    /**
     * 0..1
     * add component groups with different weights
     *
     * @param settings: add
     */
    fun randomize(
        settings: BehEntityRandomize.() -> Unit
    ) {
        val behEntityRandomize = BehEntityRandomize(parent).apply {
            settings(this)
        }

        val ran = mutableMapOf<String,Any>().apply {
            put("randomize", behEntityRandomize.unsafe.getData())
        }
        unsafe.general.add(ran)
    }

    /**
     * 0..*
     *
     * @param comp do a encapsulate sequence comp yourself
     * @sample sampleSeqComp
     */
    fun sequenceComp(comp: BehEntitySequenceComp.() -> Unit) {
        unsafe.general.add(BehEntitySequenceComp(parent).apply(comp).unsafe.getData())
    }

    private fun sampleSeqComp() {
        sequenceComp {
            add {  }
            remove {  }
            filters {  }
            randomise {  }
        }
    }
}