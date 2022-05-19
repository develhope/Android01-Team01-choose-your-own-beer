package co.develhope.chooseyourownbeer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.develhope.chooseyourownbeer.Beers.getBeerFromId
import co.develhope.chooseyourownbeer.databinding.BeerDetailBinding
import co.develhope.chooseyourownbeer.model.Beer

class BeerDetail : Fragment() {

    private var _binding: BeerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BeerDetailBinding.inflate(inflater, container, false)
        val beerId = arguments?.getLong("BEER_ID") ?: 0
        val beer = getBeerFromId(beerId)
        getBeerFromId(beerId)?.let { setupUI(it) }
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
}