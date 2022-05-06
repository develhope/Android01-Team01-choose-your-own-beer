package co.develhope.chooseyourownbeer.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.chooseyourownbeer.*
import co.develhope.chooseyourownbeer.databinding.FragmentSearchBinding

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

        val beerList = Beers.getBeers()

        binding.beerListSearch.apply {
            adapter = BeerAdapter(beerList) { action -> OnAdapterClick(action) }
            layoutManager= LinearLayoutManager(context)
        }
    }
    private fun OnAdapterClick(action:BeerAction){
        when (action) {
            //        is BeerAction.OnStarClick
            is BeerAction.OnGoToDetailPageClick -> {
                val intent = Intent(context, BeerDetailActivity::class.java)
                intent.putExtra("BEER_ID", action.beer.id).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}