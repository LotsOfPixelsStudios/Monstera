package com.lop.devtools.monstera.addon.molang

@Suppress("unused")
class Math(override var data: String): Molang {
    companion object {
        /**
         * Absolute value of value
         */
        fun abs(value: Any): Math {
            return Math("math.abs($value)")
        }

        /**
         * Round value up to nearest integral number
         */
        fun ceil(value: Any): Math {
            return Math("math.ceil($value)")
        }

        /**
         *Clamp value to between min and max inclusive
         */
        fun clamp(value: Any, min: Any, max: Any): Math {
            return Math("math.clamp($value, $min, $max)")
        }

        /**
         *Cosine (in degrees) of value
         */
        fun cos(value: Any): Math {
            return Math("math.cos($value)")
        }

        /**
         *Calculates e to the value'th power
         */
        fun exp(value: Any): Math {
            return Math("math.exp($value)")
        }

        /**
         *Round value down to nearest integral number
         */
        fun floor(value: Any): Math {
            return Math("math.floor($value)")
        }

        /**
         *Lerp from start to end via 0_to_1
         */
        fun lerp(start: Any, end: Any, OTol: Any): Math {
            return Math("math.lerp($start, $end, $OTol)")
        }

        /**
         *Lerp the shortest direction around a circle from start degrees to end degrees via 0_to_1
         */
        fun lerprotate(start: Any, end: Any, OTol: Any): Math {
            return Math("math.lerprotate($start, $end, $OTol)")
        }

        /**
         * Natural logarithm of value
         */
        fun ln(value: Any): Math {
            return Math("math.ln($value)")
        }

        /**
         *Return highest value of A or B
         */
        fun max(a: Any, b: Any): Math {
            return Math("math.max($a, $b)")
        }

        /**
         *Return lowest value of A or B
         */
        fun min(a: Any, b: Any): Math {
            return Math("math.min($a, $b)")
        }

        /**
         *Return the remainder of value / denominator
         */
        fun mod(value: Any, denominator: Any): Math {
            return Math("math.mod($value, $denominator)")
        }

        /**
         *Elevates base to the exponent'th power
         */
        fun pow(base: Any, exponent: Any): Math {
            return Math("math.pow($base, $exponent)")
        }

        /**
         *Random value between low and high inclusive
         */
        fun random(low: Any, high: Any): Math {
            return Math("math.random($low, $high)")
        }

        /**
         *Round value to nearest integral number
         */
        fun round(value: Any): Math {
            return Math("math.round($value)")
        }

        /**
         *Sine (in degrees) of value
         */
        fun sin(value: Any): Math {
            return Math("math.sin($value)")
        }

        /**
         *Square root of value
         */
        fun sqrt(value: Any): Math {
            return Math("math.sqrt($value)")
        }

        /**
         *Round value towards zero
         */
        fun trunc(value: Any): Math {
            return Math("math.trunc($value)")
        }
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

    override operator fun not(): Query {
        return Query("!" + this.data)
    }

    override fun toString(): String {
        return data
    }
}