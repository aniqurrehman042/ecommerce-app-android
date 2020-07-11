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
        setUpMobilesRecycler()
    }

    private fun setUpMobilesRecycler() {
        var mobiles: MutableList<CategoryModel> = ArrayList()
        mobiles.add(CategoryModel("AC", "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg", R.drawable.ic_cat_ac))
        mobiles.add(CategoryModel("Cosmetics", "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg", R.drawable.ic_cat_cosmetics))
        mobiles.add(CategoryModel("Deep Freezer", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_deepfreezer))
        mobiles.add(CategoryModel("Furniture", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_furniture))
        mobiles.add(CategoryModel("Gaming", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_gaming))
        var mobilesAdapter = LargeCategoriesRecyclerAdapter(activity as Activity, mobiles)
        rvMobiles.layoutManager = GridLayoutManager(context, 3)
        rvMobiles.adapter = mobilesAdapter
    }

    private fun setUpCategoriesRecycler() {
        var categories: MutableList<CategoryModel> = ArrayList()
        categories.add(CategoryModel("AC", "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg", R.drawable.ic_cat_ac))
        categories.add(CategoryModel("Cosmetics", "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg", R.drawable.ic_cat_cosmetics))
        categories.add(CategoryModel("Deep Freezers", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_deepfreezer))
        categories.add(CategoryModel("Furniture", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_furniture))
        categories.add(CategoryModel("Gaming", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_gaming))
        categories.add(CategoryModel("Home Appliances", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_home_appliances))
        categories.add(CategoryModel("Mobiles", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_mobile))
        categories.add(CategoryModel("Motorcycles", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_motorcycle))
        categories.add(CategoryModel("Room Coolers", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_roomcooler))
        categories.add(CategoryModel("Travels", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_travels))
        categories.add(CategoryModel("Water Dispenser", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg", R.drawable.ic_cat_waterdespensor))
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