package com.example.chooseyourownbeer

object Beers {
    data class Beer(
        val imagePath: String,
        val title: String,
        val size: Double,
        val shortDescription: String,
        val fullDescription: String,
        var favorite: Boolean
    )

    private val ichnusa = Beer(
        "./res/drawable/ichnusa.png",
        "Ichnusa non filtrata",
        33.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val messina = Beer(
        "./res/drawable/birra_messina.png",
        "Birra Messina Cristalli Di sale",
        66.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val tennents = Beer(
        "./res/drawable/tennets.png",
        "Tennent's Super",
        33.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        true
    )
    private val heineken = Beer(
        "./res/drawable/ichnusa.png",
        "Heineken",
        66.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val peroni = Beer(
        "./res/drawable/birra_messina.png",
        "Peroni",
        33.0,
        "Lorem Ipsum",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        true
    )

    private val beerList: MutableList<Beer> = mutableListOf(ichnusa, messina, tennents, heineken, peroni)

    fun getBeers(): MutableList<Beer> {
        return beerList
    }

    fun switchFavorite(beer: Beer) {
        beer.favorite = !beer.favorite
    }
}