package co.develhope.chooseyourownbeer.model

import android.util.Log
import androidx.lifecycle.ViewModel
import co.develhope.chooseyourownbeer.network.PunkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    //Build retrofit
    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Create class
    var punkService = retrofit.create(PunkService::class.java)

    fun retriveBeerRepo(){
        Log.d("test","test")
        CoroutineScope(Dispatchers.IO).launch {
        val repos = punkService.listRepos("beers")
        }
    }
}