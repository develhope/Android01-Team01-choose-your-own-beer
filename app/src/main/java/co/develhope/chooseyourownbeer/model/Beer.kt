package co.develhope.chooseyourownbeer.model

data class Beer(val id: Long,
                var imagePath: Int,
                var title: String,
                var size: Double,
                val shortDescription: String,
                var fullDescription: String,
                val favourite: Boolean)
