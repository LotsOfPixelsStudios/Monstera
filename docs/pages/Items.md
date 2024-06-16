# Items

## :fa-solid fa-paperclip: Basic

Items are currently relatively simple, as geometries etc. are limited. 

````kotlin
item("my_item", "My Item") {
    renderOffset("tools")
    texture(getResource("test_item"))
    menuCategory {
        category = BehItem.Description.Category.EQUIPMENT
        grouo = "itemGroup.name.chestplate"
    }
    components {
        //...
    }
}
````

## Components

Make sure when creating weapons, to set `handEquipped = true` within the components.

````kotlin
components {
    allowOffHandData(true)
    armor { }
    blockPlacer { }
    canDestroyInCreative(true)
    cooldown { }
    cooldown(5, "attack")
    creativeCategory { }
    damage(5)
    digger { }
    durability { }
    enchantable { }
    entityPlacer { }
    food { }
    fuel { }
    glint = true
    handEquipped(true)
    hoverTextColor = "#ffffff"
    interactButton = true
    liquidClipped(true)
    maxStackSize(1)
    projectile { }
    record { }
    repairable { }
    shooter { }
    shouldDespawn(true)
    stackedByData(false)
    throwable { }
    useAnimation = "animation.item.draw"
    useModifiers { }
    wearable { }
    tags("armor")
}
````

