@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.lop.devtools.monstera.addon.item

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeField
import com.lop.devtools.monstera.addon.concept.attachable.AttachableApi
import com.lop.devtools.monstera.addon.concept.recipes.CraftingRecipe
import com.lop.devtools.monstera.files.beh.item.BehItem
import com.lop.devtools.monstera.files.beh.item.BehItemComponents
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.lang.langKey
import com.lop.devtools.monstera.files.res.ItemTextureIndex
import com.lop.devtools.monstera.files.res.TextureIndex
import com.lop.devtools.monstera.files.res.attachables.ResAttachable
import java.io.File

class Item(val name: String, val displayName: String, val addon: Addon) {
    @MonsteraUnsafeField
    val behItem = BehItem()

    @MonsteraUnsafeField
    var category: String = "equipment"

    @MonsteraUnsafeField
    val craftingRecipe: CraftingRecipe = CraftingRecipe()

    @MonsteraUnsafeField
    val attachable = ResAttachable()

    @MonsteraUnsafeField
    val attachableApi = AttachableApi(identifier(), attachable, addon)

    fun category(category: String = "Equipment") {
        this.category = category
    }

    fun menuCategory(data: BehItem.Description.Category) {
        behItem.description {
            this.menuCategory = data
        }
    }

    fun identifier() = addon.config.namespace + ":" + name

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
        TextureIndex.instance(addon).textures.add("textures/items/monstera/${uniqueFilename.removeSuffix(".png")}")
        ItemTextureIndex.instance(addon).addItemTexture(name, "textures/items/monstera/${uniqueFilename.removeSuffix(".png")}")
        behItem.description {
            components {
                icon {
                    this.texture = name
                }
            }
        }
    }

    fun vanillaTexture(texture: String, path: String = "textures/items/$texture") {
        ItemTextureIndex.instance(addon).addItemTexture(name, path)
        behItem.description {
            components {
                icon {
                    this.texture = texture
                }
            }
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

    fun attachable(data: AttachableApi.() -> Unit) {
        attachableApi.apply(data)
    }

    fun build() {
        behItem.description {
            identifier = identifier()
            if (menuCategoryData == null)
                menuCategory = BehItem.Description.Category.ITEMS
        }
        behItem.components {
            displayNameKey("item.${identifier()}.name")
        }
        langKey("item.${identifier()}.name", displayName)

        behItem.build(name, addon.config.paths.behItems)

        if(!attachable.isEmpty()) {
            attachable.apply {
                description {
                    this.identifier = this@Item.identifier()
                }
            }
            attachable.build(name)
        }

        //resItem.build(name)

        if (!craftingRecipe.unsafe.isEmpty())
            craftingRecipe.unsafe.build(name, identifier(), addon)
    }
}