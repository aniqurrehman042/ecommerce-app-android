package com.sahoolatkar.sahoolatkar.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sahoolatkar.sahoolatkar.ui.fragments.GettingStartedPayLaterFragment
import com.sahoolatkar.sahoolatkar.ui.fragments.GettingStartedPurchasingFragment
import com.sahoolatkar.sahoolatkar.ui.fragments.GettingStartedRegistrationFragment
import com.sahoolatkar.sahoolatkar.ui.fragments.GettingStartedVerificationFragment

class GettingStartedPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    private val gettingStartedRegistrationFragment: GettingStartedRegistrationFragment = GettingStartedRegistrationFragment()
    private val gettingStartedVerificationFragment: GettingStartedVerificationFragment = GettingStartedVerificationFragment()
    private val gettingStartedPurchasingFragment: GettingStartedPurchasingFragment = GettingStartedPurchasingFragment()
    private val gettingStartedPayLaterFragment: GettingStartedPayLaterFragment = GettingStartedPayLaterFragment()

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return gettingStartedRegistrationFragment
            }
            1 -> {
                return gettingStartedVerificationFragment
            }
            2 -> {
                return gettingStartedPurchasingFragment
            }
            3 -> {
                return gettingStartedPayLaterFragment
            }
        }

        return gettingStartedRegistrationFragment
    }

    override fun getCount(): Int {
        return 4
    }
}