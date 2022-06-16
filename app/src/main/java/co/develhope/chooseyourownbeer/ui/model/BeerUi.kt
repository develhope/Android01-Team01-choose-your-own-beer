package co.develhope.chooseyourownbeer.ui.model

data class BeerUi(
    val id: Int,
    val iconBeer: Int,
    val title: String,
    val size: Double,
    val shortDescription: String,
    val fullDescription: String,
    val favourite: Boolean)
