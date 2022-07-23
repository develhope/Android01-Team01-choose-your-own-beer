package co.develhope.chooseyourownbeer.network.dto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class BeersResult(private val beersDto : BeersDto) : ViewModel() {

    private var _beers = MutableLiveData<List<BeersDto>>()
    val beers : LiveData<List<BeersDto>>
        get() = _beers

    private var _error = MutableLiveData<String>()
    val error : LiveData<String>
        get() = _error


    fun get() {
        CoroutineScope(Dispatchers.Main).launch {
            //@TODO Add here loading progress
            try {
                _beers.value = null
            } catch (e : Exception) {
                _error.value = e.localizedMessage
            }
        }
    }
}


//Guarda dove viene usato BeerResult. per esempio BeersProvider.
// cattura lì gli errori di rete e restituisci un oggetto di una sealed class (es. Result, Error)
// Modifica i viewmodel che usano la funzione per la chiamata di rete di conseguenza