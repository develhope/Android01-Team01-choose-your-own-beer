package co.develhope.chooseyourownbeer.ui.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.develhope.chooseyourownbeer.network.BeersProvider
import co.develhope.chooseyourownbeer.ui.home.HomeViewModel
import co.develhope.chooseyourownbeer.ui.search.SearchViewModel
import java.lang.IllegalArgumentException

//Add extend view model

class MyViewModelFactory(private val beersProvider: BeersProvider, private val preferences: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(beersProvider, preferences) as T
        }
        else if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(beersProvider, preferences) as T
        }
        throw IllegalArgumentException("ViewModel unKnow")
    }
}