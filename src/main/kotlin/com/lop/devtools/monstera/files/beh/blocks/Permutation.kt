package com.lop.devtools.monstera.files.beh.blocks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lop.devtools.monstera.addon.api.MonsteraBuildSetter
import com.lop.devtools.monstera.files.MonsteraRawFile
import com.lop.devtools.monstera.files.beh.blocks.components.BlockComponents

class Permutation: MonsteraRawFile() {
    @SerializedName("condition")
    @Expose
    var condition: String? = null

    @SerializedName("components")
    @Expose
    var components: BlockComponents? = null
        @MonsteraBuildSetter set

    @OptIn(MonsteraBuildSetter::class)
    fun components(data: BlockComponents.() -> Unit) {
        components = (components ?: BlockComponents()).apply(data)
    }
}