# Creating an Addon

## Basics

Within the `Main.kt` is already an addon structure given and hints on what is possible.

### Addon Config

To create an addon, Monstera needs some basic information in the form of a config, if no config exists as in the 
template, one will be created for you when you run the program once. In the console or std in/out Monstera will ask for 
the project name and short, other information can be edited directly in the config.

````kotlin
val conf = addon(loadConfig().getOrElse {
    it.printStackTrace()
    return      //something went wrong loading the config, for example an I/O error
}) {
    //addon body
}
````

### :fa-solid fa-screwdriver-wrench: Modifications of the Config

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

If an error like this happens, have a look at the monstera-local.json, there is the minecraft path that Monstera 
assumed, you can change this path so that it points to the correct directory.

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

The JSON UI is not currently in the latest builds and is not currently being worked on, if you are interested open a 
discussion.