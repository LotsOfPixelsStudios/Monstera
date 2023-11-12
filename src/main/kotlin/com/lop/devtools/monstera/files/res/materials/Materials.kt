package com.lop.devtools.monstera.files.res.materials

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.addon.entity.resource.ResourceEntity
import com.lop.devtools.monstera.files.MonsteraBuilder
import com.lop.devtools.monstera.getMonsteraLogger

/**
 * this is experimental as there should be only one entity.material file, use Material.modify()
 */
fun Addon.resMaterial(filename: String, data: Materials.() -> Unit) {
    Materials(filename).apply(data).unsafe.build(addon = this)
}

fun ResourceEntity.newMaterial(material: Material, data: MaterialsDef.() -> Unit) =
    unsafeMaterials.newMaterial(material, data)

interface MaterialExtensions {
    infix fun String.extends(data: Material) = Material("$this:${data.name}")
}

class Materials(val fileName: String) : MonsteraFile, MaterialExtensions {
    override val unsafe = Unsafe()
    private val logger = getMonsteraLogger("Material")

    companion object {
        private val instances = mutableMapOf<Int, Materials>()

        fun instance(addon: Addon): Materials {
            return instances[addon.hashCode()] ?: Materials("entity").also {
                instances[addon.hashCode()] = it
            }
        }
    }


    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val materialDefs = mutableMapOf<String, MaterialsDef>()

        override fun getData(): MutableMap<String, Any> {
            general.putAll(materialDefs.map { (id, material) -> id to material.unsafe.getData() })
            if (!general.containsKey("version")) {
                logger.error("Build failed, no version found, materials won't be applied!")
            }
            return mutableMapOf("materials" to general)
        }

        fun build(addon: Addon) {
            MonsteraBuilder.buildTo(addon.config.paths.resMaterials, "$fileName.material", getData(), true)
        }
    }

    /**
     * the version, should be always "1.0.0"
     */
    fun version(data: String) {
        unsafe.general["version"] = data
    }

    /**
     * the id of the new material
     *
     * @return the name of the material (not the identifier)
     */
    fun newMaterial(material: Material, data: MaterialsDef.() -> Unit): String {
        if (unsafe.materialDefs.containsKey(material.name)) {
            unsafe.materialDefs[material.name]?.apply(data) ?: logger.error(
                "Invalid state. This should not happen! Material list contained key '$material' but when trying to apply data it was null!"
            )
        } else {
            unsafe.materialDefs[material.name] = MaterialsDef().apply(data)
        }

        return material.name.split(":").first()
    }
}

class MaterialsDef : MonsteraFile, MaterialExtensions {
    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        val defines = mutableListOf<String>()

        override fun getData(): MutableMap<String, Any> {
            if (defines.isNotEmpty()) {
                general["+defines"] = defines
            }
            return general
        }
    }

    fun defines(vararg enum: String) {
        unsafe.defines.addAll(enum)
    }

    fun defines(vararg enum: MaterialDefinition) {
        unsafe.defines.addAll(enum.map { it.data })
    }

    fun vertexFields() {
        TODO("Not yet implemented")
    }
}