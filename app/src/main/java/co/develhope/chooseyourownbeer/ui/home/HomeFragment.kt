package co.develhope.chooseyourownbeer.ui.home

import android.content.Intent
import co.develhope.chooseyourownbeer.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.chooseyourownbeer.*
import co.develhope.chooseyourownbeer.databinding.FragmentHomeBinding


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
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val beerList = Beers.getBeers()

        binding.beerList.apply {
            adapter = BeerAdapter(beerList) { action -> onAdapterClick(action) }
            layoutManager= LinearLayoutManager(context)
        }
    }
        private fun onAdapterClick(action:BeerAction){
            when (action) {
    //        is BeerAction.OnStarClick
              is BeerAction.OnGoToDetailPageClick -> {
                  val idBeer= action.beer.id
                  (activity as MainActivity).goTo(
                      fragment = BeerDetail.newInstance(idBeer),
                      addToBackStack = false,
                      tag = BeerDetail.TAG
                  )
                }
            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}