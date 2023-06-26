package com.baharudin.pagingcaching.data.remote.service

import com.baharudin.pagingcaching.data.remote.dto.BeerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page : Int,
        @Query("per_page") pageCount : Int
    ) : List<BeerDto>
}