package com.pucuk.binar_challenge_ch_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.pucuk.binar_challenge_ch_4.data.preferences.NotePreferences
import com.pucuk.binar_challenge_ch_4.ui.MyViewModels

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var viewModel: MyViewModels


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MyViewModels::class.java]
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_graph_container) as NavHostFragment

        navController = navHostFragment.navController


        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    this.supportActionBar?.show()
                    this.supportActionBar?.title = "Welcome to NotesApp"
                }
                else -> {this.supportActionBar?.hide()}
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ascending -> {
                NotePreferences(this).put("KEY_FILTER", "ASCENDING")
                viewModel.user.observe(this){
                    val bundle = Bundle().apply {
                        putParcelable("USER_ENTITY", it)
                    }
                    this.navController.navigate(R.id.homeFragment, bundle)
                }
                return true
            }
            R.id.descending -> {
                NotePreferences(this).put("KEY_FILTER", "DESCENDING")
                viewModel.user.observe(this){
                    val bundle = Bundle().apply {
                        putParcelable("USER_ENTITY", it)
                    }
                    this.navController.navigate(R.id.homeFragment, bundle)
                }
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}