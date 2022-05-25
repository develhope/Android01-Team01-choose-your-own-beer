package co.develhope.chooseyourownbeer.network

import retrofit2.http.GET
import retrofit2.http.Path

interface PunkService {
    @GET("{beers}")
    suspend fun listRepos(@Path("beers") user: String?): BeersResult
}