package com.sahoolatkar.sahoolatkar.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.ui.BillingInfoActivity
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.ui.OptionalInfoActivity
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_payment_options.*
import java.io.Serializable

class PaymentOptionsFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        btCashOnDelievery.setOnClickListener{ startBillingInfoActivity();
        }
    }
    private fun startBillingInfoActivity()
    {
        val intent = Intent(requireActivity(), BillingInfoActivity::class.java)
        intent.putExtra("cartProducts", mainViewModel.cartProducts as Serializable)
        startActivity(intent)
    }

}