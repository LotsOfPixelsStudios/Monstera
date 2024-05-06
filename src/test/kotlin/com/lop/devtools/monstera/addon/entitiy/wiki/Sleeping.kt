package com.lop.devtools.monstera.addon.entitiy.wiki

import com.lop.devtools.monstera.addon.buildTestAddon
import kotlin.test.AfterTest

class Sleeping {
    @AfterTest
    fun buildTask() {
        buildTestAddon()
    }
    
    
}