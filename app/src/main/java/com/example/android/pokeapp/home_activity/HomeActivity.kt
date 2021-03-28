package com.example.android.pokeapp.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.android.pokeapp.R
import com.example.android.pokeapp.databinding.ActivityMainBinding
import com.example.android.pokeapp.home_activity.list.vm.ListViewModel
import com.example.android.pokeapp.home_activity.profile.vm.ProfileViewModel
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val profileViewModel: ProfileViewModel by viewModel()

    private val listViewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel.fetchProfile()
        listViewModel.fetchPokemons()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val header = binding.navView.getHeaderView(0)
        val profileName = header.findViewById<TextView>(R.id.tv_profile_name)
        val profileLastName = header.findViewById<TextView>(R.id.tv_profile_last_name)
        val profileEmail = header.findViewById<TextView>(R.id.tv_email)
        val profilePokemon = header.findViewById<TextView>(R.id.tv_profile_pokemon)

        profileViewModel.profile.observe(this) {
            profileName.text = it.name
            profileLastName.text = it.lastName
            profileEmail.text = it.email
            profilePokemon.text = it.favoritePokemon
            Log.i("juan", it.name)
        }


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // menuInflater.inflate(R.menu.drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}