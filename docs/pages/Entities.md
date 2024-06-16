# Entities

## :fa-solid fa-paperclip: Basic

Entities can be defined in one place.
In Minecraft you have different files like resource entity, behavior entity, animations, animation controllers,
textures, geometries, ...

In Monstera you can define the name of the entity and then further specify the entities via resources and behavior
functions.
This can be used to build up an own structure that fits the development style.

````kotlin
entity(name = "my_entity", displayName = "§aMy Entity") {
    behaviour {

    }
    resource {

    }
}
````

Note: To get a Spawn Egg within the Creative Inventory, a spawn egg must be defined. See more
under further down.

## :fa-solid fa-code: Behaviour

### Components

Components are the building blocks for the behaviour of the entity. So we can give the entity the ability to jump, fly,
walk or attack.

Here are some example Components:

```kotlin
behaviour {
    components {
        physics {
            hasGravity = true
            hasCollision = true
        }
        familyTypes = arrayListOf("mob", "animal")
    }
}
```

### Component Groups

In Component Groups we can now define components that aren't present at all time. So we can add remove and overwrite
components.

```kotlin
behaviour {
    componentGroup("baby") {
        isBaby()
    }
    componentGroup("adult") {
        attack {
            damage = 3
            damageRange = arrayListOf(1, 2)
        }
        behMeleeAttack {
            priority = 1
        }
    }
    components {
        physics { }
    }
}
```

We can give each component group a name to keep track of them.

### Events

To add and remove Component Groups we can use events by calling within the entity context:

````kotlin
behaviour {
    event("grow_up") {
        remove {
            componentGroups("baby", "scared")
        }
        add {
            componentGroups("adult")
        }
    }
}
````

#### Default Events

Following default events are also support:

````kotlin
behaviour {
    eventBorn { }
    eventSpawned { }
    eventOnPrime { }
    eventTransformed { }
}
````

### Properties

