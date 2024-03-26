@file:Suppress("unused")

package com.lop.devtools.monstera.addon.molang

interface Molang {
    var data: String

    operator fun plus(data: Any): Math
    operator fun minus(data: Any): Math
    operator fun times(data: Any): Math
    operator fun div(data: Any): Math
    operator fun not(): Query

}

fun Molang.buildMolang(data: Any, operator: String): Query {
    if(this.data.endsWith(")")) {
        this.data = this.data.removeSuffix(")")
        return Query("${this.data} $operator $data)")
    }
    return Query("(${this.data} $operator $data)")
}

infix fun Molang.and(data: Any): Molang {
    return Query("(${this.data} && $data)")
}

infix fun Molang.or(data: Any): Molang {
    return Query("(${this.data} || $data)")
}

infix fun Molang.eq(data: Any): Molang {
    return buildMolang(data, "==")
}

infix fun Molang.equals(data: Any): Molang {
    return buildMolang(data, "==")
}

infix fun Molang.neq(data: Any): Molang {
    return buildMolang(data, "!=")
}

infix fun Molang.notEquals(data: Any): Molang {
    return buildMolang(data, "!=")
}

infix fun Molang.sm(data: Any): Molang {
    return buildMolang(data, "<")
}

infix fun Molang.smaller(data: Any): Molang {
    return buildMolang(data, "<")
}

infix fun Molang.less(data: Any): Molang {
    return buildMolang(data, "<")
}

infix fun Molang.lt(data: Any): Molang {
    return buildMolang(data, "<")
}

infix fun Molang.seq(data: Any): Molang {
    return buildMolang(data, "<=")
}

infix fun Molang.smallerEquals(data: Any): Molang {
    return buildMolang(data, "<=")
}

infix fun Molang.lessEquals(data: Any): Molang {
    return buildMolang(data, "<=")
}

infix fun Molang.bg(data: Any): Molang {
    return buildMolang(data, ">")
}

infix fun Molang.gt(data: Any): Molang {
    return buildMolang(data, ">")
}

infix fun Molang.bigger(data: Any): Molang {
    return buildMolang(data, ">")
}

infix fun Molang.beq(data: Any): Molang {
    return buildMolang(data, ">=")
}

infix fun Molang.biggerEquals(data: Any): Molang {
    return buildMolang(data, ">=")
}

infix fun Molang.greaterEquals(data: Any): Molang {
    return buildMolang(data, ">=")
}