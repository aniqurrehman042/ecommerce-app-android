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
import com.sahoolatkar.sahoolatkar.api_models.subscription.Subscription
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.LoadingUtils
import com.sahoolatkar.sahoolatkar.utils.SharedPrefsUtils
import com.sahoolatkar.sahoolatkar.utils.ToastUtils
import com.sahoolatkar.sahoolatkar.viewmodels.MainViewModel
import kotlinx.android.synthetic.*
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
                    val lineItem = LineItems(
                        cartProduct.product.id,
                        cartProduct.quantity
                    )

                    if (cartProduct.product.variations.isNotEmpty()) {
                        lineItem.variation_id = when (cartProduct.installments) {
                            GlobalVariables.INSTALLMENTS_3 -> cartProduct.product.variations[1]
                            GlobalVariables.INSTALLMENTS_6 -> cartProduct.product.variations[2]
                            GlobalVariables.INSTALLMENTS_9 -> cartProduct.product.variations[3]
                            GlobalVariables.INSTALLMENTS_12 -> cartProduct.product.variations[4]
                            else -> cartProduct.product.variations[0]
                        }
                    }

                    lineItems.add(lineItem)
                }
                val order =
                    Order(
                        0,
                        customer.id,
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
                    val orderCreationResponse: Response<Order> = SahoolatKarApiUtils.postOrder(order)
                    if (orderCreationResponse.isSuccessful) {

                        val subscriptionLineItems = ArrayList<LineItems>()

                        for (lineItem in order.line_items) {
                            if (lineItem.variation_id != 0) {
                                subscriptionLineItems.add(lineItem)
                            }
                        }

                        if (subscriptionLineItems.size > 0) {

                            val subscription = Subscription(
                                0,
                                orderCreationResponse.body()!!.id,
                                "",
                                customer.id,
                                "",
                                customer.billing,
                                customer.shipping,
                                "cod",
                                "Cash on delivery",
                                subscriptionLineItems,
                                "",
                                "",
                                "",
                                "",
                                ""
                            )

                            val createSubscriptionResponse =
                                SahoolatKarApiUtils.createSubscription(subscription)

                            if (createSubscriptionResponse.isSuccessful) {
                                onOrderSuccess()
                            } else {
                                ToastUtils.showLongToast(mainActivity, "Failed to place order. Error: " + orderCreationResponse.message().toString())
                            }

                        } else {
                            onOrderSuccess()
                        }
                    } else {
                        ToastUtils.showLongToast(mainActivity, "Failed to place order. Error: " + orderCreationResponse.message().toString())
                    }
                    LoadingUtils.hideLoader(mainActivity, llLoader)
                }
            }
        }
    }

    private fun onOrderSuccess() {
        ToastUtils.showLongToast(mainActivity, "Order Placed Successfully")
        mainViewModel.cartProducts.clear()
        mainActivity.resetCart()
        mainActivity.goBackToHomeFragment()
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

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }

}