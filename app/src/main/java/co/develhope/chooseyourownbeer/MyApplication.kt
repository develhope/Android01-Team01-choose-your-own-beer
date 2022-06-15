package com.android.example.cleanarchietetture_viemodellivedata

import android.app.Application
import android.util.Log
import co.develhope.chooseyourownbeer.network.BeersProvider
import co.develhope.chooseyourownbeer.ui.MyViewModelFactory

class MyApplication : Application (){

    private val punkProvider = BeersProvider()

    val mainViewModelFactory = MyViewModelFactory(punkProvider)

    override fun onCreate() {
        super.onCreate()
        Log.d("My application", "Starter")
    }
}