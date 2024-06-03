package com.lop.devtools.monstera.addon.concept.attachable

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.extractAnimationIdsFromFile
import com.lop.devtools.monstera.addon.entity.getGeoId
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.attachables.ResAttachable
import com.lop.devtools.monstera.files.resolveRelativePath
import com.lop.devtools.monstera.getMonsteraLogger
import java.io.File
import java.nio.file.Path

class AttachableApi(val identifier: String, val rawFile: ResAttachable, val addon: Addon) {
    fun geometry(file: File) {
        val uniqueFilename = getUniqueFileName(file)
        val id = getGeoId(file)
        val target = addon.config.paths.resModels.resolve("attachables").resolve(uniqueFilename)
        file.copyTo(target.toFile(), true)
        geometry(id)
    }

    fun geometry(geoId: String) {
        rawFile.description {
            geometry {
                default = geoId
            }
        }
    }

    fun texture(file: File, enchantedGlint: File? = null) {
        val uniqueFileName = getUniqueFileName(file)
        val target =
            addon.config.paths.resTextures.resolve("attachables").resolve("monstera").resolve(uniqueFileName)
        file.copyTo(target.toFile(), true)

        var targetGlint: Path? = null
        if (enchantedGlint?.exists() == true) {
            val uniqueFileNameGlint = getUniqueFileName(enchantedGlint)
            targetGlint =
                addon.config.paths.resTextures.resolve("attachables").resolve("monstera").resolve(uniqueFileNameGlint)
            enchantedGlint.copyTo(targetGlint.toFile(), true)
        }
        texture(resolveRelativePath(target, "textures"), targetGlint?.let { resolveRelativePath(it, "textures") })
    }

    fun texture(path: String, enchantedGlint: String?) {
        rawFile.description {
            textures {
                default = path
                enchanted = enchantedGlint ?: "textures/misc/enchanted_item_glint"
            }
        }
    }

    /**
     * @param animFile the animations
     * @param animName set the name for all animations (input is the identifier), default is the string after the last dot "animation.entity.walk" => "walk"
     */
    fun animation(animFile: File, animName: (String) -> String = { it.split(".").last() }) {
        val animations = extractAnimationIdsFromFile(animFile)
        if (animations.isEmpty()) {
            getMonsteraLogger("Attachables").warn("No animations found in file: ${animFile.name}")
            return
        }
        val uniqueFilename = getUniqueFileName(animFile)
        val target = addon.config.paths.resAnim.resolve(uniqueFilename)
        animFile.copyTo(
            target.toFile(),
            overwrite = true
        )

        animations.forEach {
            rawFile.apply {
                description {
                    animation(animName(it), it)
                }
            }
        }
    }

    fun animation(name: String, id: String) {
        rawFile.description {
            this.animation(name, id)
        }
    }

    fun material(materialDefault: String, materialEnchanted: String) {
        rawFile.description {
            material {
                default = materialDefault
                enchanted = materialEnchanted
            }
        }
    }

    fun renderController(vararg controller: String) = rawFile.description { renderController(controller.toList()) }
    
    fun scripts(data: ResAttachable.Scripts.() -> Unit) {
        rawFile.description {
            this.scripts(data)
        }
    }
    
    /**
     * loads default materials and the default render controller
     */
    fun loadArmorPreset() {
        material("armor", "armor_enchanted")
        renderController("controller.render.armor")
    }
    
    fun loadItemPreset() {
        TODO()
    }
}