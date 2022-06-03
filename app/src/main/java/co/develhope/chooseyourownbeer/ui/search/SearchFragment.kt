package co.develhope.chooseyourownbeer.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.chooseyourownbeer.*
import co.develhope.chooseyourownbeer.databinding.FragmentSearchBinding
import co.develhope.chooseyourownbeer.model.Beer

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var listFiltered: List<Beer>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    listFiltered = Beers.getFilteredBeer(query)
                    val count = listFiltered.size
                    printCount(count)
                    showListFiltered()
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    listFiltered = Beers.getFilteredBeer(query)
                    val count = listFiltered.size
                    printCount(count)
                    showListFiltered()
                }
                return true
            }
        })
    }

    private fun printCount(count: Int) {
        if (count != 0) {
            binding.textResult.text = "$count Risultati"
        } else {
            binding.textResult.text = "Nessun elemento trovato"
        }
    }

    private fun showListFiltered() {
        binding.beerListSearch.apply {
            adapter = BeerAdapter(listFiltered) { action -> onAdapterClick(action) }
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
                val bundle = bundleOf("BEER_ID" to idBeer)
                findNavController().navigate(R.id.action_navigation_search_to_beerDetail, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}