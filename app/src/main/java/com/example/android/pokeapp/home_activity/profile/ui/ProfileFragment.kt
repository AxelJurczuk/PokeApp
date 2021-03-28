package com.example.android.pokeapp.home_activity.profile.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android.pokeapp.R
import com.example.android.pokeapp.databinding.FragmentProfileBinding
import com.example.android.pokeapp.home_activity.profile.vm.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding?=null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            profileViewModel.saveName(binding.etName.text.toString())
            profileViewModel.saveLastName(binding.etLastName.text.toString())
            profileViewModel.saveEmail(binding.etEmail.text.toString())
            profileViewModel.saveFavoritePokemon(binding.etPokemon.text.toString())

            profileViewModel.fetchProfile()
            Toast.makeText(requireContext(), "saved changes", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_listFragment)
            Log.i("juan", profileViewModel.toString())
        }
        Log.i("juan", profileViewModel.toString())

    }


}