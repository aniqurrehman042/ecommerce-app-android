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
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.sahoolatkar.sahoolatkar.models.ProductModel
import com.sahoolatkar.sahoolatkar.models.SliderItemModel
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.ui.SplashActivity
import com.sahoolatkar.sahoolatkar.viewmodels.ProductViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var mainView: View
    private lateinit var mainActivity: MainActivity
    private lateinit var mobilesViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_home, container, false)
        return mainView
    }

    private fun setUpViewModels() {
        mobilesViewModel = ProductViewModel(GlobalVariables.CATEGORY_MOBILES_ID)
        mobilesViewModel.getProducts().observe(mainActivity, Observer<List<ProductApiModel>> {
            val mobiles: MutableList<ProductModel> = ArrayList()

            for (productItem in it) {
                mobiles.add(
                    ProductModel(
                        productItem.name,
                        productItem.description,
                        productItem.images[0].src,
                        0f,
                        0
                    )
                )
            }

            val mobilesAdapter =
                ProductsAdapter(
                    mainActivity,
                    mobiles,
                    GlobalVariables.HOME_FRAGMENT
                )
            rvMobiles.layoutManager = GridLayoutManager(context, 2)
            rvMobiles.adapter = mobilesAdapter
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
        setUpOffersSlider()
        setUpFeaturedProductsSlider()
        setUpRecyclers()
        setUp1stBanner()
        setUpTopSliderIndicator()
        setListeners()
    }

    private fun setListeners() {
        tvMobilesViewAll.setOnClickListener {
            Navigation.findNavController(mainActivity, R.id.navHostFragment)
                .navigate(HomeFragmentDirections.actionHomeToProductsCatalogFragment(GlobalVariables.CATEGORY_MOBILES_ID))
        }

        tvHomeAppliancesViewAll.setOnClickListener {
            Navigation.findNavController(mainActivity, R.id.navHostFragment)
                .navigate(HomeFragmentDirections.actionHomeToProductsCatalogFragment(GlobalVariables.CATEGORY_HOME_APPLIANCES_ID))
        }
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
        setUpMobilesRecycler()
    }

    private fun setUpHomeAppliancesRecycler() {
        val homeAppliances: MutableList<ProductModel> = ArrayList()

        SahoolatKarApiUtils.getProductsByCategory(
            mainActivity,
            GlobalVariables.CATEGORY_HOME_APPLIANCES_ID,
            object :
                IGetAllProductsCallback {
                override fun onGetProducts(products: MutableList<ProductApiModel>) {

                    if (products.isNotEmpty()) {
                        for (productItem in products) {
                            homeAppliances.add(
                                ProductModel(
                                    productItem.name,
                                    productItem.description,
                                    productItem.images[0].src,
                                    0f,
                                    0
                                )
                            )
                        }
                    }

                    val mobilesAdapter =
                        ProductsAdapter(
                            mainActivity,
                            homeAppliances,
                            GlobalVariables.HOME_FRAGMENT
                        )
                    rvMobiles.layoutManager = GridLayoutManager(context, 2)
                    rvMobiles.adapter = mobilesAdapter
                }
            })
    }

    private fun setUpMobilesRecycler() {
//        val mobiles: MutableList<ProductModel> = ArrayList()
//
//        SahoolatKarApiUtils.getProductsByCategory(mainActivity, GlobalVariables.CATEGORY_MOBILES_ID, object :
//            IGetAllProductsCallback {
//            override fun onGetProducts(products: MutableList<ProductApiModel>) {
//
//                if (products.isNotEmpty()) {
//                    for (productItem in products) {
//                        mobiles.add(ProductModel(productItem.name, productItem.description, productItem.images[0].src, 0f, 0))
//                    }
//                }
//
//                val mobilesAdapter =
//                    ProductsAdapter(mainActivity, mobiles, getString(R.string.fragment_home))
//                rvMobiles.layoutManager = GridLayoutManager(context, 2)
//                rvMobiles.adapter = mobilesAdapter
//            }
//        })
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

    private fun setUpOffersSlider() {
        val offers = ArrayList<SliderItemModel>()
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/1-1-1030x472.png"))
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/2-1.png"))
        offers.add(SliderItemModel("https://mygreatdubai.com/sahoolatkar/wp-content/uploads/2020/07/1-1-1030x472.png"))
        val sliderAdapter = OffersSliderAdapter(activity as Context, offers, true)
        offersSlider.adapter = sliderAdapter

        offersSlider.clipToPadding = false;
        offersSlider.setPadding(70, 0, 70, 0)
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

//        var productsList: MutableList<ProductApiModel>? = null
//
//        SahoolatKarApiUtils.getAllProducts(mainActivity, object : IGetAllProductsCallback {
//            override fun onGetAllProducts(products: MutableList<ProductApiModel>) {
//                productsList = products
//            }
//        })

        val sliderAdapter = ProductsSliderAdapter(activity as Context, featuredProducts, true)
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