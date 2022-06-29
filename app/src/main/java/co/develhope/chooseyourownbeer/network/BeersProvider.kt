package co.develhope.chooseyourownbeer.network

import co.develhope.chooseyourownbeer.ui.model.BeerUi
import co.develhope.chooseyourownbeer.network.dto.toListOfBeerUi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BeersProvider {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var beersService = retrofit.create(BeerService::class.java)


    suspend fun getFullListOfBeerRepos(): List<BeerUi> {
        return beersService.getBeersList().toListOfBeerUi()
        }
    }
