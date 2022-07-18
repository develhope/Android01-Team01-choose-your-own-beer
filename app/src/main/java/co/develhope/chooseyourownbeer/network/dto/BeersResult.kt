package co.develhope.chooseyourownbeer.network.dto


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.chooseyourownbeer.network.BeerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private lateinit var MainViewModel : ViewModel

class BeersResult : ArrayList<BeersResult>() {

    private val retrofit =
        Retrofit.Builder().baseUrl("https://api.punkapi.com/v2/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    private val beerService = retrofit.create(BeerService::class.java)

    val beerUi = MutableLiveData<BeersResult>()


    fun beerUi() {
        Log.d("MainViewModel", "list of beers")
        CoroutineScope(Dispatchers.IO).launch {
            val beers= beerService.getBeersList()
            Log.d("test", "test")
        }
    }

}





