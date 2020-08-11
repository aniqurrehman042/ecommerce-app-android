package com.sahoolatkar.sahoolatkar.ui

import android.app.Dialog
import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.CategoriesFilterSpinnerAdapter
import com.sahoolatkar.sahoolatkar.adapters.MenuAdapter
import com.sahoolatkar.sahoolatkar.api_models.product.Product
import com.sahoolatkar.sahoolatkar.models.Category
import com.sahoolatkar.sahoolatkar.models.MenuItemModel
import com.sahoolatkar.sahoolatkar.utils.*
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_menu.*
import kotlinx.android.synthetic.main.layout_search_filter.*


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
        dialog = Dialog(this)
        UIUtils.setFullScreen(window)

        init()
    }

    private fun init() {
        showMenu()
        setUpBottomBar()
        setListeners()
        setAnimations()
        setUpMenuRecycler()
        initSearchFilter()
    }

    private fun initSearchFilter() {
        closeSearchFilterPopUp()

        val categories = FixedDataUtils.getCategoryList()
        categories.add(0, Category("Select Categories", "", "", 0))

        spCategories.adapter = CategoriesFilterSpinnerAdapter(
            this,
            R.layout.layout_filter_category_item,
            categories
        )

        rsPriceRange.valueFrom = 0f
        rsPriceRange.valueTo = 300000f
    }

    private fun showMenu() {
        ViewUtils.showView(clMenuContainer)
    }

    private fun setUpMenuRecycler() {
        val menuItems = ArrayList<MenuItemModel>()
        menuItems.add(MenuItemModel("Wish List", ""))
        rvMenuItems.layoutManager = LinearLayoutManager(this)
        rvMenuItems.adapter = MenuAdapter(this, menuItems)
    }

    private fun setAnimations() {
        animEnterFromLeft =
            AnimationUtils.loadAnimation(this, R.anim.enter_from_left).apply { fillAfter = true }
        animFadeInScaleUp =
            AnimationUtils.loadAnimation(this, R.anim.fade_in_scale_up).apply { fillAfter = true }
        animFadeOutScaleDown = AnimationUtils.loadAnimation(this, R.anim.fade_out_scale_down)
            .apply {
                fillAfter = true
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        ViewUtils.hideView(clCart)
                    }

                    override fun onAnimationStart(p0: Animation?) {

                    }
                })
            }
        animFadeOutScaleDownFast =
            AnimationUtils.loadAnimation(this, R.anim.fade_out_scale_down_fast)
                .apply { fillAfter = true }
        animExitToLeft =
            AnimationUtils.loadAnimation(this, R.anim.exit_to_left).apply {
                fillAfter = true
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        ViewUtils.hideView(clMenuContainer)
                    }

                    override fun onAnimationStart(p0: Animation?) {

                    }
                })
            }
        animFadeIn = AlphaAnimation(0f, 1f).apply { fillAfter = true; duration = 500 }
        animFadeOut = AlphaAnimation(1f, 0f).apply { fillAfter = true; duration = 500 }

        startInitialAnimations()
    }

    private fun startInitialAnimations() {
        val animExitToLeftFast =
            AnimationUtils.loadAnimation(this, R.anim.exit_to_left_fast).apply { fillAfter = true }
        val animFadeOutFast = AlphaAnimation(1f, 0f).apply { fillAfter = true; duration = 0 }

        clCart.startAnimation(animFadeOutScaleDownFast)
        ViewUtils.hideView(clCart)
        clMenu.startAnimation(animExitToLeftFast)
        vOverlay.startAnimation(animFadeOutFast)
        ViewUtils.hideView(clMenuContainer)
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

        ivSearchFilter.setOnClickListener {
            openSearchFilterPopUp()
        }

        llCloseSearchFilter.setOnClickListener {
            closeSearchFilterPopUp()
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

        clCart.setOnClickListener {
            if (navHostFragment.childFragmentManager.fragments[0].javaClass.simpleName != "CartFragment")
                Navigation.findNavController(this@MainActivity, R.id.navHostFragment)
                    .navigate(R.id.cartFragment, null, navOptions, null)
        }

        etSearch.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            }

            false
        }

    }

    private fun performSearch() {
        val searchTerm = etSearch.text.toString()
        EditTextUtils.hideKeyboardFrom(this)
    }

    private fun openMenu() {
        clMenu.startAnimation(animEnterFromLeft)
        vOverlay.startAnimation(animFadeIn)
        ViewUtils.showView(clMenuContainer)
        menuOpen = true
    }

    fun closeMenu() {
        clMenu.startAnimation(animExitToLeft)
        vOverlay.startAnimation(animFadeOut)
        menuOpen = false
    }

    private fun updateCartIcon(cartItems: Int) {
        tvCartItems.text = cartItems.toString()
    }

    fun onAddCartItem() {
        val cartItems = tvCartItems.text.toString().toInt()
        updateCartIcon(cartItems + 1)
        if (cartItems == 0) {
            showCart()
        }
    }

    fun onRemoveCartItem() {
        val cartItems = tvCartItems.text.toString().toInt()
        updateCartIcon(cartItems - 1)
        if (cartItems == 1) {
            hideCart()
        } else {
            blinkCart()
        }
    }

    private fun showCart() {
        ViewUtils.showView(clCart)
        clCart.startAnimation(animFadeInScaleUp)
    }

    fun hideCart() {
        clCart.startAnimation(animFadeOutScaleDown)
    }

    fun blinkCart() {
        clCart.startAnimation(animFadeOut)
        clCart.startAnimation(animFadeIn)
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
        ViewUtils.hideView(ivMenu)
        ViewUtils.hideView(ivLogo)
        ViewUtils.hideView(clCart)
        ViewUtils.hideView(ivNotifications)
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
        ViewUtils.showView(ivMenu)
        ViewUtils.showView(ivLogo)
        ViewUtils.showView(clCart)
        ViewUtils.showView(ivNotifications)
        showSearchBar()
    }

    fun removeProductFromWishList(product: Product) {
        if (mainViewModel.wishListProducts.contains(product))
            mainViewModel.wishListProducts.remove(product)
    }

    fun addProductToWishList(product: Product) {
        if (!mainViewModel.wishListProducts.contains(product))
            mainViewModel.wishListProducts.add(product)
    }

    fun playThrowSound() {
        val mpThrow = MediaPlayer.create(this, R.raw.snap)
        if (mpThrow.isPlaying)
            mpThrow.stop()
        mpThrow.start()
    }

    private fun openSearchFilterPopUp() {
        ViewUtils.showView(clSearchFilterPopUp)
    }

    private fun closeSearchFilterPopUp() {
        ViewUtils.hideView(clSearchFilterPopUp)
    }

    fun resetCart() {
        updateCartIcon(0)
        hideCart()
    }

    fun goBackToHomeFragment() {
        Navigation.findNavController(this, R.id.navHostFragment).popBackStack(R.id.home, false)
    }

    override fun onBackPressed() {
        LoadingUtils.enableUserInteraction(this)
        super.onBackPressed()
    }
}
