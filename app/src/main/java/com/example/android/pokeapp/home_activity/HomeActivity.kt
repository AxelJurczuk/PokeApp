package com.example.android.pokeapp.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.android.pokeapp.R
import com.example.android.pokeapp.databinding.ActivityMainBinding
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        toggle= ActionBarDrawerToggle(this, binding.drawerLayout , R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        /*
        NavigationUI.setupActionBarWithNavController
      binding.navView.setNavigationItemSelectedListener {
          when (it.itemId){
              R.id.profileFragment->{ Toast.makeText(applicationContext,
                  "item 1 clicked", Toast.LENGTH_SHORT).show()
              //navigation
                  }
              R.id.listFragment-> Toast.makeText(applicationContext,
                  "item 2 clicked", Toast.LENGTH_SHORT).show()
              R.id.item_3-> Toast.makeText(applicationContext,
                  "item 3 clicked", Toast.LENGTH_SHORT).show()
          }
          true
      }

       */
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}