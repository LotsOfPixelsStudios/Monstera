package com.lop.devtools.monstera

import com.lop.devtools.monstera.addon.addon
import com.lop.devtools.monstera.files.getResource
import java.awt.Color
import kotlin.test.Test

class Integration {
    @Test
    fun intTest() {
        val conf1 = loadConfig(getResource("monstera.json"), getResource("monstera-local.json")).getOrElse {
            getMonsteraLogger("addon").error(it.message)
            it.printStackTrace()
            return
        }

        val conf2 = config("my_test") {
            namespace = "monstera"
            projectShort = "te"
        }

        addon(
            conf1
        ) {
            entity("my_test_entity", "My Test Entity") {
                behaviour {
                    components {
                        physics { }
                        pushable { }
                    }
                    properties {
                        enum("1rwong_start") {
                            default("abc")
                            values = mutableListOf("abc", "ab")
                        }
                        enum("missing_default") {
                            values = mutableListOf("a")
                        }
                        enum("missing_values") {
                            default("ab")
                        }
                    }

                    craftingRecipe {

                    }
                }
                resource {
                    components {
                        spawnEgg("Spawn $name") {
                            eggByColor(Color.CYAN, Color.BLACK)
                        }
                    }
                }
            }
            entity("aververyverlongnametahtisshurlytoolongthanks") {

            }
            item("my_item", "My Item") {

            }
        }
    }
}