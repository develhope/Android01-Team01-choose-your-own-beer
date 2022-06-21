package co.develhope.chooseyourownbeer.network

import android.widget.ImageView
import co.develhope.chooseyourownbeer.R
import com.squareup.picasso.Picasso


fun ImageView.setImageByUrl(url: String?, width: Int, height: Int) {
    Picasso.get()
        .load(url)
        .resize(width,height)
        .placeholder(R.drawable.shapeview_background)
        .error(R.drawable.shapeview_background)
        .into(this)
}