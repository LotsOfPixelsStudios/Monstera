package com.lop.devtools.monstera

import com.lop.devtools.monstera.addon.addon
import java.awt.Color

fun main() {
    addon(config("testPrj") {
        description = "This is a Test Project"
        //packIcon = getResource("pack.png")
    }) {
        entity("my_test_entity", "My Test Entity") {
            behaviour {
                components {
                    physics {  }
                    pushable {  }
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
                    spawnEgg {
                        eggByColor(Color.CYAN, Color.BLACK)
                    }
                }
            }
        }
        entity("aververyverlongnametahtisshurlytoolongthanks") {

        }
        item("my_item", "My Item") {
            renderOffset("tools")
        }
    }
}