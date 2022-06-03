package co.develhope.chooseyourownbeer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import co.develhope.chooseyourownbeer.Beers
import co.develhope.chooseyourownbeer.databinding.BeerDetailBinding
import co.develhope.chooseyourownbeer.model.Beer

class BeerDetailActivity : AppCompatActivity() {

    private var _binding: BeerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = BeerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val beerId: Int = intent.getIntExtra("BEER_ID", 0)
        val beer = beerId.let { Beers.getBeerFromId(it) } ?: {binding.beerError.visibility = View.VISIBLE}
        setupUI(beer as Beer)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    // When the toolbar arrow button is pressed, BeerDetailActivity closes.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupUI(beer: Beer) {
        binding.imageBeer.setImageResource(beer.imagePath)
        binding.titleBeer.text = beer.title
        binding.size.text = beer.size.toString()
        binding.longDescription.text = beer.fullDescription
    }
}