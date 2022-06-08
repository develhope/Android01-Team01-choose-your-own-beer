package co.develhope.chooseyourownbeer.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import co.develhope.chooseyourownbeer.BeerAction
import co.develhope.chooseyourownbeer.BeerAdapter
import co.develhope.chooseyourownbeer.R
import co.develhope.chooseyourownbeer.databinding.ActivityMainBinding
import co.develhope.chooseyourownbeer.usecase.PunkSearchViewModel
import co.develhope.chooseyourownbeer.usecase.model.PunkRepository
import com.android.example.cleanarchietetture_viemodellivedata.MyApplication
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PunkSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as MyApplication).mainViewModelFactory.create(PunkSearchViewModel::class.java)

        observerRepos()
        viewModel.retrieveRepos("beers")

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


    private fun observerRepos(){
        viewModel.repos.observe(this) {
            showRepos(it)
        }
        viewModel.error.observe(this) {
            Snackbar.make(
                findViewById(R.id.container),
                "Error:$it",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Retry") {
                viewModel.retrieveRepos("beers")
            }.show()
        }
    }

    fun showRepos(repoResults: List<PunkRepository>){
        Log.d("PunkSearchScreen", "list of repos received, size: ${repoResults.size}")
        val list = findViewById<RecyclerView>(R.id.repo_list_beer)
        list.visibility = View.VISIBLE
        val adapter = BeerAdapter(
            repoResults,
        ) { action -> onAdapterClick(action) }
        list.adapter = adapter
    }
    private fun onAdapterClick(action: BeerAction) {
    }
    }