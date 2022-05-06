package co.develhope.chooseyourownbeer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import co.develhope.chooseyourownbeer.databinding.ActivityDetailBinding
import co.develhope.chooseyourownbeer.databinding.ActivityMainBinding
import co.develhope.chooseyourownbeer.model.Beer
import com.google.android.material.bottomnavigation.BottomNavigationView

class BeerDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val beerId = intent.getLongExtra("BEER_ID", 0)
        val beer = Beers.getBeerFromId(beerId)
        Beers.getBeerFromId(beerId)?.let { setupUI(it) }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {
            finish()
            overridePendingTransition(0, 0)
            return@setOnItemSelectedListener false
        }
    }

    fun setupUI(beer: Beer) {
        binding.layoutContainer.imageBeer.setImageResource(beer.imagePath)
        binding.layoutContainer.titleBeer.text = beer.title
        binding.layoutContainer.size.text = beer.size.toString()
        binding.layoutContainer.longDescription.text = beer.fullDescription
    }
}