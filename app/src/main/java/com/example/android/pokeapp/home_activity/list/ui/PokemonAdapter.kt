package com.example.android.pokeapp.home_activity.list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.data.models.domain.PokemonDetails
import com.example.android.pokeapp.R
import com.example.android.pokeapp.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonAdapter(private var mValues: List<PokemonDetails>?,
                         private val cellClickListener: CellClickListener
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private lateinit var binding: ItemPokemonBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }
    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        mValues?.let {

            val item = it[position]

            holder.tvPokemonName.text = item.name

            holder.tvWeight.text = item.weight.toString()+" g"

            holder.tvType.text = item.types.joinToString { it.type.name }

            Picasso.get()
                .load(item.picture.frontPicture)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(holder.ivPicture)

            holder.itemView.setOnClickListener { _ ->
                cellClickListener.onCellClickListener(item)
            }
        } ?: clearList()
    }
    override fun getItemCount(): Int {
        return mValues?.size ?: 0
    }
    inner class ViewHolder (mView: View): RecyclerView.ViewHolder(mView){
        val tvPokemonName = binding.tvPokemonName
        val ivPicture = binding.ivPicture
        val tvWeight = binding.tvWeight
        val tvType = binding.tvType
    }
    private fun clearList() {
        val emptyList = listOf<PokemonDetails>()
        mValues = emptyList
        notifyItemRangeRemoved(0, 0)
    }
}
interface CellClickListener{
    fun onCellClickListener(pokemonDetails: PokemonDetails)
}