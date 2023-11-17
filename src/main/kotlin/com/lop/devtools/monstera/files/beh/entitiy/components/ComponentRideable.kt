package com.lop.devtools.monstera.files.beh.entitiy.components

import com.lop.devtools.monstera.Config
import com.lop.devtools.monstera.addon.api.MonsteraFile
import com.lop.devtools.monstera.addon.api.MonsteraUnsafeMap
import com.lop.devtools.monstera.getMonsteraLogger

class ComponentRideable : MonsteraFile {
    override val unsafe = Unsafe()
    private fun logger() = getMonsteraLogger("Component Ride")

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()
        val seats: CompSeats = CompSeats()

        override fun getData(): MutableMap<String, Any> {
            seatCount?.let { general["seat_count"] = it }
            pullInEntities?.let { general["pull_in_entities"] = it }
            crouchingSkipInteract?.let { general["crouching_skip_interact"] = it }
            controllingSeat?.let { general["controlling_seat"] = it }
            familyTypes?.let { general["family_types"] = it }
            riderCanInteract?.let { general["rider_can_interact"] = it }

            if (general.containsKey("family_types")) {
                val types = general["family_types"] as ArrayList<*>
                //stream of all entries matched if player is present -> if so only execute warnings once
                types.firstOrNull { it == "player" }?.apply {
                    if (!general.containsKey("interact_text") && !ignoreInteractText) {
                        logger().warn(
                            "no interact text found: Players with touch screen can't interact, add " +
                                    "interactText() to you're rideable component or ignore it with ignoreInteractText()"
                        )
                    }
                    if (!ignoreExitText && !setExitText) {
                        logger().warn("no exit text found: Players may not know how to leave the current entity")
                    }
                }
            }

            val seatsData = seats.getData()
            if(seatsData.isNotEmpty())
                general["seats"] = seatsData

            return general
        }
    }

    @Deprecated("", ReplaceWith("unsafe.general"))
    val general = mutableMapOf<String, Any>()
    var ignoreInteractText = false
    var ignoreExitText = false
    private var setExitText = false

    var seatCount: Int? = null
    var pullInEntities: Boolean? = null
    var crouchingSkipInteract: Boolean? = null
    var controllingSeat: Int? = null
    var familyTypes: ArrayList<String>? = null
        set(value) {
            if (field == null)
                field = value
            else if (value != null)
                field!!.addAll(value)
        }
    var riderCanInteract: Boolean? = null

    /**
     * 0..1
     *
     * The text that will display when interacting with this entity with Touch-screen controls,
     */
    fun interactText(
        displayName: String? = null,
        key: String = "action.interact." + displayName?.replace(" ", "_")?.lowercase(),
        config: Config? = null
    ) {
        unsafe.general["interact_text"] = key
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
        config: Config? = null
    ) {
        setExitText = true
        config?.langFileBuilder?.addonRes?.add(key, displayText)
    }

    fun ignoreInteractText() {
        ignoreInteractText = true
    }

    fun seats(value: CompSeats.() -> Unit) {
        unsafe.seats.apply(value)
    }
}

class CompSeats {
    val general = arrayListOf<Any>()

    fun seat(value: CompSeat.() -> Unit) {
        general.add(CompSeat().apply(value).unsafe.getData())
    }

    fun getData(): ArrayList<Any> {
        return general
    }
}

class CompSeat : MonsteraFile {
    val general = mutableMapOf<String, Any>()

    override val unsafe = Unsafe()

    inner class Unsafe : MonsteraUnsafeMap {
        val general = mutableMapOf<String, Any>()

        override fun getData(): MutableMap<String, Any> {
            minRiderCount?.let { general["min_rider_count"] = it }
            maxRiderCount?.let { general["max_rider_count"] = it }
            rotateRiderBy?.let { general["rotate_rider_by"] = it }
            rotateRiderByQuery?.let { general["rotate_rider_by"] = it }
            lockRiderRotation?.let { general["lock_rider_rotation"] = it }

            return general
        }
    }

    var minRiderCount: Int? = null
    var maxRiderCount: Int? = null
    var rotateRiderBy: Int? = null
    var rotateRiderByQuery: String? = null
    var lockRiderRotation: Number? = null

    fun position(x: Float, y: Float, z: Float) {
        general["position"] = arrayListOf(x, y, z)
    }
}