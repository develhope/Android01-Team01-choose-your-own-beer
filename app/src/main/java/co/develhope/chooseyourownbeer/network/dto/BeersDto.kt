package co.develhope.chooseyourownbeer.network.dto

import co.develhope.chooseyourownbeer.ui.model.BeerUi

data class BeersDto(val abv: Double,
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
)
fun List<BeersDto>.toListOfBeerUi():List<BeerUi> =
    this.map { beer ->
        BeerUi(
    id = beer.id.toInt(),
    iconBeer = beer.image_url,
    title = beer.name,
    size = beer.volume.value.toDouble(),
    shortDescription = beer.description,
    fullDescription = beer.description,
    favourite = false
)
    }