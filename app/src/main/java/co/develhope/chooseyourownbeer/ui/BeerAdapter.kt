package co.develhope.chooseyourownbeer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.chooseyourownbeer.R
import co.develhope.chooseyourownbeer.databinding.BeerLayoutBinding
import co.develhope.chooseyourownbeer.model.Beer
import co.develhope.chooseyourownbeer.usecase.model.PunkRepository

sealed class BeerAction {
    data class OnStarClick(val beer: Beer) : BeerAction()
    data class OnGoToDetailPageClick(val beer: Beer) : BeerAction()
}

class BeerAdapter(private val beerList: List<PunkRepository>, private val onBeerClick: (BeerAction) -> Unit) :
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
                if (beer.favourite) {
                    binding.icon.setImageResource(R.drawable.fullstar)
                } else {
                    binding.icon.setImageResource(R.drawable.emptystar)
                }
                binding.icon.setOnClickListener {
                    onBeerClick(BeerAction.OnStarClick(beer))
                }
                binding.button.setOnClickListener {
                    onBeerClick(BeerAction.OnGoToDetailPageClick(beer))
                }
            }
        }
    }
}