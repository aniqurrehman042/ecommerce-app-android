package com.sahoolatkar.sahoolatkar.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductDetailsPagerAdapter
import com.sahoolatkar.sahoolatkar.adapters.ProductImgsSliderAdapter
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.models.CartProduct
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_product_details.*
import org.json.JSONObject

class ProductDetailsFragment : Fragment() {
    private lateinit var pagerAdapter: ProductDetailsPagerAdapter

    private val args: ProductDetailsFragmentArgs by navArgs()
    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()
    private var prices: MutableList<String> = ArrayList()

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
        setInstallments()
        setListeners()
    }

    private fun setInstallments() {
        if (args.product.meta_data.isNotEmpty() && args.product.meta_data[0].value.value[0] != '{') {
            ViewUtils.hideView(rgInstallments)
            ViewUtils.hideView(tvStartingFrom)
        } else {
            if (!args.product.variations.isNullOrEmpty()) {
                getPrices()
                setPrices()
            }
        }
    }

    private fun setPrices() {
        rb1Installments.text = rb1Installments.text.toString() + " " + prices[0]
        rb3Installments.text = rb3Installments.text.toString() + " " + prices[1]
        rb6Installments.text = rb6Installments.text.toString() + " " + prices[2]
        rb9Installments.text = rb9Installments.text.toString() + " " + prices[3]
        rb12Installments.text = rb12Installments.text.toString() + " " + prices[4]
    }

    private fun getPrices() {
        val valueJsonString = args.product.meta_data[0].value.value
        val valueJsonObject = JSONObject(valueJsonString)
        val variationIdObjectsContainer = valueJsonObject.getJSONObject(valueJsonObject.keys().next()).getJSONObject("r")

        for (variationIdObjectKey in variationIdObjectsContainer.keys()) {
            val variationIdObject = variationIdObjectsContainer.getJSONObject(variationIdObjectKey)
            val variationObject = variationIdObject.getJSONObject(variationIdObject.keys().next())
            prices.add(variationObject.getString("p"))
        }
    }

    private fun setListeners() {
        tvAddToCart.setOnClickListener {
            addCartItem()
        }
        tvPlus.setOnClickListener {
            addQty()
        }
        tvMinus.setOnClickListener {
            minusQty()
        }
        rgInstallments.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                rb1Installments.id -> tvPrice.text = prices[0]
                rb3Installments.id -> tvPrice.text = prices[1]
                rb6Installments.id -> tvPrice.text = prices[2]
                rb9Installments.id -> tvPrice.text = prices[3]
                rb12Installments.id -> tvPrice.text = prices[4]
            }
        }
    }

    private fun addCartItem() {
        var found = false
        val installments = when {
            rb3Installments.isChecked -> GlobalVariables.INSTALLMENTS_3
            rb6Installments.isChecked -> GlobalVariables.INSTALLMENTS_6
            rb9Installments.isChecked -> GlobalVariables.INSTALLMENTS_9
            rb12Installments.isChecked -> GlobalVariables.INSTALLMENTS_12
            else -> GlobalVariables.INSTALLMENTS_1
        }

        for (cartProduct in mainViewModel.cartProducts) {
            if (cartProduct.product === args.product) {
                cartProduct.quantity = tvQty.text.toString().toInt()
                cartProduct.installments = installments
                found = true
            }
        }

        if (!found) {
            mainViewModel.cartProducts.add(
                CartProduct(
                    args.product,
                    tvQty.text.toString().toInt(),
                    installments
                )
            )
            mainActivity.onAddCartItem()
        } else {
            mainActivity.blinkCart()
        }
        mainActivity.playThrowSound()
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
        pagerAdapter = ProductDetailsPagerAdapter(this, args.product)
        vpProductDetails.adapter = pagerAdapter
        TabLayoutMediator(tlProductDetails, vpProductDetails) { tab, pos ->
            tab.text = when (pos) {
                1 -> "Specifications"
                else -> "Overview"
            }
        }.attach()
    }

    private fun minusQty() {
        var num: Int = (tvQty.text).toString().toInt()
        if (num > 1)
            num--
        tvQty.text = num.toString()
    }

    private fun addQty() {
        var num: Int = (tvQty.text).toString().toInt()
        num++
        tvQty.text = num.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}
