package co.develhope.chooseyourownbeer.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.chooseyourownbeer.Beers.switchFavorite
import co.develhope.chooseyourownbeer.databinding.FragmentHomeBinding
import co.develhope.chooseyourownbeer.ui.adapter.BeerAction
import co.develhope.chooseyourownbeer.ui.adapter.BeerAdapter
import co.develhope.chooseyourownbeer.ui.detail.BeerDetailActivity
import co.develhope.chooseyourownbeer.ui.model.BeerUi
import co.develhope.chooseyourownbeer.MyApplication
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
        viewModel =
            (activity?.application as MyApplication).mainViewModelFactory.create(HomeViewModel::class.java)
        observerRepos()
    }

    private fun onAdapterClick(action: BeerAction) {
        when (action) {
            is BeerAction.OnStarClick -> {
                switchFavorite(action.beerUi)
                viewModel.getSortedBeers()
                observerRepos()
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

    private fun showBeers(beersList: List<BeerUi>) {
        binding.beerList.apply {
            adapter = BeerAdapter(
                beersList
            ) { action ->
                onAdapterClick(action)
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observerRepos() {
        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is HomeViewModelEvent.HomeResult -> {
                    showBeers(it.beers)
                }
                is HomeViewModelEvent.HomeError -> Snackbar.make(
                    binding.fragmentHome,
                    "Error:$it",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Retry") {
                    viewModel.retrieveRepos()
                }.show()
                is HomeViewModelEvent.HomeLoading -> binding.loadingProgressBar.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}