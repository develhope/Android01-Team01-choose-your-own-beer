package co.develhope.chooseyourownbeer

import co.develhope.chooseyourownbeer.ui.model.BeerUi
import java.util.*

object Beers {
    private var beerUiList : MutableList<BeerUi> = mutableListOf<BeerUi>()

    fun getBeers(): MutableList<BeerUi> {
        return beerUiList
    }

    fun refreshBeers(updatedBeers: List<BeerUi>): List<BeerUi> {
        beerUiList = updatedBeers as MutableList<BeerUi>
        return beerUiList
    }

    fun getFilteredBeer(text: String): List<BeerUi> {
        return beerUiList.filter { it.title.contains(text, ignoreCase = true) }
    }

    fun switchFavorite(beerUi: BeerUi) {
        val newFavourite = beerUi.copy(favourite = !beerUi.favourite)
        Collections.replaceAll(beerUiList, beerUi, newFavourite)
    }
}