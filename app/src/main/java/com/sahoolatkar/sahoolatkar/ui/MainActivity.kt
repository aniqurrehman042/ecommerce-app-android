package com.sahoolatkar.sahoolatkar.ui

import android.app.Dialog
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.DealFilterAdapter
import com.sahoolatkar.sahoolatkar.adapters.MenuAdapter
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.models.CategoryModel
import com.sahoolatkar.sahoolatkar.models.MenuItemModel
import com.sahoolatkar.sahoolatkar.utils.UIUtils
import com.sahoolatkar.sahoolatkar.utils.ViewUtils
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_menu.*
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {
    private lateinit var animEnterFromLeft: Animation
    private lateinit var animExitToLeft: Animation
    private lateinit var animFadeInScaleUp: Animation
    private lateinit var animFadeOutScaleDown: Animation
    private lateinit var animFadeOutScaleDownFast: Animation
    private lateinit var animFadeIn: Animation
    private lateinit var animFadeOut: Animation
    private var menuOpen = false
    var dialog: Dialog? = null
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     dialog= Dialog(this)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        showMenu()
        setUpBottomBar()
        setListeners()
        setAnimations()
        setUpMenuRecycler()
    }

    private fun showMenu() {
        ViewUtils.showView(clMenuContainer)
    }

    private fun setUpMenuRecycler() {
        val menuItems = ArrayList<MenuItemModel>()
        menuItems.add(MenuItemModel("Wish List", ""))
        menuItems.add(MenuItemModel("Language", ""))
        menuItems.add(MenuItemModel("Language", ""))
        menuItems.add(MenuItemModel("Language", ""))
        menuItems.add(MenuItemModel("Language", ""))
        menuItems.add(MenuItemModel("Language", ""))
        rvMenuItems.layoutManager = LinearLayoutManager(this)
        rvMenuItems.adapter = MenuAdapter(this, menuItems)
    }

    private fun setAnimations() {
        animEnterFromLeft =
            AnimationUtils.loadAnimation(this, R.anim.enter_from_left).apply { fillAfter = true }
        animFadeInScaleUp =
            AnimationUtils.loadAnimation(this, R.anim.fade_in_scale_up).apply { fillAfter = true }
        animFadeOutScaleDown = AnimationUtils.loadAnimation(this, R.anim.fade_out_scale_down)
            .apply { fillAfter = true }
        animFadeOutScaleDownFast =
            AnimationUtils.loadAnimation(this, R.anim.fade_out_scale_down_fast)
                .apply { fillAfter = true }
        animExitToLeft =
            AnimationUtils.loadAnimation(this, R.anim.exit_to_left).apply {
                fillAfter = true; setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    clMenuContainer.visibility = View.GONE
                }

                override fun onAnimationStart(p0: Animation?) {

                }
            })
            }
        animFadeIn = AlphaAnimation(0f, 1f).apply { fillAfter = true; duration = 500 }
        animFadeOut = AlphaAnimation(1f, 0f).apply { fillAfter = true; duration = 500 }
        val animExitToLeftFast =
            AnimationUtils.loadAnimation(this, R.anim.exit_to_left_fast).apply { fillAfter = true }
        val animFadeOutFast = AlphaAnimation(1f, 0f).apply { fillAfter = true; duration = 0 }

        llCart.startAnimation(animFadeOutScaleDownFast)
        clMenu.startAnimation(animExitToLeftFast)
        vOverlay.startAnimation(animFadeOutFast)
        clMenuContainer.visibility = View.GONE
    }

    private fun setListeners() {

        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.enter_from_left)
            .setExitAnim(R.anim.exit_to_right)
            .build()

        ivNotifications.setOnClickListener {
            if (navHostFragment.childFragmentManager.fragments[0].javaClass.simpleName != "NotificationsFragment") {
                Navigation.findNavController(this@MainActivity, R.id.navHostFragment)
                    .navigate(R.id.notificationsFragment, null, navOptions, null)
            }

        }
        ivSearchFilter.setOnClickListener{
            showPopUp()
        }
        ivMenu.setOnClickListener {
            if (menuOpen) {
                closeMenu()
            } else {
                openMenu()
            }
        }

        vPlaceholder.setOnClickListener {
            closeMenu()
        }

        llCart.setOnClickListener {
            if (navHostFragment.childFragmentManager.fragments[0].javaClass.simpleName != "CartFragment")
                Navigation.findNavController(this@MainActivity, R.id.navHostFragment)
                    .navigate(R.id.cartFragment, null, navOptions, null)
        }

    }

    fun openMenu() {
        clMenu.startAnimation(animEnterFromLeft)
        vOverlay.startAnimation(animFadeIn)
        clMenuContainer.visibility = View.VISIBLE
        menuOpen = true
    }

    fun closeMenu() {
        clMenu.startAnimation(animExitToLeft)
        vOverlay.startAnimation(animFadeOut)
        menuOpen = false
    }

    fun updateCartIcon(cartItems: Int) {
        tvCartItems.text = cartItems.toString()
    }

    fun addCartItem() {
        val cartItems = tvCartItems.text.toString().toInt()
        updateCartIcon(cartItems + 1)
        if (cartItems == 0) {
            showCart()
        }
    }

    fun removeCartItem() {
        val cartItems = tvCartItems.text.toString().toInt()
        updateCartIcon(cartItems - 1)
        if (cartItems == 1) {
            hideCart()
        }
    }

    private fun showCart() {
        llCart.startAnimation(animFadeInScaleUp)
    }

    private fun hideCart() {
        llCart.startAnimation(animFadeOutScaleDown)
    }

    fun blinkCart() {
        llCart.startAnimation(animFadeOut)
        llCart.startAnimation(animFadeIn)
    }

    private fun setUpBottomBar() {
        bnv_bottom_bar.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.navHostFragment
            )
        )
    }

    fun hideTopBar() {
        ViewUtils.hideView(vTopOval)
        ViewUtils.hideView(ll_topbar)
        hideSearchBar()
    }

    fun hideSearchBar() {
        ViewUtils.hideView(vEtVerticalLine)
        ViewUtils.hideView(ivSearchFilter)
        ViewUtils.hideView(etSearch)
    }

    fun showSearchBar() {
        ViewUtils.showView(vEtVerticalLine)
        ViewUtils.showView(ivSearchFilter)
        ViewUtils.showView(etSearch)
    }

    fun showTopBar() {
        ViewUtils.showView(vTopOval)
        ViewUtils.showView(ll_topbar)
        showSearchBar()
    }

    fun removeProductFromWishList(product: ProductApiModel) {
        if (mainViewModel.wishListProducts.contains(product))
            mainViewModel.wishListProducts.remove(product)
    }

    fun addProductToWishList(product: ProductApiModel) {
        if (!mainViewModel.wishListProducts.contains(product))
            mainViewModel.wishListProducts.add(product)
    }

    fun playThrowSound() {
        val mpThrow = MediaPlayer.create(this, R.raw.snap)
        if (mpThrow.isPlaying)
            mpThrow.stop()
        mpThrow.start()
    }
    fun showPopUp()
    {

        val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a custom view using layout inflater
        val view: View =
            LayoutInflater.from(this).inflate(R.layout.layout_deals_filter, null, false)
        // Initialize a new instance of popup window
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT,true // Window height
        )

        val recycler = view.findViewById<RecyclerView>(R.id.rvCategories);
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listCategory = givelist()
        rvMenuItems.adapter = DealFilterAdapter( listCategory)
        TransitionManager.beginDelayedTransition(view as ViewGroup?)
        popupWindow.showAtLocation(
            view, // Location to display popup window
            Gravity.CENTER, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )
    }
    fun givelist() :MutableList<CategoryModel>
    {
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
        return categories
    }
}


