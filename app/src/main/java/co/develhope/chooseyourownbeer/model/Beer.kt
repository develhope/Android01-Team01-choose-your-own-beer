package co.develhope.chooseyourownbeer.model

data class Beer(val id: Int,
                var imagePath: String?,
                var title: String,
                var size: Double,
                val shortDescription: String,
                var fullDescription: String,
                val favourite: Boolean)
