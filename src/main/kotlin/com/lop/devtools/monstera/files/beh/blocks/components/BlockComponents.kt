package com.lop.devtools.monstera.files.beh.blocks.components

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.HolidayCreatorFeature
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.MonsteraRawFileTypeAdapter
import com.lop.devtools.monstera.files.beh.tables.loot.BehLootTables
import com.lop.devtools.monstera.getMonsteraLogger
import java.awt.Color

class BlockComponents : MonsteraRawFile() {
    @SerializedName("minecraft:collision_box")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var collisionBoxData: Any? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * collisionBox {
     *     size()
     *     origin()
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun collisionBox(data: CollisionBox.() -> Unit) {
        collisionBoxData = if (collisionBoxData is CollisionBox) {
            (collisionBoxData as CollisionBox).apply(data)
        } else {
            CollisionBox().apply(data)
        }
    }

    /**
     * removes previously added collision box
     */
    @OptIn(MonsteraBuildSetter::class)
    fun disableCollisionBox() {
        collisionBoxData = false
    }

    @HolidayCreatorFeature(till = "1.19.50")
    @SerializedName("minecraft:crafting_table")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var craftingTableData: CraftingTable? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * craftingTable {
     *     tableName = "mykey.name"
     *     craftingTags()
     * }
     * ```
     */
    @HolidayCreatorFeature(till = "1.19.50")
    @OptIn(MonsteraBuildSetter::class)
    fun craftingTable(data: CraftingTable.() -> Unit) {
        craftingTableData = (craftingTableData ?: CraftingTable()).apply(data)
    }

    @SerializedName("minecraft:destructible_by_explosion")
    @Expose
    var destructibleByExplosionData: Any? = null
        @MonsteraBuildSetter set

    /**
     * How resistant your block is to being destroyed by explosions.
     *
     * ```
     * destructibleByExplosion {
     *     explosionResistance = 20
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun destructibleByExplosion(data: DestructibleByExplosion.() -> Unit) {
        destructibleByExplosionData = if (destructibleByExplosionData is DestructibleByExplosion) {
            (destructibleByExplosionData as DestructibleByExplosion).apply(data)
        } else {
            DestructibleByExplosion().apply(data)
        }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun disableDestructionByExplosion() {
        destructibleByExplosionData = false
    }

    @SerializedName("minecraft:destructible_by_mining")
    @Expose
    var destructibleByMiningData: Any? = null
        @MonsteraBuildSetter set

    /**
     * How resistant your block is to being destroyed by explosions.
     *
     * ```
     * destructibleByExplosion {
     *     explosionResistance = 20
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun destructibleByMining(data: DestructibleByMining.() -> Unit) {
        destructibleByMiningData = if (destructibleByMiningData is DestructibleByMining) {
            (destructibleByMiningData as DestructibleByMining).apply(data)
        } else {
            DestructibleByMining().apply(data)
        }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun disableDestructionByMining() {
        destructibleByMiningData = false
    }

    @SerializedName("minecraft:flammable")
    @Expose
    var flammableData: Any? = null
        @MonsteraBuildSetter set

    /**
     * How resistant your block is to being destroyed by explosions.
     *
     * ```
     * destructibleByExplosion {
     *     explosionResistance = 20
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun flammable(data: Flammable.() -> Unit) {
        flammableData = if (flammableData is Flammable) {
            (flammableData as Flammable).apply(data)
        } else {
            Flammable().apply(data)
        }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun disableFlammable() {
        flammableData = false
    }

    @SerializedName("minecraft:loot")
    @Expose
    var loot: String? = null

    /**
     * ```
     * table("name") {
     *     pool {  }
     * }
     * ```
     */
    fun table(tableName: String, data: BehLootTables.() -> Unit) {
        val lootTables = BehLootTables().apply(data)
        lootTables.debug(tableName)
        val target = BehLootTables.Block(lootTables).build(tableName)
        target.fold({
            loot = BehLootTables.resolveRelative(it)
        }, {
            getMonsteraLogger(this.javaClass.name).warn("Equipment table not added!")
        })
    }

    @SerializedName("minecraft:map_color")
    @Expose
    var mapColor: MutableList<Number>? = null

    fun mapColor(color: Color) {
        mapColor = arrayListOf(color.red, color.green, color.blue)
    }

    @SerializedName("minecraft:placement_filter")
    @Expose
    @HolidayCreatorFeature(till = "1.19.60")
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var placementFilterData: PlacementFilter? = null
        @MonsteraBuildSetter set

    /**
     * Configure conditions for a block to be able to survive. If these conditions are not met, the block will not be placed, or if the block is already placed, it will pop off.
     * ```
     * placementFilter {
     *     condition { }
     * }
     * ```
     */
    @HolidayCreatorFeature(till = "1.19.60")
    @OptIn(MonsteraBuildSetter::class)
    fun placementFilter(data: PlacementFilter.() -> Unit) {
        placementFilterData = (placementFilterData ?: PlacementFilter()).apply(data)
    }

    @SerializedName("minecraft:selection_box")
    @Expose
    var selectionBoxData: Any? = null
        @MonsteraBuildSetter set

    /**
     * The selectable area (hitbox) of the block, measured in pixels - must be contained within the block unit (16×16×16).
     * ```
     * selectionBox {
     *     size()
     *     origin()
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    @HolidayCreatorFeature(till = "1.19.60")
    fun selectionBox(data: SelectionBox.() -> Unit) {
        selectionBoxData = if (selectionBoxData is SelectionBox) {
            (selectionBoxData as SelectionBox).apply(data)
        } else {
            SelectionBox().apply(data)
        }
    }

    @OptIn(MonsteraBuildSetter::class)
    fun disableSelectionBox() {
        selectionBoxData = false
    }

    @SerializedName("minecraft:transformation")
    @Expose
    @JsonAdapter(MonsteraRawFileTypeAdapter::class)
    var transformationData: Transformation? = null
        @MonsteraBuildSetter set


    /**
     * Allows for translation (movement), scaling and rotation of blocks - both visual and functional.
     * Transformed models must not exceed the block geometry limits.
     *
     * ```
     * transformation {
     *     translation(-5, 8, 0)
     *     rotation(90, 180, 0)
     *     scale(0.5, 1, 0.5)
     * }
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun transformation(data: Transformation.() -> Unit) {
        transformationData = (transformationData ?: Transformation()).apply(data)
    }

    @SerializedName("minecraft:light_dampening")
    @Expose
    var lightDampening: Number? = null

    @SerializedName("minecraft:light_emission")
    @Expose
    var lightEmission: Number? = null

    @SerializedName("minecraft:friction")
    @Expose
    var friction: Number? = null

    @SerializedName("minecraft:display_name")
    @Expose
    var displayName: String? = null

    //may todo bone visibilty conditions if needed
    @SerializedName("minecraft:geometry")
    @Expose
    var geometry: String? = null

    @SerializedName("minecraft:material_instances")
    @Expose
    var materialInstance: MutableMap<String, MaterialSettings>? = null
        @MonsteraBuildSetter set

    /**
     * ```
     * all("path")
     * site("*", "path")
     * ```
     */
    @OptIn(MonsteraBuildSetter::class)
    fun materialInstance(data: MaterialInstance.() -> Unit) {
        materialInstance = (materialInstance
            ?: mutableMapOf()).also { it.putAll(MaterialInstance().apply(data).materialSettingsSite) }
    }
}