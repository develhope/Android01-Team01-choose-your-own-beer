package co.develhope.chooseyourownbeer.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.chooseyourownbeer.network.PunkProvider
import co.develhope.chooseyourownbeer.usecase.model.PunkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PunkSearchViewModel (private val punkProvider: PunkProvider): ViewModel() {

    private var _repos = MutableLiveData<List<PunkRepository>>()
    val repos: LiveData<List<PunkRepository>>
       get() = _repos

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun retrieveRepos(beername : String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _repos.value = punkProvider.getBeerRepos(beername)
            } catch (e : Exception){
                _error.value = e.localizedMessage
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
