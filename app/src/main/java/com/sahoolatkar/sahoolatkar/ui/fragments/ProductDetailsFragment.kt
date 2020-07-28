package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductDetailsPagerAdapter
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsFragment : Fragment() {
    private lateinit var pagerAdapter: ProductDetailsPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter();
    }
fun initAdapter()
{
    pagerAdapter = ProductDetailsPagerAdapter(activity?.supportFragmentManager!!)
    vpProductDetails.adapter = pagerAdapter
    tlProductDetails!!.addTab(tlProductDetails!!.newTab().setText("Overview"))
    tlProductDetails!!.addTab(tlProductDetails!!.newTab().setText("Specifications"))

}
}
