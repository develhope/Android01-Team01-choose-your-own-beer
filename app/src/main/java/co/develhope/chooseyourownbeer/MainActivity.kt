package co.develhope.chooseyourownbeer

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.develhope.chooseyourownbeer.databinding.ActivityMainBinding
import co.develhope.chooseyourownbeer.network.BeersResult
import co.develhope.chooseyourownbeer.network.PunkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //Build retrofit
    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Create class
    var punkService = retrofit.create(PunkService::class.java)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Call method for beer repo
        retrievePunkRepos()

        //Hide actionBar
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile
            )
        )
        setupWithNavController(navView, navController)
        navView.setupWithNavController(navController)
    }
    fun retrievePunkRepos(){
        lifecycleScope.launch{
            try{
                val repos = punkService.listRepos("beers")
                //@TODO showRepos(repos)
            } catch (e: Exception){
                Log.e("MainActivity", "Error retrieving repos: $e")
                Snackbar.make(findViewById(R.id.container), "Error retrieving repos",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry"){ retrievePunkRepos() }.show()
            }
        }
    }
    //@TODO create function showRepos(repos)
}