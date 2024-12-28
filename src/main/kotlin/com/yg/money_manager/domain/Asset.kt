package com.yg.money_manager.domain

import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

abstract class Assets {
    abstract val name: String
    abstract val principalValue: Long
    abstract val foreignExchangeExposure: Boolean
    abstract val volatility: Float

    abstract fun getProfits(): Long
    abstract fun getPresentValue(): Long
}

class VariableAssets(
    override val name: String,
    override val principalValue: Long,
    override val foreignExchangeExposure: Boolean,
    override val volatility: Float,
    private val presentValue: Long,
) : Assets() {

    override fun getProfits(): Long {
        return presentValue - principalValue
    }

    override fun getPresentValue(): Long {
        return presentValue
    }
}

data class FixedAssets(
    override val name: String,
    override val principalValue: Long,
    override val foreignExchangeExposure: Boolean,
    override val volatility: Float,
    val interestRate: Float,
    val startDate: LocalDate,
    val endDate: LocalDate?,
) : Assets() {

    override fun getProfits(): Long {
        val period = endDate
            ?.let {
                Period.between(it, startDate).get(ChronoUnit.DAYS).toDouble() / 365f
            } ?: 1.0

        return (principalValue * period * interestRate).toLong()
    }

    override fun getPresentValue(): Long {
        return principalValue + getProfits()
    }
}