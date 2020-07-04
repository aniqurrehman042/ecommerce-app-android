package com.sahoolatkar.sahoolatkar.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.LargeCategoriesRecyclerAdapter
import com.sahoolatkar.sahoolatkar.adapters.SliderAdapter
import com.sahoolatkar.sahoolatkar.adapters.SmallCategoriesRecyclerAdapter
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

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
        init()
    }

    private fun init() {
        setUpSlider()
        setUpRecyclers()
        setUp1stBanner()
    }

    private fun setUp1stBanner() {
        Picasso.get().load("https://p.bigstockphoto.com/GeFvQkBbSLaMdpKXF1Zv_bigstock-Aerial-View-Of-Blue-Lakes-And--227291596.jpg")
            .into(iv1stBanner)
    }

    private fun setUpRecyclers() {
        setUpCategoriesRecycler()
        setUpKitchenAppliancesRecycler()
        setUpMobilesRecycler()
    }

    private fun setUpMobilesRecycler() {
        var mobiles: MutableList<CategoryModel> = ArrayList()
        mobiles.add(CategoryModel("Toys", "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg"))
        mobiles.add(CategoryModel("Food", "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg"))
        mobiles.add(CategoryModel("Mobiles", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg"))
        mobiles.add(CategoryModel("Sports", "https://sport-numericus.com/wp-content/uploads/2019/06/041816_sports.jpg"))
        mobiles.add(CategoryModel("Toys", "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg"))
        mobiles.add(CategoryModel("Food", "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg"))
        mobiles.add(CategoryModel("Mobiles", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg"))
        mobiles.add(CategoryModel("Sports", "https://sport-numericus.com/wp-content/uploads/2019/06/041816_sports.jpg"))
        var mobilesAdapter = LargeCategoriesRecyclerAdapter(activity as Activity, mobiles)
        rvMobiles.layoutManager = GridLayoutManager(context, 3)
        rvMobiles.adapter = mobilesAdapter
    }

    private fun setUpKitchenAppliancesRecycler() {
        var kitchenAppliances: MutableList<CategoryModel> = ArrayList()
        kitchenAppliances.add(CategoryModel("Toys", "https://cdn4.iconfinder.com/data/icons/toys-5/100/Toys2-512.png"))
        kitchenAppliances.add(CategoryModel("Food", "https://cdn0.iconfinder.com/data/icons/e-commerce-207/1024/food-512.png"))
        kitchenAppliances.add(CategoryModel("Mobiles", "https://cdn4.iconfinder.com/data/icons/devices-24/64/iphone_iphone-512.png"))
        kitchenAppliances.add(CategoryModel("Sports", "https://image.flaticon.com/icons/png/512/2158/2158445.png"))
        kitchenAppliances.add(CategoryModel("Toys", "https://cdn4.iconfinder.com/data/icons/toys-5/100/Toys2-512.png"))
        kitchenAppliances.add(CategoryModel("Food", "https://cdn0.iconfinder.com/data/icons/e-commerce-207/1024/food-512.png"))
        kitchenAppliances.add(CategoryModel("Mobiles", "https://cdn4.iconfinder.com/data/icons/devices-24/64/iphone_iphone-512.png"))
        kitchenAppliances.add(CategoryModel("Sports", "https://image.flaticon.com/icons/png/512/2158/2158445.png"))
        var categoriesAdapter: SmallCategoriesRecyclerAdapter = SmallCategoriesRecyclerAdapter(activity as Activity, kitchenAppliances)
        rvKitchenAppliances.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvKitchenAppliances.adapter = categoriesAdapter
    }

    private fun setUpCategoriesRecycler() {
        var categories: MutableList<CategoryModel> = ArrayList()
        categories.add(CategoryModel("Toys", "https://cdn4.iconfinder.com/data/icons/toys-5/100/Toys2-512.png"))
        categories.add(CategoryModel("Food", "https://cdn0.iconfinder.com/data/icons/e-commerce-207/1024/food-512.png"))
        categories.add(CategoryModel("Mobiles", "https://cdn4.iconfinder.com/data/icons/devices-24/64/iphone_iphone-512.png"))
        categories.add(CategoryModel("Sports", "https://image.flaticon.com/icons/png/512/2158/2158445.png"))
        categories.add(CategoryModel("Toys", "https://cdn4.iconfinder.com/data/icons/toys-5/100/Toys2-512.png"))
        categories.add(CategoryModel("Food", "https://cdn0.iconfinder.com/data/icons/e-commerce-207/1024/food-512.png"))
        categories.add(CategoryModel("Mobiles", "https://cdn4.iconfinder.com/data/icons/devices-24/64/iphone_iphone-512.png"))
        categories.add(CategoryModel("Sports", "https://image.flaticon.com/icons/png/512/2158/2158445.png"))
        var categoriesAdapter: SmallCategoriesRecyclerAdapter = SmallCategoriesRecyclerAdapter(activity as Activity, categories)
        rvCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
    }

    private fun setUpSlider() {
        var sliderAdapter = SliderAdapter(activity as Context)
        sliderAdapter.addItem(SliderItemModel("https://image.shutterstock.com/image-photo/butterfly-grass-on-meadow-night-260nw-1111729556.jpg"))
        sliderAdapter.addItem(SliderItemModel("https://p.bigstockphoto.com/GeFvQkBbSLaMdpKXF1Zv_bigstock-Aerial-View-Of-Blue-Lakes-And--227291596.jpg"))
        sliderAdapter.addItem(SliderItemModel("https://eoimages.gsfc.nasa.gov/images/imagerecords/90000/90796/NHQ201708210304.jpg"))
        sliderAdapter.addItem(SliderItemModel("https://image.shutterstock.com/image-photo/butterfly-grass-on-meadow-night-260nw-1111729556.jpg"))
        sliderAdapter.addItem(SliderItemModel("https://p.bigstockphoto.com/GeFvQkBbSLaMdpKXF1Zv_bigstock-Aerial-View-Of-Blue-Lakes-And--227291596.jpg"))
        sliderAdapter.addItem(SliderItemModel("https://eoimages.gsfc.nasa.gov/images/imagerecords/90000/90796/NHQ201708210304.jpg"))
        imageSlider.setSliderAdapter(sliderAdapter)
        imageSlider.startAutoCycle()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}