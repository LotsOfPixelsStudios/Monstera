package com.lop.devtools.monstera.files.beh.entitiy.components.scraped

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.beh.entitiy.components.Components

class Rideable {
    @SerializedName("seat_count")
    @Expose
    var seatCount: Number? = null
        

    @SerializedName("passenger_max_width")
    @Expose
    var passengerMaxWidth: Number? = null
        

    @SerializedName("interact_text")
    @Expose
    var interactText: String? = null
        

    @SerializedName("pull_in_entities")
    @Expose
    var pullInEntities: Boolean? = null
        

    @SerializedName("seats")
    @Expose
    var seatsData: MutableList<Seats>? = null
        @MonsteraBuildSetter set

    /**
     *
     * ```
     * seats {
     *     minRiderCount = 0
     *     maxRiderCount = 1
     *     rotateRiderBy = -90
     *     lockRiderRotation = 90
     * }
     *```
     */
    @OptIn(MonsteraBuildSetter::class)
    @Components.VanillaComponentMarker
    fun seat(value: Seats.() -> Unit) {
        seatsData = (seatsData ?: mutableListOf()).also { it.add(Seats().apply(value)) }
    }

    @SerializedName("crouching_skip_interact")
    @Expose
    var crouchingSkipInteract: Boolean? = null
        

    @SerializedName("family_types")
    @Expose
    var familyTypesData: MutableList<String>? = null
        

    fun familyTypes(vararg value: String) {
        familyTypesData = (familyTypesData ?: mutableListOf()).also { it.addAll(value.toList()) }
    }

    /**
     * 0..1
     *
     * The text that will display when interacting with this entity with Touch-screen controls,
     */
    fun interactText(
        displayName: String? = null,
        key: String = "action.interact." + displayName?.replace(" ", "_")?.lowercase(),
        config: Config?
    ) {
        interactText = key
        if (displayName != null)
            config?.langFileBuilder?.addonRes?.add(key, displayName)
    }

    /**
     * 0..1
     *
     * @param displayText the text that will show when the player starts riding
     * @param key the key that will be set within the lang file, key looks like "action.hint.exit.$identifier"
     * while the identifier the entity identifier of this entity is
     */
    fun exitText(
        displayText: String,
        key: String,
        config: Config?
    ) {
        config?.langFileBuilder?.addonRes?.add(key, displayText)
    }

    class Seats {
        @SerializedName("position")
        @Expose
        var positionData: MutableList<Number>? = null
            

        @Components.VanillaComponentMarker
        fun position(vararg value: Number) {
            positionData = (positionData ?: mutableListOf()).also { it.addAll(value.toList()) }
        }

        @SerializedName("min_rider_count")
        @Expose
        var minRiderCount: Number? = null
            

        @SerializedName("max_rider_count")
        @Expose
        var maxRiderCount: Number? = null
            

        @SerializedName("rotate_rider_by")
        @Expose
        var rotateRiderBy: Number? = null
            

        @SerializedName("lock_rider_rotation")
        @Expose
        var lockRiderRotation: Number? = null
    }
}
