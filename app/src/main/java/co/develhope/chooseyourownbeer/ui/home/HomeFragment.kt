package co.develhope.chooseyourownbeer.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.chooseyourownbeer.ui.BeerAction
import co.develhope.chooseyourownbeer.ui.BeerAdapter
import co.develhope.chooseyourownbeer.Beers
import co.develhope.chooseyourownbeer.Beers.getBeers
import co.develhope.chooseyourownbeer.databinding.FragmentHomeBinding
import co.develhope.chooseyourownbeer.model.Beer
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

        refreshList()
    }

    private fun onAdapterClick(action: BeerAction) {
        when (action) {
            is BeerAction.OnStarClick -> {
                Beers.switchFavorite(action.beer)
                refreshList()
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

    private fun refreshList() {
        val beerList = Beers.getBeers()
        val sortedList = beerList.sortedWith(compareBy<Beer> { it.favourite }.reversed().thenBy { it.id })
        binding.beerList.apply {
            adapter = BeerAdapter(sortedList) { action -> onAdapterClick(action) }
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}