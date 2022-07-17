package co.develhope.chooseyourownbeer.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.chooseyourownbeer.*
import co.develhope.chooseyourownbeer.databinding.FragmentSearchBinding
import co.develhope.chooseyourownbeer.ui.adapter.BeerAction
import co.develhope.chooseyourownbeer.ui.adapter.BeerAdapter
import co.develhope.chooseyourownbeer.ui.model.BeerUi
import co.develhope.chooseyourownbeer.ui.detail.BeerDetailActivity
import co.develhope.chooseyourownbeer.ui.home.HomeViewModel
import co.develhope.chooseyourownbeer.ui.home.HomeViewModelEvent
import com.google.android.material.snackbar.Snackbar

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SearchViewModel
    private lateinit var listFiltered: List<BeerUi>

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

        viewModel =
            (activity?.application as MyApplication).mainViewModelFactory.create(SearchViewModel::class.java)
        observeBeer()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null && query != "") {
                    listFiltered = Beers.getFilteredBeer(query)
                    val count = listFiltered.size
                    printCount(count)
                    showListFiltered(listFiltered)
                } else if (query == "") {
                    listFiltered = emptyList()
                    binding.textResult.text = ""
                    showListFiltered(listFiltered)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    listFiltered = Beers.getFilteredBeer(query)
                    val count = listFiltered.size
                    printCount(count)
                    showListFiltered(listFiltered)
                }
                return true
            }
        })
    }

    private fun printCount(count: Int) {
        if (count != 0) {
            binding.textResult.text =
                getString(R.string.search_result_text, count.toString())
        } else {
            binding.textResult.text = getString(R.string.search_result_nothing_text)
        }
    }

    private fun showListFiltered(listFiltered: List<BeerUi>) {
        binding.beerListSearch.apply {
            val sortedList =
                listFiltered.sortedWith(
                    compareBy<BeerUi> { it.favourite }.reversed().thenBy { it.id })
            adapter = BeerAdapter(sortedList) { action -> onAdapterClick(action) }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeBeer() {
        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is SearchViewModelEvent.SearchResult -> {
                    showListFiltered(it.beers)
                }
                is SearchViewModelEvent.SearchError -> Snackbar.make(
                    binding.fragmentSearch,
                    "Error:$it",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Retry") {
                    viewModel.retrieveBeers()
                }.show()
            }
        }
    }

    private fun onAdapterClick(action: BeerAction) {
        when (action) {
            is BeerAction.OnStarClick -> {
                viewModel.updateScreen(action.beerUi)
                showListFiltered(Beers.getFilteredBeer(binding.searchView.query.toString()))
            }
            is BeerAction.OnGoToDetailPageClick -> {
                val beer = action.beerUi
                val intent = Intent(context, BeerDetailActivity::class.java)
                intent.putExtra("BEER", beer)
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