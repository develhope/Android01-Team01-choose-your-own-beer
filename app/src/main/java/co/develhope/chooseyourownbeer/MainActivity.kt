package co.develhope.chooseyourownbeer

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import co.develhope.chooseyourownbeer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.hide()

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

    private fun changeFragment(
        f: Fragment,
        addToBackStack: Boolean = true,
        tag: String = ""
    ) {
        val ft = supportFragmentManager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(null)
        }

        ft.replace(R.id.nav_host_fragment_activity_main, f, tag)
        if (!supportFragmentManager.isStateSaved)
            ft.commit()
    }

    fun goTo(
        fragment: Fragment,
        addToBackStack: Boolean,
        tag: String
    ) {
        changeFragment(
            fragment,
            tag = tag,
            addToBackStack = addToBackStack
        )
    }
}