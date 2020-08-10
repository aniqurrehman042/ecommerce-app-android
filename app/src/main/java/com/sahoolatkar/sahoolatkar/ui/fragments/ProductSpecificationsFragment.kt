package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.SpecificationsRecyclerAdapter
import com.sahoolatkar.sahoolatkar.api_models.product.Attributes
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_specifications.*

class ProductSpecificationsFragment(var attributes: List<Attributes>) : Fragment() {

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        setUpRecycler()
    }

    private fun setUpRecycler() {
        rvSpecifications.layoutManager = LinearLayoutManager(mainActivity)
        rvSpecifications.adapter = SpecificationsRecyclerAdapter(attributes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}