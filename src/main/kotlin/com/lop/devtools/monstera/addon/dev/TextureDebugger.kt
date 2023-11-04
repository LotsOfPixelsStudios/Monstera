package com.lop.devtools.monstera.addon.dev

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.res.TextureIndex
import org.slf4j.LoggerFactory

fun validateTextures(addon: Addon) {
    val logger = LoggerFactory.getLogger("Validate Textures")

    val resPackIco = addon.config.resPath.resolve("pack_icon.png").toFile()
    val behPackIco = addon.config.behPath.resolve("pack_icon.png").toFile()

    if(!resPackIco.exists()) {
        logger.warn("Missing res icon")
        ResourceLoader.loadResourceWithStreamTo("pack.png", resPackIco)
    }

    if(!behPackIco.exists()) {
        logger.warn("Missing beh icon")
        ResourceLoader.loadResourceWithStreamTo("pack.png", behPackIco)
    }

    if(TextureIndex.instance(addon).textures.isEmpty()) {
        logger.info("TextureList is empty nothing to validate")
    }
}

private fun isDefaultMcTexture(texture: String): Boolean {
    val resourceStream = ResourceLoader.getResourceAsStream("mc_default_textures.json")

    //check if the uri is correct/not null
    val content = String(resourceStream.readBytes())

    //read it and check for the texture
    //is actually a default texture
    return content.contains(texture)    //is not a default texture
}
