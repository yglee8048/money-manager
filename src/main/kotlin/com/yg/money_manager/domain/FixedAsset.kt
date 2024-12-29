package com.yg.money_manager.domain

import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

class FixedAsset(
    id: Long,
    groupId: Long,
    override var name: String,
    override var principalValue: Long,
    override var foreignExchangeExposure: Boolean,
    override var volatility: Float,
    val interestRate: Float,
    val startDate: LocalDate,
    val endDate: LocalDate?,
) : Asset(id, groupId) {

    override var expectedEarningsRate: Float = super.getEarningsRate()

    override fun getPresentValue(): Long {
        return principalValue + getProfits()
    }

    private fun getProfits(): Long {
        val period = endDate
            ?.let {
                Period.between(it, startDate).get(ChronoUnit.DAYS).toDouble() / 365f
            } ?: 1.0

        return (principalValue * period * interestRate).toLong()
    }
}
