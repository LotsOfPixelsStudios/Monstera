package com.lop.devtools.monstera.addon.molang

infix fun String.eq(value: Any): String {
    return "$this == $value"
}

infix fun String.equals(value: Any): String {
    return "$this == $value"
}

infix fun String.neq(value: Any): String {
    return "$this != $value"
}

infix fun String.notEquals(value: Any): String {
    return "$this != $value"
}

infix fun String.sm(value: Any): String {
    return "$this < $value"
}

infix fun String.smaller(value: Any): String {
    return "$this < $value"
}

infix fun String.less(value: Any): String {
    return "$this < $value"
}

infix fun String.seq(value: Any): String {
    return "$this <= $value"
}

infix fun String.smallerEquals(value: Any): String {
    return "$this <= $value"
}

infix fun String.lessEquals(value: Any): String {
    return "$this <= $value"
}

infix fun String.bg(value: Any): String {
    return "$this > $value"
}

infix fun String.bigger(value: Any): String {
    return "$this > $value"
}

infix fun String.beq(value: Any): String {
    return "$this >= $value"
}

infix fun String.biggerEquals(value: Any): String {
    return "$this >= $value"
}

infix fun String.or(value: Any): String {
    return "$this || $value"
}

infix fun String.OR(value: Any): String {
    return "$this || $value"
}

infix fun String.and(value: Any): String {
    return "$this && $value"
}

infix fun String.AND(value: Any): String {
    return "$this && $value"
}

infix fun String.not(value: Any): String {
    return "$this !$value"
}

infix fun Query.not(value: Any): String {
    return "!$value"
}