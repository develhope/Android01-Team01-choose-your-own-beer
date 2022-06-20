package co.develhope.chooseyourownbeer.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.develhope.chooseyourownbeer.network.BeersProvider
import co.develhope.chooseyourownbeer.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

//Add extend view model

class MyViewModelFactory(private val beersProvider: BeersProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(beersProvider) as T
        }
        throw IllegalArgumentException("ViewModel unKnow")
    }
}