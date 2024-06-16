package com.lop.devtools.monstera.addon.block

import com.lop.devtools.monstera.addon.buildTestAddon
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.addon.testAddon
import com.lop.devtools.monstera.files.getResource
import kotlin.test.AfterTest
import kotlin.test.Test

class PermutationBlock {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }

    @Test
    fun testPermutationBlock() = testAddon {
        block("test_permutation_block", "Permutation Block") {
            val stateName = "sample_int_state"

            state(stateName, 1, 2, 3, 4, 5, 6)
            permutation(Query.blockState(stateName)) {
                geometry = blockGeometry(getResource("default_model.geo.json"))
            }
        }
    }
}