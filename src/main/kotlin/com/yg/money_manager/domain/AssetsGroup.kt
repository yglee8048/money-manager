package com.yg.money_manager.domain

class AssetsGroup(
    val id: Long,
    val name: String,
    val bankName: String,
    val isTaxCut: Boolean,
    val isPension: Boolean,
    val assets: List<Asset>,
) {
    fun getPrincipalValue(): Long = assets.sumOf { it.principalValue }

    fun getPresentValue(): Long = assets.sumOf { it.getPresentValue() }

    fun getEarningsRate(): Float {
        val earnings = getPresentValue() - getPrincipalValue()
        return earnings.toFloat() / getPrincipalValue() * 100f
    }
}
