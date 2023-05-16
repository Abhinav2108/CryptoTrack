package com.example.cryptotrack.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.cryptotrack.R
import com.example.cryptotrack.databinding.FragmentDetailsBinding
import com.example.cryptotrack.presentation.viewmodels.CoinDetailsByIdViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val coinDetailsByIdViewModel: CoinDetailsByIdViewModel by viewModels()
    private var id = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        detailsBinding = FragmentDetailsBinding.bind(view)
        id = args.id
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinDetailsByIdViewModel.getCoinById(id)
        requestApi(id)
    }

    private fun requestApi(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            coinDetailsByIdViewModel._coinValue.collect{
                when{
                    it.isLoading ->{
                        detailsBinding.detailsProgressBar.visibility = View.VISIBLE
                    }
                    it.errorMessage.isNotBlank() ->{
                        detailsBinding.detailsProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "${it.errorMessage}", Toast.LENGTH_LONG).show()
                    }
                    it.coinDetails!= null ->{
                        detailsBinding.apply {
                            detailsProgressBar.visibility = View.GONE
                            coinImage.load(it.coinDetails.image)
                            coinNameDetails.text = it.coinDetails.name
                            tvCurrentPrice.text = "Current Price: ${it.coinDetails.price_change_24h}"
                            tvHighPrice.text = "High Price: ${it.coinDetails.high_24h}"
                            tvLowPrice.text = "Low Price: ${it.coinDetails.low_24h}"
                            tvPricePercentChange.text = "Percentage Change: ${it.coinDetails.price_change_percentage_24h}%"
                            tvDescription.text = it.coinDetails.description
                        }
                    }
                }
            }
        }
    }

}