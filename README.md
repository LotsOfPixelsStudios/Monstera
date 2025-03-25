# Monstera 🌱

![](https://img.shields.io/github/v/release/LotsOfPixelsStudios/Monstera)


Status: We are still migrating, although we are mainly looking for bugs. For smaller projects, this project is usable in production.

A library and environment designed to streamline the development of Minecraft addons.

## Licence Notice 🎫

We have released this library under the GPL-3.0 licence, we are aware of the limitations of this licence, so we want to clarify
what we want to achieve with this licence:

- we don't want you to just copy and modify the library and possibly sell it as closed source
- We want you to create your own projects for Minecraft (using this library), which can of course also be closed source. This
  may mean that we have to change the licence to LGPL in the future.
- We explicitly allow the creation of closed source projects with this library with the goal of creating a Minecraft addon.

## Getting Started

See the template project at [https://github.com/LotsOfPixelsStudios/MonsteraTemplate](https://github.com/LotsOfPixelsStudios/MonsteraTemplate)

If you have already a kotlin project add the dependency

````gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.lotsofpixelsstudios:monstera:0.4.3")
}
````

## Goal 🏆

- design of an easy-to-use interface for a developer with kotlin to increase efficiency while coding.
- low level functions to interact with plugins

## Contributing

Note to all contributors: We may wish to change the licence as stated in the Licence section, therefore we as Lots of
Pixels Studios reserves the right to change the licence as we see fit without consulting you independently.

### Files 📁

In this package we reconstruct Minecraft files as they end up in the addon using a DSL.

For each type of file you will find a class that usually contains the format version in the inner class `FileHeader`, most of these classes extend the open class `MonsteraRawFile`.
This class contains a simple map where a developer can add missing keys that are needed. To prevent Gson from parsing the map name `additionalKeys`, a custom `JsonSerializer`
was written and applied to the defined Gson instance in the `MonsteraBuilder` object.

Different types of files you might encounter:

- The default file with the basic structure

```kotlin
class SomeFile : MonsteraBuildableFile, MonsteraRawFile() {

    //default for basic buildable files, returns the path where the final file was built to
    override fun build(filename: String, path: Path?, version: String?): Result<Path> { }

    data class FileHeader(

        //gson will take this name as the key for the json element
        @SerializedName("format_version")

        //gson is set to exlude every field that is missing this annotation
        @Expose

        //basic information for minecraft - is contained in the json output
        var formatVersion: String = "1.10.0",

        @SerializedName("minecraft:some_file")
        @Expose

        //this type adapter annotation tells gson to parse the additional keys seperatly
        @JsonAdapter(MonsteraRawFileTypeAdapter::class)
        var myFile: SomeFile
    ) : MonsteraRawFile()
}
```

- The default file without the file header class, this usually happens when the first key can be defined by the developer, in this case `myDefs` can contain custom keys

```kotlin
class SomeFile {
    override fun build(filename: String, path: Path?, version: String?): Result<Path> {  }

    @SerializedName("format_version")
    @Expose
    var formatVersion: String = "1.14.0"

    @SerializedName("some_defs")
    @Expose
    var myDefs: MutableMap<String, MyDataClass> = mutableMapOf()
        @MonsteraBuildSetter set
}

//To ensure that the developer can still modify keys, this class is open so that the developer
//can inherit and modify the class with more keys.
open class MyDataClass {  }
```

Addon specific files: files are seperated in the packages `beh` and `res` for behaviour and resource alike.
Exceptions are files that can live in both behaviour and resource pack:

- lang files / texts
- manifest
- animation controllers
- properties

### Addon

In this package we define higher level functionality for addons in general.

This should give the developer a more user-friendly interface to use, the developer should still be able to access raw file object as this might be necessary when creating files with
different format versions.

