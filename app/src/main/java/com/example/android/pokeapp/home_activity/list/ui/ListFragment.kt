package com.example.android.pokeapp.home_activity.list.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.data.models.domain.PokemonDetails
import com.example.android.data.remote.ResultHandler
import com.example.android.pokeapp.R
import com.example.android.pokeapp.databinding.FragmentListBinding
import com.example.android.pokeapp.home_activity.list.vm.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : Fragment(), CellClickListener {

    private var _binding: FragmentListBinding?=null
    private val binding get() = _binding!!

    private val listViewModel:ListViewModel by viewModel()

    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.fetchPokemons()

        listViewModel.pokemonList.observe(viewLifecycleOwner){
            adapter = PokemonAdapter(it, this)
            adapter.notifyDataSetChanged()
            binding.recyclerView.adapter=adapter
            binding.recyclerView.setHasFixedSize(true)
        }

        listViewModel.showMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(pokemonDetails: PokemonDetails) {
        Toast.makeText(requireContext(), pokemonDetails.name, Toast.LENGTH_SHORT).show()
    }

}