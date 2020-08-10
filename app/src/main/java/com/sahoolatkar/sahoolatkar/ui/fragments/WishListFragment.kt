package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.ProductsAdapter
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_wishlist.*

class WishListFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        setUpRecycler()
    }

    private fun setUpRecycler() {
        rvWishList.layoutManager = GridLayoutManager(context, 2)
        rvWishList.adapter = ProductsAdapter(mainActivity, mainViewModel.wishListProducts, GlobalVariables.WISH_LIST_FRAGMENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}