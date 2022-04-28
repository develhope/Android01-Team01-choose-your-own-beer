package co.develhope.chooseyourownbeer

import co.develhope.chooseyourownbeer.model.Beer
import co.develhope.chooseyourownbeer.R
import java.util.*

object Beers {
    private val ichnusa = Beer(
        R.drawable.ichnusa,
        "Ichnusa non filtrata",
        33.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val messina = Beer(
        R.drawable.birra_messina,
        "Birra Messina Cristalli Di sale",
        66.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val tennents = Beer(
        R.drawable.tennets,
        "Tennent's Super",
        33.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        true
    )
    private val heineken = Beer(
        R.drawable.ichnusa,
        "Heineken",
        66.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val peroni = Beer(
        R.drawable.birra_messina,
        "Peroni",
        33.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        true
    )

    private val beerList: List<Beer> = listOf(ichnusa, messina, tennents, heineken, peroni)

    fun getBeers(): List<Beer> {
        return beerList
    }

    fun switchFavorite(beer: Beer) {
        val newFavourite = beer.copy(favourite = !beer.favourite)
        Collections.replaceAll(beerList, beer, newFavourite)
    }
}