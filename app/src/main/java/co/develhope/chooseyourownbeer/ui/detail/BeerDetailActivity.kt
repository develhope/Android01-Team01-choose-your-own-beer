package co.develhope.chooseyourownbeer.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.develhope.chooseyourownbeer.Beers
import co.develhope.chooseyourownbeer.R
import co.develhope.chooseyourownbeer.databinding.BeerDetailBinding
import co.develhope.chooseyourownbeer.network.setImageByUrl
import co.develhope.chooseyourownbeer.ui.model.BeerUi

class BeerDetailActivity : AppCompatActivity() {

    private lateinit var binding: BeerDetailBinding

    companion object {
        private const val BEER = "BEER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BeerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val beer: BeerUi? = intent.getParcelableExtra(BEER)
        if (beer != null) {
            setupUI(beer)
            binding.beerError.visibility = View.INVISIBLE
        } else {
            binding.beerError.visibility = View.VISIBLE
        }

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

    @SuppressLint("SetTextI18n")
    private fun setupUI(beerUi: BeerUi) {
        binding.imageBeer.setImageByUrl(
            beerUi.iconBeer,
            resources.getDimension(R.dimen.image_width_big_detail).toInt(),
            resources.getDimension(R.dimen.image_height_big_detail).toInt()
        )
        binding.titleBeer.text = beerUi.title
        (beerUi.size.toString() + " cl").also { it.also { binding.size.text = it } }
        binding.longDescription.text = beerUi.fullDescription
        setButtonState(beerUi.favourite)

        binding.favouriteButton.setOnClickListener {
            Beers.switchFavorite(beerUi)
            setupUI(Beers.getBeers().first { it.id == beerUi.id })
        }
    }

    private fun setButtonState(favourite: Boolean) {
        if (favourite) {
            binding.favouriteButton.setImageResource(R.drawable.fullstar)
        } else {
            binding.favouriteButton.setImageResource(R.drawable.emptystar)
        }
    }
}