package co.develhope.chooseyourownbeer.network

import co.develhope.chooseyourownbeer.network.dto.toPunkRepository
import co.develhope.chooseyourownbeer.usecase.model.PunkRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val PAGE_SIZE = 20

class PunkProvider {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var punkService = retrofit.create(PunkService::class.java)


    suspend fun getBeerRepos(beers: String): List<PunkRepository> = punkService.listRepos(beers,PAGE_SIZE).map {
        it -> it.toPunkRepository()
    }
}