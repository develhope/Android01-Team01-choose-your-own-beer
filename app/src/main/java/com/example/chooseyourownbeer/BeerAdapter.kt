package com.example.chooseyourownbeer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chooseyourownbeer.model.Beer

class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val beerImage: ImageView
    val beerTitle: TextView
    val beerSize: TextView
    val beerDesc: TextView

    init {
        beerImage = view.findViewById(R.id.imagePath)
        beerTitle = view.findViewById(R.id.title)
        beerSize = view.findViewById(R.id.size)
        beerDesc = view.findViewById(R.id.shortDescription)
    }
}

class BeerAdapter(val beerList: List<Beer>) : RecyclerView.Adapter<BeerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val beerView =
            LayoutInflater.from(parent.context).inflate(R.layout.beer_layout, parent, false)
        return BeerViewHolder(beerView)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.beerImage.setImageResource(beerList[position].imagePath)
        holder.beerTitle.text = beerList[position].title
        holder.beerSize.text = beerList[position].size.toString()
        holder.beerDesc.text = beerList[position].shortDescription
    }

    override fun getItemCount(): Int {
        return beerList.size
    }
}