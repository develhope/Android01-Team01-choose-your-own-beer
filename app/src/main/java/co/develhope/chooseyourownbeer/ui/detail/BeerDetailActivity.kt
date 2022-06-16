package co.develhope.chooseyourownbeer.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import co.develhope.chooseyourownbeer.Beers
import co.develhope.chooseyourownbeer.R
import co.develhope.chooseyourownbeer.databinding.BeerDetailBinding
import co.develhope.chooseyourownbeer.ui.model.BeerUi

class BeerDetailActivity : AppCompatActivity() {

    private var _binding: BeerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = BeerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val beerId: Int = intent.getIntExtra("BEER_ID", 0)

        val beer = beerId.let {
            Beers.getBeerFromId(it) } ?: {
            binding.beerError.visibility = View.VISIBLE}
        setupUI(beer as BeerUi)

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

    private fun setupUI(beerUi: BeerUi) {
        binding.imageBeer.setImageResource(beerUi.iconBeer)
        binding.titleBeer.text = beerUi.title
        binding.size.text = beerUi.size.toString()
        binding.longDescription.text = beerUi.fullDescription
        if (beerUi.favourite) {
            binding.favouriteButton.setImageResource(R.drawable.fullstar)
        } else {
            binding.favouriteButton.setImageResource(R.drawable.emptystar)
        }
    }
}