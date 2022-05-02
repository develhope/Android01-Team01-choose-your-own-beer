package co.develhope.chooseyourownbeer.model

data class Beer(val id: Long,
                val imagePath: Int,
                val title: String,
                val size: Double,
                val shortDescription: String,
                val fullDescription: String,
                val favourite: Boolean)
