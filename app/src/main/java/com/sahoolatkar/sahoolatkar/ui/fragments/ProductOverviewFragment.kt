package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.sahoolatkar.sahoolatkar.R
import kotlinx.android.synthetic.main.fragment_product_overview.*

class ProductOverviewFragment(val shortDescription: String) : Fragment() {

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
        return inflater.inflate(R.layout.fragment_product_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvHighlights.text = Html.fromHtml(shortDescription, Html.FROM_HTML_MODE_COMPACT)
        } else {
            tvHighlights.text = HtmlCompat.fromHtml(shortDescription, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    }
}