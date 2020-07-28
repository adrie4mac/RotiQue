package id.adrie.rotique

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionManager
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    private val customBackIcon: Drawable? by lazy {
        ContextCompat.getDrawable(this, R.drawable.vector_back_button)
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }

    private val toolbar: Toolbar? by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        lifecycleScope.launch {
            navController.graph = navController.navInflater.inflate(R.navigation.app_navigation_graph).apply {
                startDestination = R.id.home_fragment
            }
            setupActionBarWithNavController(navController, appBarConfiguration)
            navController.addOnDestinationChangedListener { _, _, _ ->
                TransitionManager.beginDelayedTransition(toolbar)
                supportActionBar?.apply {
                    setHomeAsUpIndicator(customBackIcon)
                    setDisplayShowTitleEnabled(false)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchMenu = menu?.findItem(R.id.appSearchBar)
        val searchView = searchMenu?.actionView as? SearchView
        searchView?.apply {
            queryHint = "Search"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val data = query
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val data1 = newText
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}