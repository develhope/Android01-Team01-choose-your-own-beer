package co.develhope.chooseyourownbeer.ui.search

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.chooseyourownbeer.Beers
import co.develhope.chooseyourownbeer.ui.model.BeerUi
import co.develhope.chooseyourownbeer.network.BeersProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

sealed class SearchViewModelEvent {
    data class SearchResult(val beers: List<BeerUi>) : SearchViewModelEvent()
    data class SearchError(val message: String) : SearchViewModelEvent()
}

const val KEY_BEER_LIST = "key_beer_list"
const val KEY_SAVE_DATE = "key_save_date"
const val KEY_EXPIRE_DATE = "key_expire_date"

class SearchViewModel(
    private val beerProvider: BeersProvider,
    private val preferences: SharedPreferences
) : ViewModel() {

    private var _result = MutableLiveData<SearchViewModelEvent>()
    private lateinit var expireDate: LocalDate
    val result: LiveData<SearchViewModelEvent>
        get() = _result

    init {
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        CoroutineScope(Dispatchers.Main).launch {
            loadList()
            val expireString = preferences.getString(KEY_EXPIRE_DATE, null)
            if (expireString != null) {
                expireDate = LocalDate.parse(expireString)
            }
            if (Beers.getBeers().isEmpty() || LocalDate.now().isAfter(expireDate)) {
                retrieveBeers()
            } else {
                val beers = Beers.getBeers()
                _result.value = SearchViewModelEvent.SearchResult(
                    beers.sort()
                )
            }
        }
    }

    private fun saveList() {
        val json = Gson().toJson(Beers.getBeers().sort())
        preferences.edit().putString(KEY_BEER_LIST, json).commit()
        saveDate()
    }

    private fun saveDate() {
        val saveDate = LocalDate.now()
        val expireDate = saveDate.plusDays(7)
        preferences.edit().putString(KEY_SAVE_DATE, saveDate.toString()).commit()
        preferences.edit().putString(KEY_EXPIRE_DATE, expireDate.toString()).commit()
    }

    private fun loadList() {
        val json = preferences.getString(KEY_BEER_LIST, null)
        val type = object : TypeToken<MutableList<BeerUi>>() {}.type
        val beers = Gson().fromJson<MutableList<BeerUi>>(json, type)
        if (beers != null) {
            Beers.refreshBeers(beers)
        }
    }

    private fun List<BeerUi>.sort(): List<BeerUi> {
        val sortedBeers =
            this.sortedWith(compareBy<BeerUi> { it.favourite }.reversed().thenBy { it.id })
        Beers.refreshBeers(sortedBeers)
        return sortedBeers
    }

    private fun getSortedBeers() {
        CoroutineScope(Dispatchers.Main).launch {
            val beers = Beers.getBeers().sort()
            _result.value = SearchViewModelEvent.SearchResult(beers)
        }
        saveList()
    }

    fun updateScreen(beer: BeerUi) {
        Beers.switchFavorite(beer)
        getSortedBeers()
    }

    fun retrieveBeers() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val beers = beerProvider.getFullListOfBeerRepos()
                _result.value = SearchViewModelEvent.SearchResult(beers.sort())
                saveList()
            } catch (e: Exception) {
                _result.value = SearchViewModelEvent.SearchError("Error: ${e.localizedMessage}")
            }
        }
    }
}
