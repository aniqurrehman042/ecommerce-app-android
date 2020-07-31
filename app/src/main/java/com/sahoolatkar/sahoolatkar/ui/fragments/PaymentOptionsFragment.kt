package com.sahoolatkar.sahoolatkar.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.activity_result_contracts.OrderPlacementResultContract
import com.sahoolatkar.sahoolatkar.ui.BillingInfoActivity
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_payment_options.*
import java.io.Serializable

class PaymentOptionsFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        btCashOnDelievery.setOnClickListener {
            startBillingInfoActivity()
        }
    }

    private fun startBillingInfoActivity() {
        registerForActivityResult(OrderPlacementResultContract()) { orderPlacementSuccessful ->
            if (orderPlacementSuccessful) {
                mainViewModel.cartProducts.clear()
                mainActivity.resetCart()
                mainActivity.goBackToHomeFragment()
            }
        }.launch(mainViewModel.cartProducts)
    }

}