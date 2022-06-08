package co.develhope.chooseyourownbeer.network

import co.develhope.chooseyourownbeer.network.dto.BeersResult
import retrofit2.http.GET
import retrofit2.http.Path

interface PunkService {
    @GET("{beers}")
    suspend fun listRepos(@Path("/beers") beers: String, PAGE_SIZE: Int): BeersResult
}