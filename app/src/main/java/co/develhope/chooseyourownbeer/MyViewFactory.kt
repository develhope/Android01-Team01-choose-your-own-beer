package com.android.example.cleanarchietetture_viemodellivedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.develhope.chooseyourownbeer.network.PunkProvider
import co.develhope.chooseyourownbeer.usecase.PunkSearchViewModel
import java.lang.IllegalArgumentException

//Add extend view model

class MyViewFactory(private val punkProvider: PunkProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PunkSearchViewModel::class.java)){
            return PunkSearchViewModel(punkProvider) as T
        }
        throw IllegalArgumentException("ViewModel unKnow")
    }
}