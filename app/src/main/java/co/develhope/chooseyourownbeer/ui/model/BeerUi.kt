package co.develhope.chooseyourownbeer.ui.model

import android.os.Parcel
import android.os.Parcelable

data class BeerUi(
    val id: Int,
    val iconBeer: Int,
    val title: String,
    val size: Double,
    val shortDescription: String,
    val fullDescription: String,
    val favourite: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(iconBeer)
        parcel.writeString(title)
        parcel.writeDouble(size)
        parcel.writeString(shortDescription)
        parcel.writeString(fullDescription)
        parcel.writeByte(if (favourite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BeerUi> {
        override fun createFromParcel(parcel: Parcel): BeerUi {
            return BeerUi(parcel)
        }

        override fun newArray(size: Int): Array<BeerUi?> {
            return arrayOfNulls(size)
        }
    }
}
