package co.develhope.chooseyourownbeer

import co.develhope.chooseyourownbeer.ui.model.BeerUi
import java.util.*

object Beers {
    private var beerUiList : MutableList<BeerUi> = mutableListOf<BeerUi>()

    fun getBeers(): List<BeerUi> {
        return beerUiList
    }

    fun getBeerFromId(idBeer: Int): BeerUi? {
        return beerUiList.find { it.id == idBeer }
    }

    fun getFilteredBeer(text: String): List<BeerUi> {
        return beerUiList.filter { it.title.contains(text) }
    }

    fun switchFavorite(beerUi: BeerUi) {
        val newFavourite = beerUi.copy(favourite = !beerUi.favourite)
        Collections.replaceAll(beerUiList, beerUi, newFavourite)
    }
}