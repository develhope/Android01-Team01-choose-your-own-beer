package co.develhope.chooseyourownbeer.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.chooseyourownbeer.BeerAction
import co.develhope.chooseyourownbeer.BeerAdapter
import co.develhope.chooseyourownbeer.Beers
import co.develhope.chooseyourownbeer.R
import co.develhope.chooseyourownbeer.databinding.FragmentSearchBinding
import co.develhope.chooseyourownbeer.model.Beer

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    var listFiltered = Beers.getFilteredBeer(query)
                    var count = listFiltered.size
                    binding.textResult.text ="$count Risultati"
                    binding.beerListSearch.apply {
                        adapter = BeerAdapter(listFiltered) { action -> onAdapterClick(action) }
                        layoutManager= LinearLayoutManager(context)
                    }
                } else {
                    Toast.makeText(context, "Nessun elemento trovato", Toast.LENGTH_LONG).show()
                }
                return false
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                /*               if (beerList.contains(query)) {
                                   adapterQuery?.filter?.filter(query)
                               } else {
                                   Toast.makeText(context, "Nessun elemento trovato", Toast.LENGTH_LONG).show()
                               }
               */                return false
            }
        })
    }
    private fun onAdapterClick(action:BeerAction){
        when (action) {
            //        is BeerAction.OnStarClick
            is BeerAction.OnGoToDetailPageClick -> {
                val idBeer= action.beer.id
                val bundle= bundleOf("BEER_ID" to idBeer)
                findNavController().navigate(R.id.action_navigation_search_to_beerDetail, bundle)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}