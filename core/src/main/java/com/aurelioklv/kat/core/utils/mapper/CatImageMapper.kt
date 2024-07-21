package com.aurelioklv.kat.core.utils.mapper

import com.aurelioklv.kat.core.domain.model.CatImage
import com.aurelioklv.kat.core.data.local.entity.CatImageEntity
import com.aurelioklv.kat.core.data.remote.response.NetworkCatImage

object NetworkCatImageToEntityMapper : Mapper<NetworkCatImage, CatImageEntity> {
    override fun map(from: NetworkCatImage): CatImageEntity {
        return CatImageEntity(
            id = from.id.toString(),
            url = from.url.toString(),
            width = from.width ?: 0,
            height = from.height ?: 0
        )
    }
}

object CatImageEntityToModelMapper : Mapper<CatImageEntity, CatImage> {
    override fun map(from: CatImageEntity): CatImage {
        return CatImage(
            id = from.id,
            url = from.url,
            width = from.width,
            height = from.height
        )
    }
}