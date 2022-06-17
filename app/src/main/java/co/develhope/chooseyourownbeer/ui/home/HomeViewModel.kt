package co.develhope.chooseyourownbeer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.chooseyourownbeer.ui.model.BeerUi
import co.develhope.chooseyourownbeer.network.BeersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (private val beerProvider: BeersProvider): ViewModel() {

    private var _beers = MutableLiveData<List<BeerUi>>()
    val beers: LiveData<List<BeerUi>>
       get() = _beers

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun retrieveRepos(){
        CoroutineScope(Dispatchers.Main).launch {
            //@TODO Add here loading progress
            try {
                _beers.value = beerProvider.getFullListOfBeerRepos()
            } catch (e : Exception){
                _error.value = e.localizedMessage
            }
        }
    }
}
