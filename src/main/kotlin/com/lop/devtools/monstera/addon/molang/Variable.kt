package com.lop.devtools.monstera.addon.molang

/**
 * add or get a Variable
 *
 * ```
 * Variable.new("my_var", Query.isBaby)
 * Variable.new("my_var" to Query.isBaby)
 *
 * Variable["my_var"]
 * Variable.get("my_var")
 * ```
 */
class Variable(override var data: String) : Molang {
    companion object {
        fun new(name: String, value: Molang): Variable {
            return Variable("variable.$name=$value;")
        }

        fun new(pair: Pair<String, Molang>): Variable {
            return new(pair.first, pair.second)
        }

        operator fun get(name: String): Variable {
            return Variable("variable.$name")
        }
    }

    override operator fun not(): Query {
        return Query("!" + this.data)
    }

    override operator fun times(data: Any): Math {
        return Math("(${this.data} * $data)")
    }

    override operator fun plus(data: Any): Math {
        return Math("(${this.data} + $data)")
    }

    override operator fun minus(data: Any): Math {
        return Math("(${this.data} - $data)")
    }

    override operator fun div(data: Any): Math {
        return Math("(${this.data} / $data)")
    }

    override fun toString(): String {
        return data
    }
}