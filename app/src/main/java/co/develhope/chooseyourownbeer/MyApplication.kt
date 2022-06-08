package com.android.example.cleanarchietetture_viemodellivedata

import android.app.Application
import android.util.Log
import co.develhope.chooseyourownbeer.network.PunkProvider

class MyApplication : Application (){

    private val punkProvider = PunkProvider()

    val mainViewModelFactory = MyViewFactory(punkProvider)

    override fun onCreate() {
        super.onCreate()
        Log.d("My application", "Starter")
    }
    }