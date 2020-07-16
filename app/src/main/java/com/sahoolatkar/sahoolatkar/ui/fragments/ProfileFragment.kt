package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.utils.ViewUtils

class ProfileFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onResume() {
        super.onResume()
        ViewUtils.hideView(activity?.findViewById(R.id.vTopOval)!!)
        ViewUtils.hideView(activity?.findViewById(R.id.ll_topbar)!!)
        ViewUtils.hideView(activity?.findViewById(R.id.vEtVerticalLine)!!)
        ViewUtils.hideView(activity?.findViewById(R.id.ivSearchFilter)!!)
        ViewUtils.hideView(activity?.findViewById(R.id.etSearch)!!)
    }

    override fun onPause() {
        super.onPause()
        ViewUtils.showView(activity?.findViewById(R.id.vTopOval)!!)
        ViewUtils.showView(activity?.findViewById(R.id.ll_topbar)!!)
        ViewUtils.showView(activity?.findViewById(R.id.vEtVerticalLine)!!)
        ViewUtils.showView(activity?.findViewById(R.id.ivSearchFilter)!!)
        ViewUtils.showView(activity?.findViewById(R.id.etSearch)!!)
    }
}