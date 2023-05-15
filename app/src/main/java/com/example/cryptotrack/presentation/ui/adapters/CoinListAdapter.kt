package com.example.cryptotrack.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cryptotrack.R
import com.example.cryptotrack.databinding.CurrencyListLayoutBinding
import com.example.cryptotrack.domain.model.Coin
import com.example.cryptotrack.presentation.ui.fragments.HomeFragmentDirections

class CoinListAdapter : RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    private val coinListCallback = object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

    }
    val coinDifferCallback = AsyncListDiffer(this, coinListCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.currency_list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val coinPosition = coinDifferCallback.currentList[position]
        holder.apply {
            cardImage.load(coinPosition.image)
            coinName.text = coinPosition.name
            itemView.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
                it.findNavController().navigate(direction)
            }
        }
    }

    override fun getItemCount(): Int {
        return coinDifferCallback.currentList.size
    }

    inner class CoinListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val coinBinding = CurrencyListLayoutBinding.bind(itemView)
        val cardImage = coinBinding.cardImage
        val coinName = coinBinding.coinName

    }
}