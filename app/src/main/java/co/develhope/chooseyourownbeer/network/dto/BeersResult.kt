package co.develhope.chooseyourownbeer.network.dto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private lateinit var beers : BeersResult

class BeersResult

private var _beersResult = MutableLiveData<BeerDTO>()
private val beersResult : LiveData<BeerDTO>
    get() = _beersResult
private var _error = MutableLiveData<BeerDTO>()
private val error : LiveData<BeerDTO>
    get() = _error

fun retrieveRepos() {
    CoroutineScope(Dispatchers.Main).launch {
        try {
            _beersResult.value = null
            val beers = beersResult
            val error = error
            _error.value = (_error.value)
        } catch (e : Exception) {

        }
    }
}
