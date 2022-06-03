package co.develhope.chooseyourownbeer.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.chooseyourownbeer.BeerAction
import co.develhope.chooseyourownbeer.BeerAdapter
import co.develhope.chooseyourownbeer.Beers
import co.develhope.chooseyourownbeer.databinding.FragmentHomeBinding
import co.develhope.chooseyourownbeer.ui.detail.BeerDetailActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val beerList = Beers.getBeers()

        binding.beerList.apply {
            adapter = BeerAdapter(beerList) { action -> onAdapterClick(action) }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onAdapterClick(action: BeerAction) {
        when (action) {
            is BeerAction.OnStarClick -> {
                // TODO
            }
            is BeerAction.OnGoToDetailPageClick -> {
                val idBeer = action.beer.id
                val intent = Intent(context, BeerDetailActivity::class.java)
                intent.putExtra("BEER_ID", idBeer)
                startActivity(intent)
            }
            else -> {
                return
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}