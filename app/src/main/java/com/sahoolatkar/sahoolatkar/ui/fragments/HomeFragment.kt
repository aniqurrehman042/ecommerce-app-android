package com.sahoolatkar.sahoolatkar.ui.fragments

import ProductApiModel
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.*
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.sahoolatkar.sahoolatkar.models.ProductModel
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.ui.SplashActivity
import com.sahoolatkar.sahoolatkar.utils.SahoolatKarApiUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var mainView: View
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
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
        setUpOffersSlider()
        setUpFeaturedProductsSlider()
        setUpRecyclers()
        setUp1stBanner()
        setUpTopSliderIndicator()
    }

    private fun setUpTopSliderIndicator() {
        offersIndicator.highlighterViewDelegate = {
            val highlighter = View(mainActivity)
            highlighter.layoutParams = FrameLayout.LayoutParams(16, 4)
            highlighter.setBackgroundColor(getColor(mainActivity, R.color.red))
            highlighter
        }
        offersIndicator.unselectedViewDelegate = {
            val unselected = View(mainActivity)
            unselected.layoutParams = LinearLayout.LayoutParams(16, 4)
            unselected.setBackgroundColor(getColor(mainActivity, R.color.dark_grey))
            unselected.alpha = 0.4f
            unselected
        }

        offersSlider.onIndicatorProgress = { selectingPosition, progress ->
            offersIndicator.onPageScrolled(selectingPosition, progress)
        }

        offersIndicator.updateIndicatorCounts(offersSlider.indicatorCount)
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

        val mobilesAdapter = ProductsAdapter(activity as Activity, mobiles, getString(R.string.fragment_home))
        rvMobiles.layoutManager = GridLayoutManager(context, 2)
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

        val mobilesAdapter = ProductsAdapter(activity as Activity, mobiles, getString(R.string.fragment_home))
        rvMobiles2.layoutManager = GridLayoutManager(context, 2)
        rvMobiles2.adapter = mobilesAdapter
    }

    private fun setUpCategoriesRecycler() {
        val categories: MutableList<CategoryModel> = ArrayList()
        categories.add(
            CategoryModel(
                "Plastic Furniture",
                "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg",
                R.drawable.ic_ic1_cat_furniture
            )
        )
        categories.add(
            CategoryModel(
                "Mobile Phones",
                "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg",
                R.drawable.ic_ic2_cat_mobile
            )
        )
        categories.add(
            CategoryModel(
                "Deep Freezers",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic3_cat_deepfreezer
            )
        )
        categories.add(
            CategoryModel(
                "Home Appliances",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic4_cat_home_appliances
            )
        )
        categories.add(
            CategoryModel(
                "AC",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic5_cat_ac
            )
        )
        categories.add(
            CategoryModel(
                "Room Coolers",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic6_cat_roomcooler
            )
        )
        categories.add(
            CategoryModel(
                "Water Dispenser",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic7_cat_waterdespensor
            )
        )
        categories.add(
            CategoryModel(
                "Motorcycle",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic8_cat_motorcycle
            )
        )
        categories.add(
            CategoryModel(
                "Cosmetics",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic9_cat_cosmetics
            )
        )
        categories.add(
            CategoryModel(
                "Travels",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic10_cat_travels
            )
        )
        categories.add(
            CategoryModel(
                "Gaming",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic11_cat_gaming
            )
        )
        val categoriesAdapter =
            SmallCategoriesRecyclerAdapter(activity as Activity, categories)
        rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
    }

    private fun setUpOffersSlider() {
        val offers = ArrayList<SliderItemModel>()
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/1-1-1030x472.png"))
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/2-1.png"))
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/1-1-1030x472.png"))
        val sliderAdapter = OffersSliderAdapter(activity as Context, offers, true)
        offersSlider.adapter = sliderAdapter

        offersSlider.clipToPadding = false;
        offersSlider.setPadding(70,0,70,0)
        offersSlider.pageMargin = 20

    }

    private fun setUpFeaturedProductsSlider() {
        val featuredProducts = ArrayList<ProductModel>()
        featuredProducts.add(
            ProductModel(
                "DW-550 AF",
                "Best Microwave",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/DW-550-AF.png",
                30000f,
                3
            )
        )
        featuredProducts.add(
            ProductModel(
                "32D3000 A",
                "Best LED TV",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/d3000fs_front.jpg",
                35000f,
                5
            )
        )
        featuredProducts.add(
            ProductModel(
                "43D2900",
                "Best LED TV",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/a__09932_zoom-310x310-1.jpg",
                50000f,
                10
            )
        )
        featuredProducts.add(
            ProductModel(
                "Active Cool 15 (H&C)",
                "Best AC",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/Pro-Active-inv.png",
                70000f,
                12
            )
        )
        featuredProducts.add(
            ProductModel(
                "Aura Invertor 30",
                "Best AC",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/download-7.jpg",
                50000f,
                10
            )
        )
        featuredProducts.add(
            ProductModel(
                "DW 298-G",
                "Best Microwave",
                "https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/05/DW-132-S.png",
                10000f,
                10
            )
        )

        var productsList: MutableList<ProductApiModel>? = null

        SahoolatKarApiUtils.getAllProducts(object : IGetAllProductsCallback {
            override fun onGetAllProducts(products: MutableList<ProductApiModel>) {
                productsList = products
            }
        })

        val sliderAdapter = ProductsSliderAdapter(activity as Context, featuredProducts, true)
        featuredProductsSlider.adapter = sliderAdapter

        featuredProductsSlider.clipToPadding = false
        featuredProductsSlider.setPadding(70,0,70,0)
        featuredProductsSlider.pageMargin = 20

    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}