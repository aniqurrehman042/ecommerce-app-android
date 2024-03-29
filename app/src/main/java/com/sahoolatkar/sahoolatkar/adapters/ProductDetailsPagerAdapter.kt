package com.sahoolatkar.sahoolatkar.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sahoolatkar.sahoolatkar.api_models.product.Product
import com.sahoolatkar.sahoolatkar.ui.fragments.ProductOverviewFragment
import com.sahoolatkar.sahoolatkar.ui.fragments.ProductSpecificationsFragment

class ProductDetailsPagerAdapter(
    fragment: Fragment,
    val product: Product
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProductOverviewFragment(product.short_description)
            1 -> ProductSpecificationsFragment(product.attributes)
            else -> ProductOverviewFragment(product.short_description)
        }
    }
}