You can save data on Entities with properties, see
the [documentation](https://learn.microsoft.com/en-us/minecraft/creator/documents/introductiontoentityproperties)
for more info.

#### Create new Properties:

````kotlin
behaviour {
    properties {
        enum("prop_name_1") { }
        bool("prop_name_2") { }
        int("prop_name_3") { }
        float("prop_name_4") { }
    }
}
````

You always have to define a default value and to each type some sepsifc values:

- Enum (saves a state)

````kotlin
enum("my_enum_prop") {
    default("sit")  //could also be a Molang Expression/Query
    values("sit", "stand")
    clientSync = true   //access this property in the resource pack
}
````
The enum property has some restrictions on values but Monstera will warn you if any rule is violated.
Restrictions include: values can't exceed 16, value name length is between 1 and 32 and value name has to start with an 
alphabetic character.

- Bool  (saves true or false)

````kotlin
bool("my_bool_prop") {
    default(Query.isBaby)
    clientSync = false
}
````

- Int   (saves a number)

````kotlin
int("my_int_property") {
    default(1)
    range = 1 to 10
    clientSync = true
}
````

- Float (saves a floating point number)

````kotlin
int("my_float_property") {
    default(1f)
    range = 1f to 10f
    clientSync = true
}
````

#### Modify property value with Events

To modify properties with events access the proprty by name within a sepcifc event:

````kotlin
events {
    event("my_event") {
        setProperty("my_bool_prop", true)
    }
}
````

#### Access Properties

- Query: `Query.property("my_bool_property")` & `Query.hasProperty("my_bool_property")`
- Filter: 

````kotlin
components {
    environmentSensor {
        trigger(event = "my_event") {
            allOf {
                hasProperty("==", Subject.SELF, "my_bool_property")
                boolProperty("my_bool_property", ">", Subject.SELF, 2)
            }
        }
    }
}
````

## :fa-solid fa-horse: Resource

### Blockbench Files

Monstera has also the option to load blockbench files, note: this is a std lib feature:

````kotlin
resource {
    loadBlockbenchFile(getResource("default_model.bbmodel"))
}
````

The geometry, texture and all animations will be available if there are any.
To access animations see the animations section.

If there are multiple textures defined, a Query is necessary to select the textures like:

````kotlin
resource {
    loadBlockbenchFile(getResource("default_model.bbmodel"), Query.variant)
}
````

### Textures & Geometries

To add a single texture add it like:

````kotlin
resource {
    textureLayer(getResource("entities/default_texture.png"), layerName = "default")
    geometryLayer(
        getResource("entities/default_model.geo.json"),
        layerName = "default"
    )    //it's currently only possible to add 1 geometry layer, use render parts if you need more
}
````

The `layerName` always defaults to `default`.

Monstera will only generate one texture if the same file is used between entities. But it's still possible to use
raw textures if for example a vanilla texture is needed:

````kotlin
resource {
    textureLayer(texturePath = "textures/entity/cat/jellie", layerName = "default")
    geometryLayer(
        geoId = "geometry.cat",
        layerName = "default"
    )    //it's currently only possible to add 1 geometry layer, use render parts if you need more
}
````

To add multiple textures that aren't rendered at the same time we can use:

````kotlin
resource {
    textureLayer(
        arrayListOf(
            getResource("entities/default_texture_1.png"),
            getResource("entities/default_texture_2.png"),
            getResource("entities/default_texture_3.png")
        ),
        query = Query.variant,
        layerName = "default"
    )

    textureLayer(
        arrayListOf(
            getResource("entities/default_model_1.geo.json"),
            getResource("entities/default_model_2.geo.json"),
            getResource("entities/default_model_3.geo.json")
        ),
        query = Query.variant,
        layerName = "default"
    )
}
````

### Render Parts

You may want to have access to multiple render controllers while adding textures and want to render certain textures and
geometries only on some conditions.

Therefor you can call:

````kotlin
resource {
    renderPart(partName = "outer", query = !Query.isSheared and Query.isBaby) {
        material = "parrot"
        textureLayer(getResource("default_texture.png"))
        geometryLayer(getResource("default_model.geo.json"))
    }
}
````

You can define multiple render parts. The default render controller is build if no valid render part is defined or if 
a texture or geometry was assigned in the top level (`resource { textureLayer(getResource("...") ) }`).

### Animations and Controllers

To add animations load the file(s) like:

````kotlin
resource {
    animation(getResource("entities/cat.animation.json"))
}
````

Now we can use all animations within this file. We identify the animations with the last 'word' when thinking of words
seperated through points '.'.

Example: the identifier of the animation is `animation.cat.walk` so we can use `walk` as a valid animation in the
controller.

Controller:

````kotlin
resource {
    animationController(name = "movement") {
        animStates {
            initialState = "default"
            animState("default") {
                transitions {
                    transition("walk", Query.isMoving)
                    transition("test", "0")
                }
            }
            animState("walk") {
                animation = arrayListOf("walk")
            }
            animState("test") {}
        }
    }
}
````

### Notes:

- If you want to display the entity within the creative inventory you **must** define a spawn egg

### Spawn Egg

To define a Spawn Egg add to the resource part a `components` context:

````kotlin
resource {
    components {
        spawnEgg {
            displayItemName = "Spawn Plane" //will be set automatically to 'Spawn <displayName>'
            eggByColor(Color.BLACK, Color.RED)
        }
    }
}
````

There a multiple ways to define the spawn egg:

````kotlin
eggByTexture(texture = "spawn_egg", textureIndex = 2)   //vanilla spawn egg
eggByColor(Color.BLACK, Color.RED)                      //vanilla spawn egg with custom colors
eggByTexture(textureName = "pig", path = "textures/items/pig")  //use a predefined texture
eggByFile(file = getResource("icons/pig.icon.png"))     //use a custom texture
````

### Other Components

All other components can be called like the spawnEgg like:

````kotlin
resource {
    components {
        //...
    }
}
````

List of possible functions:

| function         | description                                                    | type     |
|------------------|----------------------------------------------------------------|----------|
| spawnEgg         | define a spawn egg for the entity                              | function |
| particleEffects  | add a particle effect                                          | function | 
| soundEffects     | add a sound effect                                             | function |
| locators         | define the attach point for the leash                          | function |
| scripts          | define scripts, like preAnimation & variables                  | function |
| enableAttachment | define if the entity can wear armor                            | variable |
| material         | define which material the entity is, default `parrot`          | variable | 
| disableMaterial  | as the default is `parrot` you can disable it, default `false` | variable |

#### Define more:

If you want to modify for example the `mineEngineVersion` within the entity you can use:

````kotlin
components {
    unsafe.entity.description {
        mineEngineVersion(version = "1.10.0")   //default is `1.8.0`
    }
}
````

### Material (not fully implemented)

To add a new Material you can modify an existing one like this:

````kotlin
components {
    material = newMaterial(Material.ENTITY) {   //modify the Entity Material and creates a new one
        defines(MaterialDefinition.BLENDING)    //defines Bleding as a sample
    }
}
````

[ Further reading ](https://wiki.bedrock.dev/visuals/materials.html)

### Crafting

Can be found in the behaviour part of the entity.

```kotlin
craftingRecipe {
    val d = "minecraft:diamond"
    val s = "minecraft:stick"
    val n = ""

    craftingPattern(
        t(n,d,n),
        t(n,d,n),
        t(n,s,n)
    )
    unlock {
        item("minecraft:wood", count = 3, data = 2)
        context()
    }
}
```