package com.yg.money_manager.domain

class VariableAsset(
    id: Long,
    groupId: Long,
    override var name: String,
    override var principalValue: Long,
    override var foreignExchangeExposure: Boolean,
    override var volatility: Float,
    override var expectedEarningsRate: Float,
    var presentValue: Long,
) : Asset(id, groupId) {

    override fun getPresentValue(): Long {
        return presentValue
    }
}
