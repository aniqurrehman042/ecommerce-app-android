package com.sahoolatkar.sahoolatkar.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sahoolatkar.sahoolatkar.ui.fragments.ProductOverviewFragment
import com.sahoolatkar.sahoolatkar.ui.fragments.ProductSpecificationsFragment

class ProductDetailsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val productOverviewFragment: ProductOverviewFragment = ProductOverviewFragment()
    private val productSpecificationsFragment: ProductSpecificationsFragment = ProductSpecificationsFragment()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> productOverviewFragment
            1 -> productSpecificationsFragment
            else -> productOverviewFragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Overview"
            1 -> "Specifications"
            else -> super.getPageTitle(position)
        }
    }

    override fun getCount(): Int {
        return 2
    }
}