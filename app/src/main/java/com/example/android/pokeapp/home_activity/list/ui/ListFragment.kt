package com.example.android.pokeapp.home_activity.list.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.data.models.domain.PokemonDetails
import com.example.android.pokeapp.R
import com.example.android.pokeapp.commons.BaseFragment
import com.example.android.pokeapp.commons.uicomponents.ErrorDialog
import com.example.android.pokeapp.databinding.FragmentListBinding
import com.example.android.pokeapp.home_activity.list.vm.ListViewModel
import com.example.android.pokeapp.utils.SharedPokemonVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment(), CellClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ListViewModel by sharedViewModel()
    private val sharedPokemonVM: SharedPokemonVM by sharedViewModel()

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

        binding.recyclerView.layoutManager = GridLayoutManager(
            activity,
            2, GridLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.setHasFixedSize(true)

        listViewModel.pokemonList.observe(viewLifecycleOwner) {
            adapter = PokemonAdapter(it, this)
            adapter.notifyDataSetChanged()
            binding.recyclerView.adapter = adapter
        }

        listViewModel.showMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        listViewModel.showError.observe(viewLifecycleOwner, {
            errorDialog = activity?.let { activity ->
                ErrorDialog(
                    activity,
                    getString(R.string.alert),
                    it,
                    getString(R.string.close)
                ) {
                    errorDialog?.dismiss()
                }
            }
            errorDialog!!.setCancelable(false)
            errorDialog!!.show()
        })

        listViewModel.isLoading.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(pokemonDetails: PokemonDetails) {
        sharedPokemonVM.setPokemonDetails(pokemonDetails)
        findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
    }
}