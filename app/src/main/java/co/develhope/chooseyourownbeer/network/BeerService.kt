package co.develhope.chooseyourownbeer.network

import co.develhope.chooseyourownbeer.network.dto.BeersResult
import retrofit2.http.GET

interface BeerService {
    @GET("beers")
    suspend fun getBeersList(): BeersResult
}