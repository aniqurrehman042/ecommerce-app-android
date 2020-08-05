package com.sahoolatkar.sahoolatkar.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.activity_result_contracts.OrderPlacementResultContract
import com.sahoolatkar.sahoolatkar.api_models.shared.LineItems
import com.sahoolatkar.sahoolatkar.api_models.order.Order
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.LoadingUtils
import com.sahoolatkar.sahoolatkar.utils.SharedPrefsUtils
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_payment_options.*
import kotlinx.android.synthetic.main.layout_loader.*
import kotlinx.coroutines.launch
import retrofit2.Response

class PaymentOptionsFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        btCashOnDelievery.setOnClickListener {
            AlertDialog.Builder(mainActivity)
                .setMessage("Confirm place order?")
                .setPositiveButton("Confirm") { dialogInterface, i ->
                    placeOrder()
                    dialogInterface.dismiss()
                }
                .setNegativeButton("Close") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                .show()
        }
    }

    private fun placeOrder() {
        if (!mainViewModel.cartProducts.isNullOrEmpty()) {
            val customer = SharedPrefsUtils.getCustomer(mainActivity)
            if (customer != null) {
                val lineItems = ArrayList<LineItems>()

                for (cartProduct in mainViewModel.cartProducts) {
                    lineItems.add(
                        LineItems(
                            cartProduct.product.id,
                            cartProduct.quantity
                        )
                    )
                }
                val order =
                    Order(
                        customer.id.toInt(),
                        customer.billing,
                        customer.shipping,
                        "cod",
                        "Cash on delivery",
                        false,
                        lineItems,
                        ArrayList()
                    )

                LoadingUtils.showLoader(mainActivity, llLoader, "Placing your order...")

                lifecycleScope.launch {
                    val response: Response<Order> = SahoolatKarApiUtils.postOrder(order)
                    LoadingUtils.hideLoader(mainActivity, llLoader)
                    if (response.isSuccessful) {
                        Toast.makeText(mainActivity, "Order Placed Successfully", Toast.LENGTH_LONG)
                            .show()
                        mainViewModel.cartProducts.clear()
                        mainActivity.resetCart()
                        mainActivity.goBackToHomeFragment()
                    } else {
                        Toast.makeText(
                            mainActivity,
                            "Failed to place order. Error: " + response.message().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun startBillingInfoActivity() {
        registerForActivityResult(OrderPlacementResultContract()) { orderPlacementSuccessful ->
            if (orderPlacementSuccessful) {
                mainViewModel.cartProducts.clear()
                mainActivity.resetCart()
                mainActivity.goBackToHomeFragment()
            }
        }.launch(mainViewModel.cartProducts)
    }

}