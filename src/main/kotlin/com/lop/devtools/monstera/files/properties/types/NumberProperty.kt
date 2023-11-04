package com.lop.devtools.monstera.files.properties.types

interface NumberProperty<T: Number>: GenericProperty<Number> {
    var range: Pair<T, T>
}