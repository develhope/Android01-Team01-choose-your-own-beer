package co.develhope.chooseyourownbeer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.chooseyourownbeer.databinding.BeerLayoutBinding
import co.develhope.chooseyourownbeer.model.Beer

sealed class BeerAction {
    object OnStarClick : BeerAction()
    data class OnGoToDetailPageClick(val beer: Beer) : BeerAction()
}

class BeerAdapter(private val beerList: List<Beer>, private val onBeerClick: (BeerAction) -> Unit) :
    RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    private lateinit var binding: BeerLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        binding = BeerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        with(beerList[position]) {
            binding.imagePath.setImageResource(imagePath)
            binding.title.text = title
            binding.size.text = size.toString()
            binding.shortDescription.text = shortDescription


            binding.icon.setOnClickListener {
                onBeerClick(BeerAction.OnStarClick)
            }
            binding.button.setOnClickListener {
                onBeerClick(BeerAction.OnGoToDetailPageClick(beerList[position]))
            }
        }
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    inner class BeerViewHolder(binding: BeerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
/*
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
          binding.imagePath.setImageResource(beerList[position].imagePath)
            binding.title.text = beerList[position].title
            binding.size.text = beerList[position].size.toString()
            binding.shortDescription.text = beerList[position].shortDescription

       }
 */
    }
}