package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.SliderAdapter
import com.sahoolatkar.sahoolatkar.adapters.SmallHorizontalRecyclerAdapter
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpSlider()
        setUpRecyclers()
    }

    private fun setUpRecyclers() {
        var categories: MutableList<CategoryModel> = ArrayList()
        categories.add(CategoryModel("Toys", "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg"))
        categories.add(CategoryModel("Food", "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg"))
        categories.add(CategoryModel("Mobiles", "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg"))
        categories.add(CategoryModel("Sports", "https://sport-numericus.com/wp-content/uploads/2019/06/041816_sports.jpg"))
        var categoriesAdapter: SmallHorizontalRecyclerAdapter = SmallHorizontalRecyclerAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
    }

    private fun setUpSlider() {
        var sliderAdapter = SliderAdapter(context!!)
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