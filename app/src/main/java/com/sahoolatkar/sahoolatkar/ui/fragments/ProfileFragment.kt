package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import kotlinx.android.synthetic.*

class ProfileFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onResume() {
        super.onResume()
        mainActivity.hideTopBar()
    }

    override fun onPause() {
        super.onPause()
        mainActivity.showTopBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}