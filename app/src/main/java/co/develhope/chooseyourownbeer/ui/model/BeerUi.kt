package co.develhope.chooseyourownbeer.ui.model

data class BeerUi(
    val id: Int,
    var imagePath: Int,
    var title: String,
    var size: Double,
    val shortDescription: String,
    var fullDescription: String,
    val favourite: Boolean)
