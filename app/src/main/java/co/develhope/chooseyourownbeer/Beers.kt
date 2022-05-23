package co.develhope.chooseyourownbeer

import co.develhope.chooseyourownbeer.model.Beer
import co.develhope.chooseyourownbeer.R
import java.util.*

object Beers {
    private val ichnusa = Beer(1,
        R.drawable.ichnusa,
        "Ichnusa non filtrata",
        33.0,
        "Colore giallo dorato luminoso con schiuma fine e persistente.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val messina = Beer(2,
        R.drawable.birra_messina,
        "Birra Messina Cristalli Di sale",
        66.0,
        "Colore giallo dorato luminoso con una naturale opalescenza ha schiuma compatta e persistente.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val tennents = Beer(3,
        R.drawable.tennets,
        "Tennent's Super",
        33.0,
        "Bel colore giallo dorato luminoso con schiuma bianca di poca persistenza.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        true
    )
    private val heineken = Beer(4,
        R.drawable.heineken,
        "Heineken",
        66.0,
        "È una birra lager al malto d'orzo e luppolo, facile da bere e di gusto pulito.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        false
    )
    private val peroni = Beer(5,
        R.drawable.peroni,
        "Peroni",
        33.0,
        "È la lager italiana che unisce tutti da Nord a Sud, per offrire a tutti noi una bira",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        true
    )

    private val beerList: List<Beer> = listOf(ichnusa, heineken, peroni, messina, tennents)

    fun getBeers(): List<Beer> {
        return beerList
    }

    fun getBeerFromId(idBeer: Long): Beer? {
        val beer = beerList.find { it.id == idBeer }
        return beer
    }

    fun getFilteredBeer(text: String): List<Beer> {
        return beerList.filter { it.title.contains(text) }
    }

    fun switchFavorite(beer: Beer) {
        val newFavourite = beer.copy(favourite = !beer.favourite)
        Collections.replaceAll(beerList, beer, newFavourite)
    }
}