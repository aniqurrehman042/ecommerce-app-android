package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.OrdersAdapter
import com.sahoolatkar.sahoolatkar.api_models.order.Order
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.LoadingUtils
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_order_history.*
import kotlinx.android.synthetic.main.layout_loader.*

class OrderHistoryFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         init()
    }

    private fun init() {
        initOrders()
    }

    private fun initOrders()
    {
        LoadingUtils.showLoader(mainActivity, llLoader, "Loading Orders...")
        mainViewModel.orders.observe(viewLifecycleOwner, Observer {
            initOrdersRecycler(it)
            LoadingUtils.hideLoader(mainActivity, llLoader)
        })
    }

    private fun initOrdersRecycler(orders: List<Order>?) {
        val ordersAdapter =
            OrdersAdapter(mainActivity, orders!!)
        rvOrders.layoutManager =
            LinearLayoutManager(context)
        rvOrders.adapter = ordersAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}