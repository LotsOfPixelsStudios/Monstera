# Creating an Addon

## Basics

Within the `Main.kt` is already an addon structure given and hints on what is possible.

````kotlin
addon(config(projectName = "Template") {
    //config
    "npm.cmd run build".runCommand(File(System.getProperty("user.dir"), "scripting"))
    projectShort = "te"
    description = "sample description"
    packIcon = getResource("general/pack.png")
    world = getResource("world/template-world")
    version = arrayListOf(0, 1, 0)
    targetMcVersion = arrayListOf(1, 20, 0)
    scriptEntryFile = File("scripting", "dist", "src", "index.js")
    scriptingVersion = "1.6.0-beta"
}) {
    //addon body
}
````

Within the header of the addon function we can define some basics of the addon we want to have. The projectShort is
thereby also used as the namespace of entities etc.

Within the Body or the Addon Context we can define entities, items, etc. that will automatically be associated with the
addon.

## :fa-solid fa-screwdriver-wrench: Modifications

This section may be interesting for publishing the addon, as for example overwriting the end users version may be
a bit different as on the machine of the developer:

### version

The version of the addon defaults to `0.1.0`, if you decide to release a new version of the addon consider
bumping the version with the following principal: `<major>.<minor>.<patch>`. So bump the first number when releasing
or a braking change has happened (e.g. old world isn't usable after the update). The second number when a new 
feature/enhancement is released and third for small fixes.

### Target Mc Version

Modify the target Mc Version if you want to indicate that the addon was build in a older version of Minecraft.
Note: You may not use components of a newer Version then.

### My mc folder can't be found

This can happen if minecraft wasn't installed with the default parameters, change the direcotry like:

```kotlin
addon(/*...*/) {
    properties.comMojangPath =
        System.getenv("LOCALAPPDATA") + "\\Packages\\Microsoft.MinecraftUWP_8wekyb3d8bbwe\\LocalState\\games\\com.mojang"
}
```

## :fa-solid fa-hammer: Build the addon

### Local Build

As a default, the whole addon will be pushed to the minecraft folder after executing. Monstera will ensure that old
files
will be deleted with some limitations:

- can't delete if the projectName/projectShort has changed
- minecraft is running with the world open (this will produce an exception)

If you don't want to push the addon the minecraft folder, disable it by

````kotlin
addon(/*...*/) {
    buildToMcFolder = false
}
````

you can also disable other things like:

````kotlin
buildFunctions = false
buildTextureList = false
buildItemTextureIndex = false
buildToMcFolder = false
````

### Pipeline Build

The template has for both GitHub (Actions) and GitLab pipeline templates that can be modified to build and package the
addon.

Default is to add a Tag (GitLab) or Release (GitHub) to trigger the pipeline to add the package to the release/tag.

To correctly package the addon, see the Package page.

## :fa-solid fa-flask: Experimental Features

Currently, the JSON UI is experimental and may be discontinued as it will be deprecated in future mc versions.