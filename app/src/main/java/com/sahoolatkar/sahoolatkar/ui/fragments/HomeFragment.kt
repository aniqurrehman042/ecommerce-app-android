package com.sahoolatkar.sahoolatkar.ui.fragments

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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.OffersSliderAdapter
import com.sahoolatkar.sahoolatkar.adapters.ProductsAdapter
import com.sahoolatkar.sahoolatkar.adapters.ProductsSliderAdapter
import com.sahoolatkar.sahoolatkar.adapters.SmallCategoriesRecyclerAdapter
import com.sahoolatkar.sahoolatkar.api_callbacks.IGetAllProductsCallback
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.ui.SplashActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun setUpViewModels() {
        mainViewModel.mobiles.observe(viewLifecycleOwner, Observer<List<ProductApiModel>> {
            setUpMobilesRecycler(it)
        })

        mainViewModel.featuredProducts.observe(viewLifecycleOwner, Observer {
            setUpFeaturedProductsSlider(it)
        })

        mainViewModel.offers.observe(viewLifecycleOwner, Observer {
            setUpOffersSlider(it)
            offersIndicator.updateIndicatorCounts(offersSlider.indicatorCount)
        })
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
        setUpViewModels()
        setUpRecyclers()
        setUp1stBanner()
        setUpTopSliderIndicator()
        setListeners()
    }

    private fun setListeners() {
        tvMobilesViewAll.setOnClickListener {
            startProductsCatalogFragment(GlobalVariables.CATEGORY_MOBILES_ID, "Mobiles")
        }

        tvHomeAppliancesViewAll.setOnClickListener {
            startProductsCatalogFragment(
                GlobalVariables.CATEGORY_HOME_APPLIANCES_ID,
                "Home Appliances"
            )
        }
    }

    private fun startProductsCatalogFragment(categoryId: String, categoryName: String) {
        val action =
            HomeFragmentDirections.actionHomeToProductsCatalogFragment(categoryId, categoryName)
        Navigation.findNavController(mainActivity, R.id.navHostFragment)
            .navigate(action)
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
            if (offersIndicator != null)
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
        setUpHomeAppliancesRecycler()
    }

    private fun setUpHomeAppliancesRecycler() {
        SahoolatKarApiUtils.getProductsByCategory(
            mainActivity,
            GlobalVariables.CATEGORY_HOME_APPLIANCES_ID,
            object :
                IGetAllProductsCallback {
                override fun onGetProducts(products: MutableList<ProductApiModel>) {

                    val homeAppliancesAdapter =
                        ProductsAdapter(
                            mainActivity,
                            products,
                            GlobalVariables.HOME_FRAGMENT
                        )
                    if (rvHomeAppliances != null) {
                        rvHomeAppliances.layoutManager = GridLayoutManager(context, 2)
                        rvHomeAppliances.adapter = homeAppliancesAdapter
                    }
                }
            })
    }

    private fun setUpMobilesRecycler(it: List<ProductApiModel>) {
        val mobilesAdapter =
            ProductsAdapter(
                mainActivity,
                it,
                GlobalVariables.HOME_FRAGMENT
            )
        rvMobiles.layoutManager = GridLayoutManager(mainActivity, 2)
        rvMobiles.adapter = mobilesAdapter
    }

    private fun setUpCategoriesRecycler() {
        val categories: MutableList<CategoryModel> = ArrayList()
        categories.add(
            CategoryModel(
                "Plastic Furniture",
                "",
                "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg",
                R.drawable.ic_ic1_cat_furniture
            )
        )
        categories.add(
            CategoryModel(
                "Mobile Phones",
                "21",
                "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg",
                R.drawable.ic_ic2_cat_mobile
            )
        )
        categories.add(
            CategoryModel(
                "Deep Freezers",
                "218",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic3_cat_deepfreezer
            )
        )
        categories.add(
            CategoryModel(
                "Home Appliances",
                "242",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic4_cat_home_appliances
            )
        )
        categories.add(
            CategoryModel(
                "Air Conditioners",
                "233",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic5_cat_ac
            )
        )
        categories.add(
            CategoryModel(
                "Room Coolers",
                "238",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic6_cat_roomcooler
            )
        )
        categories.add(
            CategoryModel(
                "Water Dispenser",
                "62",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic7_cat_waterdespensor
            )
        )
        categories.add(
            CategoryModel(
                "Motorcycle",
                "41",
                "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                R.drawable.ic_ic8_cat_motorcycle
            )
        )

        val categoriesAdapter =
            SmallCategoriesRecyclerAdapter(activity as Activity, categories)
        rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
    }

    private fun setUpOffersSlider(offers: List<ProductApiModel>) {
        val sliderAdapter = OffersSliderAdapter(activity as Context, offers, true)
        offersSlider.adapter = sliderAdapter

        offersSlider.clipToPadding = false
        offersSlider.setPadding(70, 0, 70, 0)
        offersSlider.pageMargin = 20

    }

    private fun setUpFeaturedProductsSlider(featuredProducts: List<ProductApiModel>) {
        val sliderAdapter = ProductsSliderAdapter(mainActivity, featuredProducts, true)
        featuredProductsSlider.adapter = sliderAdapter

        featuredProductsSlider.clipToPadding = false
        featuredProductsSlider.setPadding(70, 0, 70, 0)
        featuredProductsSlider.pageMargin = 20

    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}