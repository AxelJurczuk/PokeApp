package com.example.android.pokeapp.home_activity.list.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.data.remote.ResultHandler
import com.example.android.pokeapp.R
import com.example.android.pokeapp.databinding.FragmentListBinding
import com.example.android.pokeapp.home_activity.list.vm.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding?=null
    private val binding get() = _binding!!

    private val listViewModel:ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.pokemonList.observe(viewLifecycleOwner){
            binding.tvName.text = it.joinToString { it.name }
            binding.tvPicture.text = it.joinToString { it.picture.frontPicture }
            binding.tvType.text = it.joinToString { it.types[0].type.name }
            binding.tvWeight.text = it.joinToString { it.weight.toString() }

            Log.i("pokeList",it.joinToString { it.name})
            Log.i("pokeList",it.joinToString { it.picture.frontPicture})
            Log.i("pokeList",it.joinToString { it.types[0].type.name})
            Log.i("pokeList",it.joinToString { it.weight.toString()})

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}