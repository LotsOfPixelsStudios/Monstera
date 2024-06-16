package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.beh.spawnrules.BehSpawnRules
import com.lop.devtools.monstera.files.beh.spawnrules.PopulationControl

open class SysSpawnRule(private val entityId: String, private val entityName: String, val addon: Addon) {
    private val file = BehSpawnRules()

    open fun populationControl(value: PopulationControl = PopulationControl.MONSTER) {
        file.description {
            identifier = entityId
            populationControl = value
        }
    }

    open fun condition(condition: BehSpawnRules.Condition.() -> Unit) {
        file.condition(condition)
    }

    open fun build() {
        if(file.descriptionData == null)
            populationControl()
        file.build(entityName, addon.config.paths.behSpawnRules)
    }
}