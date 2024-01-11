package com.lop.devtools.monstera.addon.item

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.recipes.CraftingRecipe
import com.lop.devtools.monstera.files.beh.item.BehItem
import com.lop.devtools.monstera.files.beh.item.BehItemComponents
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.items.ResItem
import java.io.File

class Item(val name: String, val displayName: String, private val addon: Addon) {
    private val behItem = BehItem()
    private val resItem = ResItem()
    private var category: String = "equipment"
    private val craftingRecipe: CraftingRecipe = CraftingRecipe()

    fun category(category: String = "Equipment") {
        this.category = category
    }

    fun identifier() = addon.config.namespace + ":" + name

    fun renderOffset(category: String = "tools") {
        resItem.components {
            renderOffset(category)
        }
    }

    fun texture(texture: File) {
        val uniqueFilename = getUniqueFileName(texture)
        val target = addon
            .config
            .paths
            .resTextures
            .resolve("items")
            .resolve("monstera")
            .resolve(uniqueFilename)
            .toFile()

        texture.copyTo(target, true)
        resItem.components {
            icon(
                "textures/items/monstera/${uniqueFilename.removeSuffix(".png")}"
            )
        }
    }

    fun vanillaTexture(texture: String, path: String = "textures/items/$texture") {
        resItem.components {
            icon(texture, path)
        }
    }

    fun components(components: BehItemComponents.() -> Unit) {
        behItem.components(components)
    }

    /**
     * creates a recipe for the crafting table
     *
     * ```
     * craftingRecipe {
     *     craftingPattern(
     *         t("","minecraft:diamond","minecraft:diamond"),
     *         t("","minecraft:diamond",""),
     *         t("","minecraft:stick","")
     *     )
     *     unlock {
     *         item("minecraft:wood", count = 3, data = 2)
     *         context()
     *     }
     * }
     * ```
     */
    fun craftingRecipe(data: CraftingRecipe.() -> Unit) {
        craftingRecipe.apply(data)
    }

    fun build() {
        behItem.description(identifier(), category)
        behItem.unsafe.build(name, addon.config.paths.behItems)

        resItem.description(identifier(), category, displayName)
        resItem.unsafe.build(name, addon.config.paths.resItem)

        if(!craftingRecipe.unsafe.isEmpty())
            craftingRecipe.unsafe.build(name, identifier(), addon)
    }
}