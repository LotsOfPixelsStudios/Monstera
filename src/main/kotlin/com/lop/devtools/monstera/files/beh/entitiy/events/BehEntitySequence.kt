package com.lop.devtools.monstera.files.beh.entitiy.events

class BehEntitySequence {
    val sequenceEvents: MutableList<BehEntityEvent.ContainsFilter> = mutableListOf()

    /**
     * 0..1 instances of remove allowed
     *
     * @ sample sequence { remove { componentGroup("testSeq1") } remove { componentGroup("testSeq2") } }
     * @see BehEntityAddRemove
     */
    fun remove(settings: BehEntityAddRemove.() -> Unit) {
        sequenceComp {
            remove(settings)
        }
    }

    /**
     * 0..1 instances of add allowed
     *
     * @ sample sequence { add { componentGroup("testSeq1") } add { componentGroup("testSeq2") } }
     * @see BehEntityAddRemove
     */
    fun add(settings: BehEntityAddRemove.() -> Unit) {
        sequenceComp {
            add(settings)
        }
    }

    /**
     * 0..1
     * add component groups with different weights
     *
     * @param settings: add
     */
    fun randomize(settings: BehEntityRandomize.() -> Unit) {
        sequenceComp {
            randomize(settings)
        }
    }

    /**
     * 0..*
     *
     * @param comp do a encapsulate sequence comp yourself
     */
    fun sequenceComp(comp: BehEntityEvent.ContainsFilter.() -> Unit) {
        sequenceEvents.add(BehEntityEvent.ContainsFilter().apply(comp))
    }
}