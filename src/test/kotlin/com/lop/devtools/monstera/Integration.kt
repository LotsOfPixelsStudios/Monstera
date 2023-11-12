package com.lop.devtools.monstera

import com.lop.devtools.monstera.addon.addon
import com.lop.devtools.monstera.addon.molang.Math
import com.lop.devtools.monstera.addon.molang.Molang
import com.lop.devtools.monstera.addon.molang.Query
import com.lop.devtools.monstera.files.beh.entitiy.data.Subject
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
                    environmentSensor {
                        trigger(event = "my_event") {
                            allOf {
                                hasProperty("==", Subject.SELF, "my_bool_property")
                                intProperty("my_bool_property", ">", Subject.SELF, 2)
                            }
                        }
                    }
                }
                events {
                    event("my_event") {
                        setProperty("my_bool_prop", true)
                    }
                }
                properties {
                    enum("my_prop") {

                    }
                    bool("") {
                        default(Query.isBaby)

                    }
                    int("") {
                        default(1)
                        range = 1 to 10

                    }
                    float("") {}
                    Query.property("my_bool_property")
                    Query.hasProperty("my_bool_property")
                    Math
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