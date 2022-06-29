package co.develhope.chooseyourownbeer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.chooseyourownbeer.Beers
import co.develhope.chooseyourownbeer.databinding.FragmentHomeBinding
import co.develhope.chooseyourownbeer.ui.model.BeerUi
import co.develhope.chooseyourownbeer.network.BeersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class HomeViewModelEvent {
    data class HomeResult(val beers: List<BeerUi>) : HomeViewModelEvent()
    data class HomeError(val message: String) : HomeViewModelEvent()
}

class HomeViewModel(private val beerProvider: BeersProvider) : ViewModel() {

    private var _result = MutableLiveData<HomeViewModelEvent>()
    val result: LiveData<HomeViewModelEvent>
        get() = _result

    init {
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        CoroutineScope(Dispatchers.Main).launch {
            if (Beers.getBeers().isEmpty()) {
                retrieveRepos()
            } else {
                _result.value = HomeViewModelEvent.HomeResult(
                    Beers.getBeers()
                        .sortedWith(compareBy<BeerUi> { it.favourite }.reversed().thenBy { it.id })
                )
            }
        }
    }

    fun checkBeers(beers: List<BeerUi>) {
        Beers.refreshBeers(beers.sortedWith(
            compareBy<BeerUi> { it.favourite }.reversed().thenBy { it.id }).toMutableList()
        )
    }


    fun retrieveRepos() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _result.value = HomeViewModelEvent.HomeResult(beerProvider.getFullListOfBeerRepos())
            } catch (e: Exception) {
                _result.value = HomeViewModelEvent.HomeError("Error: ${e.localizedMessage}")
            }
        }
    }
}
