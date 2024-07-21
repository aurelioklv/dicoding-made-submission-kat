package com.aurelioklv.kat.core.utils.mapper

import com.aurelioklv.kat.core.domain.model.Weight
import com.aurelioklv.kat.core.data.remote.response.NetworkWeight

object NetworkWeightToModelMapper : Mapper<NetworkWeight?, Weight> {
    override fun map(from: NetworkWeight?): Weight {
        return Weight(
            imperial = from?.imperial ?: "N/A",
            metric = from?.metric ?: "N/A"
        )
    }
}