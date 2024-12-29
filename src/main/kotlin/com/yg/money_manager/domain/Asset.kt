package com.yg.money_manager.domain

abstract class Asset(
    val id: Long,
    val groupId: Long,
) {
    abstract var name: String
    abstract var principalValue: Long
    abstract var foreignExchangeExposure: Boolean
    abstract var volatility: Float
    abstract var expectedEarningsRate: Float

    fun getEarningsRate(): Float {
        val earnings = getPresentValue() - principalValue
        return earnings.toFloat() / principalValue.toFloat() * 100f
    }

    abstract fun getPresentValue(): Long
}
