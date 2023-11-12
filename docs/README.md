# :fa-brands fa-pagelines: Monstera

![](https://img.shields.io/github/v/release/LotsOfPixelsStudios/Monstera)


Status: We are still migrating, although we are mainly looking for bugs. For smaller projects, this project is usable in production.

A library and environment designed to streamline the development of Minecraft addons.

## :fa-solid fa-scale-balanced: Licence Notice

We have released this library under the GPL-3.0 licence, we are aware of the limitations of this licence, so we want to clarify
what we want to achieve with this licence:

- we don't want you to just copy and modify the library and possibly sell it as closed source
- We want you to create your own projects for Minecraft (using this library), which can of course also be closed source. This
  may mean that we have to change the licence to LGPL in the future.
- We explicitly allow the creation of closed source projects with this library with the goal of creating a Minecraft addon.

## :fa-solid fa-rocket: Getting Started

See the template project at [https://github.com/LotsOfPixelsStudios/MonsteraTemplate](https://github.com/LotsOfPixelsStudios/MonsteraTemplate)

If you have already a kotlin project add the dependency

````gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.lotsofpixelsstudios:monstera:0.2.0")
}
````

## :fa-solid fa-trophy: Goal

- design of an easy-to-use interface for a developer with kotlin to increase efficiency while coding.
- low level functions to interact with plugins

## :fa-solid fa-handshake: Contributing 

Note to all contributors: We may wish to change the licence as stated in the Licence section, therefore we as Lots of
Pixels Studios reserves the right to change the licence as we see fit without consulting you independently.

### :fa-solid fa-file-lines: Files 

In this package we reconstruct minecraft files as they land in the addon with the help of a DSL.

Each class should extend the `MonsteraFile` Interface with an inner class called `Unsafe` to interact with 
variables that are usually private. The goal of Monstera is to be flexible which mean that a plugin should be able to 
delete for example keys from the data maps.

Builder: The `Builder.kt` has a Gson reference to build the json files. This is the main Builder for all json files.

Addon specific files: files are seperated in the packages `beh` and `res` for behaviour and resource alike.
Exceptions are files that can live in both behaviour and resource pack:

- lang files / texts
- manifest
- animation controllers
- properties

### :fa-solid fa-puzzle-piece: Addon 

In this package we define higher level functionality for addons in general.

The following thought was in mind while creating the structure:

- Use Interfaces for the documentation for the developer that should use the Interface
- Use an Abstract or Open class that implements the interface for the implementation so plugin developers can modify or 
add code the execution when creating for examples an own implementation of the entity  


### TODOs

- Not all classes in the files package use the `MonsteraFile` Interface
