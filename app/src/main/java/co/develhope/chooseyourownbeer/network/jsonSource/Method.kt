package co.develhope.chooseyourownbeer.network.jsonSource

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: String
)