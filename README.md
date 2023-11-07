# Monstera 🌱

![](https://img.shields.io/github/v/release/LotsOfPixelsStudios/Monstera)


State: We are still migrating, although we mainly look of bugs. For smaller projects usable in production

A library and environment designed to streamline the development of Minecraft addons.

## Licence Notice 🎫

We have released this library under the GPL-3.0 licence, we are aware of the limitations of this licence, so we want to clarify
what we want to achieve with this licence:

- we don't want you to just copy and modify the library and possibly sell it as closed source
- We want you to create your own projects for Minecraft (using this library), which can of course also be closed source. This
  may mean that we have to change the licence to LGPL in the future.
- We explicitly allow the creation of closed source projects with this library with the goal of creating a Minecraft addon.

## Goal 🏆

- design of an easy-to-use interface for a developer with kotlin to increase efficiency while coding.
- low level functions to interact with plugins

## Contributing

Note to all contributors: We may want to change the licence as stated under licence, therefor we as the company Lots of
Pixels Studios reserve our right to change the licence as we see fit without consulting you independently.

### Files 📁

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

### Addon

In this package we define higher level functionality for addons in general.

The following thought was in mind while creating the structure:

- Use Interfaces for the documentation for the developer that should use the Interface
- Use Abstract or Open class that implements the interface for the implementation so plugin developers can modify or 
add code the execution when creating for examples an own implementation of the entity  


### TODOs

- Not all classes in the files package use the `MonsteraFile` Interface
