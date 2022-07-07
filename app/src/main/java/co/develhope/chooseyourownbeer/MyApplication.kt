package co.develhope.chooseyourownbeer

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import co.develhope.chooseyourownbeer.network.BeersProvider
import co.develhope.chooseyourownbeer.ui.model.MyViewModelFactory

class MyApplication : Application (){

    private val beersProvider = BeersProvider()

    lateinit var mainViewModelFactory: MyViewModelFactory
    lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        Log.d("My application", "Starter")
        preferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        mainViewModelFactory = MyViewModelFactory(beersProvider, preferences)
    }
}