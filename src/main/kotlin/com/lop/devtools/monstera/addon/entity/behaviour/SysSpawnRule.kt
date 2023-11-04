package com.lop.devtools.monstera.addon.entity.behaviour

import com.lop.devtools.monstera.addon.Addon
import com.lop.devtools.monstera.files.beh.spawnrules.BehSpawnRules
import com.lop.devtools.monstera.files.beh.spawnrules.PopulationControl
import com.lop.devtools.monstera.files.beh.spawnrules.conditions.BehSpawnRulesCondition

class SysSpawnRule(private val entityId: String, private val entityName: String, val addon: Addon) {
    private var populationControl: PopulationControl = PopulationControl.MONSTER
    private val condsList = arrayListOf<Any>()

    fun populationControl(value: PopulationControl = PopulationControl.MONSTER) {
        populationControl = value
    }

    fun condition(condition: BehSpawnRulesCondition.() -> Unit) {
        condsList.add(BehSpawnRulesCondition().apply(condition).getData())
    }

    fun build() {
        BehSpawnRules().apply {
            description(entityId, populationControl)
            unsafe.condList.add(condsList)
        }.unsafe.build(entityName, addon.config.paths.behSpawnRules)
    }
}