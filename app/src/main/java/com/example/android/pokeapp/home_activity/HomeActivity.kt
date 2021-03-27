package com.example.android.pokeapp.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.android.pokeapp.R
import com.example.android.pokeapp.databinding.ActivityMainBinding
import com.example.android.pokeapp.home_activity.list.vm.ListViewModel
import com.example.android.pokeapp.home_activity.profile.vm.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    private val profileViewModel: ProfileViewModel by viewModel()
    private val listViewModel:ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listViewModel.fetchPokemons()
        profileViewModel.fetchProfile()

        val header = binding.navView.getHeaderView(0)
        val profileName= header.findViewById<TextView>(R.id.tv_profile_name)
        val profileLastName= header.findViewById<TextView>(R.id.tv_profile_last_name)
        val profileEmail= header.findViewById<TextView>(R.id.tv_email)
        val profilePokemon= header.findViewById<TextView>(R.id.tv_profile_pokemon)

        profileViewModel.profile.observe(this){
            profileName.text = it.name
            profileLastName.text = it.lastName
            profileEmail.text = it.email
            profilePokemon.text = it.favoritePokemon
        }

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