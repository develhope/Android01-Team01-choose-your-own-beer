package co.develhope.chooseyourownbeer.network.dto

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.develhope.chooseyourownbeer.R
import co.develhope.chooseyourownbeer.ui.MainActivity

class BeersResult {



    fun  list(mainActivity: MainActivity, beersResult: BeersResult) {
        Log.d("MainActivity", "list of beers: ${(beersResult)}")

        val list = mainActivity.findViewById<RecyclerView>(R.id.beer_list)
        list.visibility = View.VISIBLE
        list.adapter = list.adapter
        list.layoutManager = LinearLayoutManager(mainActivity)

        }
    }




