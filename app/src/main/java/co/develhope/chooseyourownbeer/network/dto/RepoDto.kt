package co.develhope.chooseyourownbeer.network.dto

import co.develhope.chooseyourownbeer.network.jsonSource.BoilVolume
import co.develhope.chooseyourownbeer.network.jsonSource.Ingredients
import co.develhope.chooseyourownbeer.network.jsonSource.Method
import co.develhope.chooseyourownbeer.network.jsonSource.Volume
import co.develhope.chooseyourownbeer.usecase.model.PunkRepository


data class RepoDto(val abv: Double,
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
                   val volume: Volume
                   var favorite : Boolean
)
fun RepoDto.toPunkRepository() = PunkRepository(this.id,this.image_url,this.name, this.volume, this.description, this.description, this.favorite)