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
    }
}