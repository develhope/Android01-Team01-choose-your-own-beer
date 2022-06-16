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
import co.develhope.chooseyourownbeer.databinding.FragmentHomeBinding
import co.develhope.chooseyourownbeer.ui.model.BeerUi
import co.develhope.chooseyourownbeer.ui.detail.BeerDetailActivity
import com.android.example.cleanarchietetture_viemodellivedata.MyApplication
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel


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
        viewModel = (activity?.application as MyApplication).mainViewModelFactory.create(HomeViewModel::class.java)
        viewModel.retrieveRepos()
        observerRepos()
    }

    private fun onAdapterClick(action: BeerAction) {
        when (action) {
            is BeerAction.OnStarClick -> {
                Beers.switchFavorite(action.beerUi)
                refreshList()
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

    private fun showBeers(beersList: List<BeerUi>){
        binding.beerList.apply {
            adapter = BeerAdapter(beersList){ action ->
                onAdapterClick(action)
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observerRepos(){
        viewModel.beers.observe(viewLifecycleOwner) {
            showBeers(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.fragmentHome,
                "Error:$it",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Retry") {
                viewModel.retrieveRepos()
            }.show()
        }
    }

    private fun refreshList() {
        val beerList = Beers.getBeers()
        val sortedList = beerList.sortedWith(compareBy<BeerUi> { it.favourite }.reversed().thenBy { it.id })
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