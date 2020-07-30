package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.CartRecyclerAdapter
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainActivity = requireActivity() as MainActivity
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        setUpRecycler()
        setListeners()
    }

    private fun setListeners() {
        btBuyNow.setOnClickListener(View.OnClickListener {
            view?.findNavController()?.navigate(R.id.action_cartFragment_to_paymentOptionsFragment);
        })
    }


    private fun setUpRecycler() {
        rvCart.layoutManager = LinearLayoutManager(mainActivity)
        rvCart.adapter = CartRecyclerAdapter(mainActivity, mainViewModel.cartProducts)
    }
}