package co.develhope.chooseyourownbeer.network.dto

import co.develhope.chooseyourownbeer.ui.model.BeerUi

data class BeerDTO(
    val abv: Double,
    val attenuation_level: Double,
    val boil_volume: BoilVolume,
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val ebc: Int,
    val first_brewed: String,
    val food_pairing: List<String>,
    val ibu: Double,
    val id: Long,
    val image_url: String,
    val ingredients: Ingredients,
    val method: Method,
    val name: String,
    val ph: Double,
    val srm: Double,
    val tagline: String,
    val target_fg: Int,
    val target_og: Double,
    val volume: Volume,
) {
    data class Amount(
        val unit: String,
        val value: Double
    )

    data class AmountX(
        val unit: String,
        val value: Double
    )

    data class BoilVolume(
        val unit: String,
        val value: Int
    )

    data class Fermentation(
        val temp: Temp
    )

    data class Hop(
        val add: String,
        val amount: Amount,
        val attribute: String,
        val name: String
    )

    data class Ingredients(
        val hops: List<Hop>,
        val malt: List<Malt>,
        val yeast: String
    )

    data class Malt(
        val amount: AmountX,
        val name: String
    )

    data class MashTemp(
        val duration: Int,
        val temp: TempX
    )

    data class Method(
        val fermentation: Fermentation,
        val mash_temp: List<MashTemp>,
        val twist: String
    )

    data class Temp(
        val unit: String,
        val value: Int
    )

    data class TempX(
        val unit: String,
        val value: Int
    )

    data class Volume(
        val unit: String,
        val value: Int
    )
}

fun List<BeerDTO>.toListOfBeerUi(): List<BeerUi> =
    this.map { beer ->
        BeerUi(
            id = beer.id.toInt(),
            iconBeer = beer.image_url,
            title = beer.name,
            size = beer.volume.value,
            shortDescription = beer.description,
            fullDescription = beer.description,
            favourite = false
        )
    }