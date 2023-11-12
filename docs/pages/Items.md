# Items

## :fa-solid fa-paperclip: Basic

Items are currently relatively simple, as geometries etc. are limited. 

````kotlin
item("my_item", "My Item") {
    renderOffset("tools")
    texture(getResource("test_item"))
    category("Equipment")
    components {
        //...
    }
}
````

## Components

Make sure when creating weapons, to set `handEquipped = true` within the components.

````kotlin
components {
    creativeInventory()
    maxStackSize = 1
    enchantable(10, "armor_torso")
    armor(2)
    repairable {}
    wearable(true, "slot.armor.chest")
    durability(0.2f, 150)
    maxDamage(2)
    handEquipped = false
    stackByData(false)
    foil(true)
    block("minecraft:grass")
    useDuration(32)
    food {}
}
````

