package com.sahoolatkar.sahoolatkar.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.*
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.sahoolatkar.sahoolatkar.models.ProductModel
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import com.sahoolatkar.sahoolatkar.ui.SplashActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var mainView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_home, container, false)
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<ImageView>(R.id.ivLogo).setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    SplashActivity::class.java
                )
            )
        }
        init()
    }

    private fun init() {
        setUpSlider()
        setUpRecyclers()
        setUp1stBanner()
    }

    private fun setUp1stBanner() {
        Picasso.get()
            .load("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/1-1-1030x472.png")
            .into(iv1stBanner)
    }

    private fun setUpRecyclers() {
        setUpCategoriesRecycler()
        setUpMobilesRecycler()
        setUpMobiles2Recycler()
    }

    private fun setUpMobilesRecycler() {
        val mobiles: MutableList<ProductModel> = ArrayList()

        mobiles.add(
            ProductModel(
                "DW-550 AF",
                "Best Microwave",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/DW-550-AF.png",
                30000f,
                3
            )
        )
        mobiles.add(
            ProductModel(
                "32D3000 A",
                "Best LED TV",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/d3000fs_front.jpg",
                35000f,
                5
            )
        )
        mobiles.add(
            ProductModel(
                "43D2900",
                "Best LED TV",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/a__09932_zoom-310x310-1.jpg",
                50000f,
                10
            )
        )
        mobiles.add(
            ProductModel(
                "Active Cool 15 (H&C)",
                "Best AC",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/Pro-Active-inv.png",
                70000f,
                12
            )
        )
        mobiles.add(
            ProductModel(
                "Aura Invertor 30",
                "Best AC",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/download-7.jpg",
                50000f,
                10
            )
        )
        mobiles.add(
            ProductModel(
                "DW 298-G",
                "Best Microwave",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/DW-132-S.png",
                10000f,
                10
            )
        )

        val mobilesAdapter = ProductsAdapter(activity as Activity, mobiles)
        rvMobiles.layoutManager = GridLayoutManager(context, 3)
        rvMobiles.adapter = mobilesAdapter
    }

    private fun setUpMobiles2Recycler() {
        val mobiles: MutableList<ProductModel> = ArrayList()
        mobiles.add(
            ProductModel(
                "DW-550 AF",
                "Best Microwave",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/DW-550-AF.png",
                30000f,
                3
            )
        )
        mobiles.add(
            ProductModel(
                "32D3000 A",
                "Best LED TV",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/d3000fs_front.jpg",
                35000f,
                5
            )
        )
        mobiles.add(
            ProductModel(
                "43D2900",
                "Best LED TV",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/a__09932_zoom-310x310-1.jpg",
                50000f,
                10
            )
        )
        mobiles.add(
            ProductModel(
                "Active Cool 15 (H&C)",
                "Best AC",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/Pro-Active-inv.png",
                70000f,
                12
            )
        )
        mobiles.add(
            ProductModel(
                "Aura Invertor 30",
                "Best AC",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/download-7.jpg",
                50000f,
                10
            )
        )
        mobiles.add(
            ProductModel(
                "DW 298-G",
                "Best Microwave",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/DW-132-S.png",
                10000f,
                10
            )
        )

        val mobilesAdapter = ProductsAdapter(activity as Activity, mobiles)
        rvMobiles2.layoutManager = GridLayoutManager(context, 2)
        rvMobiles2.adapter = mobilesAdapter
    }

    private fun setUpCategoriesRecycler() {
        val categories: MutableList<CategoryModel> = ArrayList()
        categories.add(
            CategoryModel(
                "AC",
                "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg",
                R.drawable.ic_cat_ac
            )
        )
        categories.add(
            CategoryModel(
                "Cosmetics",
                "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg",
                R.drawable.ic_cat_cosmetics
            )
        )
        categories.add(
            CategoryModel(
                "Deep Freezers",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_deepfreezer
            )
        )
        categories.add(
            CategoryModel(
                "Furniture",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_furniture
            )
        )
        categories.add(
            CategoryModel(
                "Gaming",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_gaming
            )
        )
        categories.add(
            CategoryModel(
                "Home Appliances",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_home_appliances
            )
        )
        categories.add(
            CategoryModel(
                "Mobiles",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_mobile
            )
        )
        categories.add(
            CategoryModel(
                "Motorcycles",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_motorcycle
            )
        )
        categories.add(
            CategoryModel(
                "Room Coolers",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_roomcooler
            )
        )
        categories.add(
            CategoryModel(
                "Travels",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_travels
            )
        )
        categories.add(
            CategoryModel(
                "Water Dispenser",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_cat_waterdespensor
            )
        )
        val categoriesAdapter =
            SmallCategoriesRecyclerAdapter(activity as Activity, categories)
        rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
    }

    private fun setUpSlider() {
        val offers = ArrayList<SliderItemModel>()
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/1-1-1030x472.png"))
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/2-1.png"))
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/1-1-1030x472.png"))
        val sliderAdapter = OffersSliderAdapter(activity as Context, offers, true)
        imageSlider.adapter = sliderAdapter

        imageSlider.clipToPadding = false;
        imageSlider.setPadding(70,0,70,0)
        imageSlider.pageMargin = 20

    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}