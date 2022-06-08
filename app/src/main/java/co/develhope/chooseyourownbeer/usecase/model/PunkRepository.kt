package co.develhope.chooseyourownbeer.usecase.model

import co.develhope.chooseyourownbeer.network.jsonSource.Volume

data class PunkRepository (val id: Long,
                           var imagePath: String,
                           var title: String,
                           var size: Volume,
                           val shortDescription: String,
                           var fullDescription: String,
                           val favourite: Boolean)