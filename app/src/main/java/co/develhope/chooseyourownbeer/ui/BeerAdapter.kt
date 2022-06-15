package co.develhope.chooseyourownbeer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.chooseyourownbeer.R
import co.develhope.chooseyourownbeer.databinding.BeerLayoutBinding
import co.develhope.chooseyourownbeer.ui.model.BeerUi

sealed class BeerAction {
    data class OnStarClick(val beerUi: BeerUi) : BeerAction()
    data class OnGoToDetailPageClick(val beerUi: BeerUi) : BeerAction()
}

class BeerAdapter(private val beerUiList: List<BeerUi>, private val onBeerClick: (BeerAction) -> Unit) :
    RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    private lateinit var binding: BeerLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        binding = BeerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(beerUiList[position])
    }

    override fun getItemCount(): Int {
        return beerUiList.size
    }

    inner class BeerViewHolder(binding: BeerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beerUi: BeerUi) {
            with(beerUi) {
                binding.imagePath.setImageResource(imagePath)
                binding.title.text = title
                binding.size.text = size.toString()
                binding.shortDescription.text = shortDescription
                if (beerUi.favourite) {
                    binding.icon.setImageResource(R.drawable.fullstar)
                } else {
                    binding.icon.setImageResource(R.drawable.emptystar)
                }
                binding.icon.setOnClickListener {
                    onBeerClick(BeerAction.OnStarClick(beerUi))
                }
                binding.button.setOnClickListener {
                    onBeerClick(BeerAction.OnGoToDetailPageClick(beerUi))
                }
            }
        }
    }
}