package com.lop.devtools.monstera.addon

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.api.InvokeBeforeEnd
import com.lop.devtools.monstera.addon.api.PackageInvoke
import com.lop.devtools.monstera.addon.block.Block
import com.lop.devtools.monstera.addon.entity.Entity
import com.lop.devtools.monstera.addon.item.Item
import com.lop.devtools.monstera.addon.mcfunction.McFunction
import com.lop.devtools.monstera.addon.sound.SoundUtil
import java.io.File

interface Addon {
    @DslMarker
    annotation class AddonTopLevel

    companion object {
        var active: Addon? = null
    }

    val config: Config

    val onEndListener: MutableList<InvokeBeforeEnd>
    val onPackage: MutableList<PackageInvoke>

    var buildFunctions: Boolean
    var buildTextureList: Boolean
    var buildItemTextureIndex: Boolean
    var buildToMcFolder: Boolean
    var manifestMinEnginVersion: ArrayList<Int>

    var includeInfoMcFunction: Boolean

    /**
     * Define an abstract entity
     *
     * ```
     * entity {
     *    name("id", "Display")
     *    behaviour {  }
     *    resource {  }
     * }
     * ```
     */
    @AddonTopLevel
    fun entity(name: String, displayName: String = name, entity: Entity.() -> Unit): Entity

    /**
     * Define sounds
     *
     * ```
     *  sounds {
     *    soundsDefinitions(Props.currentAddon) {
     *      newSoundDef("tp.test.drive") {
     *      category(SoundCategory.BLOCK)
     *      maxDistance(2f)
     *      minDistance(1f)
     *      //can be called multiple times
     *      sound {
     *        name(File("test.ogg"))
     *        volume(1f)
     *        pitch(1f)
     *      }
     *      sound(File("test.ogg"))
     *      sound(File("test.ogg"))
     *    }
     *  }
     *  categorySounds {
     *    blockSounds {
     *      soundEventGroup("test") {
     *        event(SoundEvent.ATTACK) {
     *          sound("test.sound")
     *          volume(0.2f)
     *        }
     *        event(SoundEvent.AMBIENT) {
     *          sound("test2.sound")
     *          volume(0.2f)
     *          pitch(0.3f)
     *        }
     *      }
     *      soundEventGroup("test2") {
     *        event(SoundEvent.SCREAM) { }
     *      }
     *    }
     *    entitySounds {
     *
     *    }
     *    defaultEntitySounds {
     *
     *    }
     *    individualEventSounds {
     *
     *    }
     *    interactiveSounds {
     *
     *    }
     *  }
     * }
     * ```
     */
    @AddonTopLevel
    fun sounds(sounds: SoundUtil.() -> Unit): SoundUtil

    /**
     * ```
     *  item {
     *      name("test_item", "Test Item")
     *      renderOffset("tools")
     *      texture(getResource("test_item"))
     *      category("Equipment")
     *      components {
     *          //...
     *      }
     *  }
     * ```
     */
    @AddonTopLevel
    fun item(name: String, displayName: String = name, item: Item.() -> Unit): Item

    /**
     * ```
     *  mcFunction {
     *      name = "my_function"
     *      entries = arrayListOf("say hello")
     *      execEveryTick = true
     *  }
     * ```
     */
    @AddonTopLevel
    fun mcFunction(name: String, data: McFunction.() -> Unit): String
    fun loadParticle(value: File)

    /**
     * add a block
     *
     * ```
     * block {
     *    defaultBlock {
     *         //...
     *    }
     *    //or
     *    geometry(file)
     *    texture(file)
     *    sound { }
     * }
     * ```
     */
    @AddonTopLevel
    fun block(name: String, displayName: String = name, data: Block.() -> Unit): Block

    fun scripts(directory: File)

    /**
     * internal function to build the addon
     */
    fun build()
}