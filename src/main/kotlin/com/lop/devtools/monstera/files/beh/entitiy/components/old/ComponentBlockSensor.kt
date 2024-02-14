package com.lop.devtools.monstera.files.beh.entitiy.components.old

import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap

class ComponentBlockSensor : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val blockSensorBreak = mutableListOf<Any>()

        override fun getData(): MutableMap<String, Any> {
            sensorRadius?.let { general["sensor_radius"] = it }
            if (blockSensorBreak.isNotEmpty()) {
                general["on_break"] = blockSensorBreak
            }
            return general
        }
    }


    var sensorRadius: Float? = null

    fun onBreak(value: BlockSensorBreakGroup.() -> Unit) {
        unsafe.blockSensorBreak.add(BlockSensorBreakGroup().apply(value).getData())
    }
}

class BlockSensorBreakGroup {
    val general = arrayListOf<Any>()

    /**
     * 1..n
     */
    fun group(blocks: ArrayList<String>, triggerEvent: String) {
        general.add(mutableMapOf("block_list" to blocks, "on_block_broken" to triggerEvent))
    }

    /**
     * 1..n
     */
    fun entry(data: BlockSensorBreakComp.() -> Unit) {
        general.add(BlockSensorBreakComp().apply(data).unsafe.getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class BlockSensorBreakComp : MonsteraFile {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        override fun getData(): MutableMap<String, Any> {
            blocksList?.let { general["block_list"] = it }
            onBlockBroken?.let { general["on_block_broken"] = it }
            return general
        }
    }

    var blocksList: ArrayList<String>? = null
        set(value) {
            if(value == null || field == null) {
                field = value
            } else {
                field?.addAll(value) ?: error("field changed to null while trying to write to it!")
            }
        }
    var onBlockBroken: String? = null
}