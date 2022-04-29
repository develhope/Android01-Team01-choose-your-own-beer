package co.develhope.chooseyourownbeer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.develhope.chooseyourownbeer.model.Beer

sealed class BeerAction(){
    object OnStarClick : BeerAction()
    object OnGoToDetailPageClick : BeerAction()
}

 class BeerAdapter(val beerList: List<Beer>, val onBeerClick: (BeerAction) -> Unit) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {
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
         holder.icon.setOnClickListener{
             onBeerClick(BeerAction.OnStarClick)
         }
         holder.button.setOnClickListener {
             onBeerClick(BeerAction.OnGoToDetailPageClick)
         }
     }

     override fun getItemCount(): Int {
         return beerList.size
     }

     inner class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val beerImage: ImageView
         val beerTitle: TextView
         val beerSize: TextView
         val beerDesc: TextView
         val icon: ImageView
         val button: Button

         init {
             beerImage = view.findViewById(R.id.imagePath)
             beerTitle = view.findViewById(R.id.title)
             beerSize = view.findViewById(R.id.size)
             beerDesc = view.findViewById(R.id.shortDescription)
             icon = view.findViewById(R.id.icon)
             button = view.findViewById(R.id.button)
         }
     }
 }