# Sounds

## :fa-solid fa-code: Sound API

Blocks and Entities use the sound API, it should help to creating sounds more efficently. 
Note: You can call the `sound { }` multiple times for multiple sounds.

### Basic Usage:

On an example on the block sound API

````kotlin
sound("block.sand.fall") {
    pitch = 1f to 1.2f   //default 1 to 1
    volume = 0.7f to 1f  //default 1 to 1
    maxDistance = 16
    minDistance = 2
    category = SoundCategory.BLOCK //default
    sound(getResource(/*...*/))  //load a single ogg file with optional settings, see docs of [Sounds.sound()], can be called multiple times
    onEvent(event = SoundEvent.DEFAULT, pitch = 1 to 2, volume = 0.7f to 1)
    onEvent(event = SoundEvent.PLACE, pitch = 0.9f to 1, volume = 0.7f to 1)

    //you can also import sounds definitions with some limitations like
    importSound("ambient.basalt_deltas.loop") //note: this will overwrite the identifier if one is set!

    //or define multiple Sounds that will be randomly selected
    sound(arrayListOf(
        getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.2f, pitch = 1, weight = 3),
        getResource("file.ogg") to SoundDefData(is3D = false, volume = 1.1f, pitch = 1, weight = 2),
        getResource("file.ogg") to SoundDefData() //use default values
    ))
 }
````

## :fa-solid fa-volume-high: Classic Sounds

If you have problems and opt to reading the [wiki](https://wiki.bedrock.dev/concepts/sounds.html) this approach can help
as it resembles what you learn there.

Sounds are split up into 2 main components:

- Sound Definitions: load the sound from a file, set a base pitch/volume
- Category Sounds: define on which event the sound is triggered, also apply a custom volume/pitch

````kotlin
sounds {
    soundsDefinitions { }
    categorySounds { }
}
````

### Sound Definitions

````kotlin
soundsDefinitions {
    newSoundDef("tp.test.drive") {
        category(SoundCategory.BLOCK)
        maxDistance(2f)
        minDistance(1f)

        //can be called multiple times
        sound {
            name(File("test.ogg"), this@addon)
            volume(1f)
            pitch(1f)
        }
        sound(File("test.ogg"), this@addon)
        sound(File("test.ogg"), this@addon)
    }
}
````

### Category Sounds

````kotlin
categorySounds {
    blockSounds {
        soundEventGroup("test") {
            event(SoundEvent.ATTACK) {
                sound("test.sound")
                volume(0.2f)
            }
            event(SoundEvent.AMBIENT) {
                sound("test2.sound")
                volume(0.2f)
                pitch(0.3f)
            }
        }
        soundEventGroup("test2") {
            event(SoundEvent.SCREAM) { }
        }
    }
}
````
