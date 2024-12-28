package com.yg.money_manager.domain

import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

abstract class Assets {
    abstract var name: String
    abstract var principalValue: Long
    abstract var foreignExchangeExposure: Boolean
    abstract var volatility: Float

    abstract fun getProfits(): Long
    abstract fun getPresentValue(): Long
}

class VariableAssets(
    override var name: String,
    override var principalValue: Long,
    override var foreignExchangeExposure: Boolean,
    override var volatility: Float,
    private var presentValue: Long,
) : Assets() {

    override fun getProfits(): Long {
        return presentValue - principalValue
    }

    override fun getPresentValue(): Long {
        return presentValue
    }
}

data class FixedAssets(
    override var name: String,
    override var principalValue: Long,
    override var foreignExchangeExposure: Boolean,
    override var volatility: Float,
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