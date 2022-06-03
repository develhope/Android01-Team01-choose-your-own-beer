package co.develhope.chooseyourownbeer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.develhope.chooseyourownbeer.Beers.getBeerFromId
import co.develhope.chooseyourownbeer.databinding.BeerDetailBinding
import co.develhope.chooseyourownbeer.model.Beer

class BeerDetailFragment : Fragment() {

    private var _binding: BeerDetailBinding? = null
    private val binding get() = _binding!!
    private var beerId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BeerDetailBinding.inflate(inflater, container, false)
        val beer = beerId?.let { getBeerFromId(it) }
        setupUI(beer as Beer)
        return binding.root

    }

    private fun setupUI(beer: Beer) {
        binding.imageBeer.setImageResource(beer.imagePath)
        binding.titleBeer.text = beer.title
        binding.size.text = beer.size.toString()
        binding.longDescription.text = beer.fullDescription
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG = BeerDetailFragment::class.java.canonicalName
            ?: "BeerDetail"

        fun newInstance(beerIdToShow: Int): BeerDetailFragment {
            return BeerDetailFragment().apply {
                beerId = beerIdToShow
            }
        }
    }
}