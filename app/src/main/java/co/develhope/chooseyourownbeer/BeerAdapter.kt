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
        holder.bind(beerList[position])
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    inner class BeerViewHolder(binding: BeerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer) {
            with(beer) {
                binding.imagePath.setImageResource(imagePath)
                binding.title.text = title
                binding.size.text = size.toString()
                binding.shortDescription.text = shortDescription
                binding.icon.setOnClickListener {
                    onBeerClick(BeerAction.OnStarClick)
                }
                binding.button.setOnClickListener {
                    onBeerClick(BeerAction.OnGoToDetailPageClick(beer))
                }
            }
        }
    }
}