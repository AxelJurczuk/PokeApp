package com.example.android.pokeapp.home_activity.details.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.pokeapp.R
import com.example.android.pokeapp.databinding.FragmentDetailsBinding
import com.example.android.pokeapp.databinding.FragmentListBinding
import com.example.android.pokeapp.utils.SharedPokemonVM
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding?=null
    private val binding get() = _binding!!

    private val sharedPokemonVM:SharedPokemonVM by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val pokemonDetails = sharedPokemonVM.pokemonDetails.value
        pokemonDetails?.let{
            binding.tvSpecies.text = it.species.name
            binding.tvBaseExperience.text = it.baseExperience.toString()
            binding.tvHeight.text = it.height.toString()
            binding.tvMoves.text = it.moves.joinToString { it.move.name }

            Picasso.get()
                .load(it.picture.frontPicture)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(binding.ivPokemon)
        }
    }
}