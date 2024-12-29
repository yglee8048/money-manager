package com.yg.money_manager.domain.vo

data class Percent(
    private val value: Float,
) {
    init {
        require(value in -1f..1f) {
            "Value must be between -1f & 1f"
        }
    }

    private val percent: Float = value * 100f

    override fun toString(): String {
        return String.format("%.2f", percent)
    }

    operator fun plus(other: Percent): Percent {
        return Percent(this.value + other.value)
    }

    operator fun minus(other: Percent): Percent {
        return Percent(this.value - other.value)
    }

    operator fun times(other: Percent): Percent {
        return Percent(this.value * other.value)
    }

    operator fun div(other: Percent): Percent {
        return Percent(this.value / other.value)
    }
}
