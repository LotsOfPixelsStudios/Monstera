package com.lop.devtools.monstera.addon.block

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.entity.getGeoId
import com.lop.devtools.monstera.addon.sound.Sound
import com.lop.devtools.monstera.addon.sound.SoundData
import com.lop.devtools.monstera.addon.sound.unsafeApplySoundData
import com.lop.devtools.monstera.files.beh.blocks.BehBlocks
import com.lop.devtools.monstera.files.beh.blocks.components.MaterialInstance
import com.lop.devtools.monstera.files.beh.blocks.components.MaterialSettings
import com.lop.devtools.monstera.files.createWithDirs
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.blocks.BlockDefs
import com.lop.devtools.monstera.files.res.blocks.TerrainTextures
import java.io.File

open class Block(
    private val addon: Addon,
    /**
     * get the name of the block, set with name()
     */
    var name: String,
    /**
     * get the display name, set with name()
     */
    var displayName: String
) {
    val unsafeBehBlock = BehBlocks()
    val unsafeBlockDefs = BlockDefs.instance(addon)
    val unsafeTerrainTextures = TerrainTextures.instance(addon)
    val unsafeSoundData: MutableList<SoundData> = mutableListOf()

    /**
     * returns the identifier of the block that can be called in a command
     */
    fun identifier() = "${addon.config.namespace}:$name"

    /**
     * set a geometry with id, note: generally not save to use, make sure the model is already in the build files
     */
    fun geometry(geoId: String) {
        unsafeBehBlock.components {
            geometry = geoId
        }
    }

    /**
     * set a custom geometry
     */
    fun geometry(file: File) {
        val uniqueFilename = getUniqueFileName(file)
        val target = addon.config.paths.resModels.resolve("blocks").resolve(uniqueFilename).toFile()
        file.copyTo(target.createWithDirs(), true)
        geometry(getGeoId(file))
    }

    /**
     * set a texture that is applied on all sites
     */
    fun texture(name: String, path: String, settings: MaterialSettings.() -> Unit) {
        unsafeBehBlock.components {
            materialInstance {
                all {
                    texture = name
                    this.apply(settings)
                }
            }
        }
        unsafeTerrainTextures.addBlockTexture(name, path)   //terrainTextures obj manages duplicate block names
    }

    /**
     * set a texture that is applied on all sites
     */
    fun texture(file: File, settings: MaterialSettings.() -> Unit) {
        val uniqueFilename = getUniqueFileName(file)
        val target = addon.config.paths.resTextures.resolve("monstera").resolve(uniqueFilename).toFile()
        file.copyTo(target.createWithDirs(), true)
        val name = getUniqueFileName(file).removeSuffix(".png")
        val path = "textures/monstera/$name"
        texture(name, path, settings)
    }

    /**
     * add block with the default geometry, some assumptions can be made, use this to add a more efficient block
     *
     * ```
     * defaultBlock {
     *     isotropic = true
     *     brightnessGamma = 1
     *     texture(file, Face.ALL)
     *     sound(file) //may change in the future
     *     carriedTextures(file)
     * }
     * ```
     */
    fun defaultBlock(data: DefaultBLock.() -> Unit) {
        DefaultBLock(this, addon).apply(data)
    }

    /**
     * add a sound to the block, call multiple times for different sound files
     *
     * ```
     * sound(identifier = "block.sand.fall") {
     *     pitch = 1f to 1.2f   //default 1 to 1
     *     volume = 0.7f to 1f  //default 1 to 1
     *     maxDistance = 16
     *     minDistance = 2
     *     category = SoundCategory.BLOCK //default
     *     sound(getResource(/*...*/))  //load a single ogg file with optional settings, see docs of [Sounds.sound()], can be called multiple times
     *    onEvent(event = SoundEvent.DEFAULT, pitch = 1 to 2, volume = 0.7f to 1)
     *    onEvent(event = SoundEvent.PLACE, pitch = 0.9f to 1, volume = 0.7f to 1)
     *
     *    //you can also import sounds definitions with some limitations like
     *    importSound("ambient.basalt_deltas.loop") //note: this will overwrite the identifier if one is set!
     *
     *    //or define multiple Sounds that will be randomly selected
     *    sound(arrayListOf(
     *        getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.2f, pitch = 1, weight = 3),
     *        getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.1f, pitch = 1, weight = 2),
     *        getResource("file.ogg") to SoundDefData() //use default values
     *    ))
     * }
     * ```
     */
    fun sound(identifier: String, data: Sound.() -> Unit): String {
        val soundData = SoundData(addon).apply { this.identifier = identifier }.apply(data)
        unsafeSoundData.add(soundData)
        return soundData.identifier
    }

    /**
     * you may want to use this function to import existing sound sounds form sound.json like, note this will overwrite
     * other sound {} definitions as this will just take and apply the defined ones
     *
     * ```
     * sound("amethyst_block")
     * //or
     * sound("nether_brick")
     * ```
     */
    fun sound(name: String) {
        unsafeBlockDefs.addDefinition(identifier()) {
            sound = name
        }
    }

    fun build(): Block {
        addon.config.langFileBuilder.addonRes.add("tile.${identifier()}.name", displayName)
        unsafeBehBlock.apply {
            description {
                identifier = "${addon.config.namespace}:$name"
            }
        }
        unsafeBehBlock.build(name)
        if (unsafeSoundData.isNotEmpty()) {
            unsafeBlockDefs.addDefinition(identifier()) {
                sound = name
            }
            addon.unsafeApplySoundData(unsafeSoundData, name)
        }
        return this
    }
}