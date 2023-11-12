# Blocks

## :fa-solid fa-paperclip: Basic

To create a basic block an option `defaultBlock` is given, this will create a Block as you might expect:

````kotlin
block("my_dirt_block", "Grassy Dirt Block") {
    defaultBlock {
        texture(getResource("default_texture.png"), DefaultBLock.Face.ALL)
    }
}
````

We can also apply some more stuff:

````kotlin
defaultBlock {
    isotropic = true
    brightnessGamma = 1
    texture(file, Face.ALL)
    sound { }
    carriedTextures(file)
}
````

## :fa-solid fa-cube: Different Geometries

We can also apply geometries and with it textures:

````kotlin
block("my_dirt_block", "Grassy Dirt Block") {
    geometry(getResource("default_texture.png"))
    texture(getResource("default_model.geo.json"))
    sound("block.sand.fall") { }
}
````

Why don't use an Entity?

For a static display, blocks are more efficient on the Minecraft Client/Server.