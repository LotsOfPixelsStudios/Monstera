package com.lop.devtools.monstera.files.res.entities.comp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.files.getUniqueFileName
import com.lop.devtools.monstera.files.res.ItemTextureIndex
import com.lop.devtools.monstera.files.res.TextureIndex
import java.awt.Color
import java.io.File
import java.nio.file.Path

class ResEntitySpawnEgg {
    @SerializedName("base_color")
    @Expose
    var baseColorData: String? = null
        @MonsteraBuildSetter set

    @SerializedName("overlay_color")
    @Expose
    var overlayColorData: String? = null
        @MonsteraBuildSetter set

    @SerializedName("texture")
    @Expose
    var textureData: String? = null
        @MonsteraBuildSetter set

    @SerializedName("texture_index")
    @Expose
    var textureIndexData: Number? = null
        @MonsteraBuildSetter set

    var displayName: String? = null

    /**
     * 1
     *
     * @param texture: texture name defined in the item texture index
     * @param textureIndex: use this if a texture has multiple textures (like the spawn_egg)
     */
    @OptIn(MonsteraBuildSetter::class)
    fun eggByTexture(texture: String = "spawn_egg", textureIndex: Int) {
        textureData = texture
        textureIndexData = textureIndex
    }

    @OptIn(MonsteraBuildSetter::class)
    fun eggByTexture(addon: Addon, textureName: String, path: String = "textures/items/$textureName") {
        ItemTextureIndex.instance(addon).addItemTexture(textureName, path)
        TextureIndex.instance(addon).textures.add(path)
        textureData = textureName
    }

    /**
     * 1
     *
     * copies the file into the correct spot and generates the necessary paths for mc
     */
    fun eggByFile(file: File, addon: Addon, resTexturePath: Path = addon.config.paths.resTextures) {
        val uniqueName = getUniqueFileName(file)
        val target = resTexturePath.resolve("monstera").resolve(uniqueName).toFile()
        file.copyTo(target, true)
        val fileName = uniqueName.removeSuffix(".png")
        eggByTexture(addon, fileName, "textures/monstera/${fileName}")
    }

    /**
     * 1
     *
     * @param baseColor: background color of the spawn egg as a Hex String like ("#53443E")
     * @param overlayColor: details of the egg texture like ("#2E6854")
     */
    @OptIn(MonsteraBuildSetter::class)
    fun eggByColor(baseColor: String, overlayColor: String) {
        baseColorData = baseColor
        overlayColorData = overlayColor
    }


    /**
     * 1
     *
     * @param baseColor: background color of the spawn egg as a Hex String like ("#53443E")
     * @param overlayColor: details of the egg texture like ("#2E6854")
     */
    fun eggByColor(baseColor: Color, overlayColor: Color) {
        eggByColor("#${Integer.toHexString(baseColor.rgb)}", "#${Integer.toHexString(overlayColor.rgb)}")
    }
}