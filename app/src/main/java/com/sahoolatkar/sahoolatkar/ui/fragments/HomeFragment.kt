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
import com.sahoolatkar.sahoolatkar.adapters.CategoriesRecyclerAdapter
import com.sahoolatkar.sahoolatkar.api_models.product.Product
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.ui.SplashActivity
import com.sahoolatkar.sahoolatkar.utils.FixedDataUtils
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
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

        mainViewModel.airConditioners.observe(viewLifecycleOwner, Observer<List<Product>> {
            setUpAirConditionersRecycler(it)
        })

        mainViewModel.mobiles.observe(viewLifecycleOwner, Observer<List<Product>> {
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

        tvAirConditionersViewAll.setOnClickListener {
            startProductsCatalogFragment(
                GlobalVariables.CATEGORY_AIR_CONDITIONERS,
                "Air Conditioners"
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
    }

    private fun setUpAirConditionersRecycler(airConditioners: List<Product>) {
        val airConditionersAdapter =
            ProductsAdapter(
                mainActivity,
                airConditioners,
                GlobalVariables.HOME_FRAGMENT
            )
        rvAirConditioners.layoutManager = GridLayoutManager(mainActivity, 2)
        rvAirConditioners.adapter = airConditionersAdapter
    }

    private fun setUpMobilesRecycler(mobiles: List<Product>) {
        val mobilesAdapter =
            ProductsAdapter(
                mainActivity,
                mobiles,
                GlobalVariables.HOME_FRAGMENT
            )
        rvMobiles.layoutManager = GridLayoutManager(mainActivity, 2)
        rvMobiles.adapter = mobilesAdapter
    }

    private fun setUpCategoriesRecycler() {
        val categories = FixedDataUtils.getCategoryList()

        val categoriesAdapter =
            CategoriesRecyclerAdapter(activity as Activity, categories)
        rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
    }

    private fun setUpOffersSlider(offers: List<Product>) {
        val sliderAdapter = OffersSliderAdapter(activity as Context, offers, true)
        offersSlider.adapter = sliderAdapter

        offersSlider.clipToPadding = false
        offersSlider.setPadding(70, 0, 70, 0)
        offersSlider.pageMargin = 20

    }

    private fun setUpFeaturedProductsSlider(featuredProducts: List<Product>) {
        val sliderAdapter = ProductsSliderAdapter(mainActivity, featuredProducts, true)
        featuredProductsSlider.adapter = sliderAdapter

        featuredProductsSlider.clipToPadding = false
        featuredProductsSlider.setPadding(70, 0, 70, 0)
        featuredProductsSlider.pageMargin = 20

    }

    override fun onResume() {
        super.onResume()
        mainActivity.findViewById<ImageView>(R.id.ivLogo).setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    SplashActivity::class.java
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}