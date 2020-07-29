package com.sahoolatkar.sahoolatkar.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductDetailsPagerAdapter
import com.sahoolatkar.sahoolatkar.adapters.ProductImgsSliderAdapter
import com.sahoolatkar.sahoolatkar.models.CartProduct
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsFragment : Fragment() {
    private lateinit var pagerAdapter: ProductDetailsPagerAdapter

    private val args: ProductDetailsFragmentArgs by navArgs()
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        setViewValues()
        initViewPager()
        setUpTopSlider()
        setListeners()
    }

    private fun setListeners() {
        tvAddToCart.setOnClickListener {
            addCartItem()
        }
        tvPlus.setOnClickListener{
         addQty()
        }
        tvMinus.setOnClickListener{
            minusQty()
        }
    }

    private fun addCartItem() {
        var found = false
        for (cartProduct in mainViewModel.cartProducts) {
            if (cartProduct.product === args.product) {
                cartProduct.quantity = tvQty.text.toString().toInt()
                found = true
            }
        }

        if (!found) {
            mainViewModel.cartProducts.add(
                CartProduct(
                    args.product,
                    tvQty.text.toString().toInt()
                )
            )
            mainActivity.addCartItem()
        }
    }

    private fun setUpTopSlider() {
        val sliderAdapter = ProductImgsSliderAdapter(activity as Context, args.product.images, true)
        productImgSlider.adapter = sliderAdapter

        productImgSlider.clipToPadding = false
        productImgSlider.setPadding(70, 0, 70, 0)
        productImgSlider.pageMargin = 20
    }

    private fun setViewValues() {
        tvProductName.text = args.product.name
        tvPrice.text = args.product.price
    }

    private fun initViewPager() {
        pagerAdapter = ProductDetailsPagerAdapter(mainActivity.supportFragmentManager)
        vpProductDetails.adapter = pagerAdapter
    }
    private fun minusQty()
    {
        var num :Int = (tvQty.text).toString().toInt()
        if(num>1)
        num--
        tvQty.text  = num.toString()
    }
    private fun addQty()
    {
        var num :Int = (tvQty.text).toString().toInt()
        num++
        tvQty.text  = num.toString()
    }
}
