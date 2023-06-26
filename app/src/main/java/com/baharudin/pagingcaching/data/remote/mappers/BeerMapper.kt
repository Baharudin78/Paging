package com.baharudin.pagingcaching.data.remote.mappers

import com.baharudin.pagingcaching.data.local.model.BeerEntity
import com.baharudin.pagingcaching.data.remote.dto.BeerDto
import com.baharudin.pagingcaching.domain.Beer

fun BeerDto.toBeerEntity() : BeerEntity{
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer() : Beer{
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}