package com.sahoolatkar.sahoolatkar.activity_result_contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.sahoolatkar.sahoolatkar.models.CartProduct
import com.sahoolatkar.sahoolatkar.ui.BillingInfoActivity
import java.io.Serializable

class OrderPlacementResultContract : ActivityResultContract<MutableList<CartProduct>, Boolean>() {
    override fun createIntent(context: Context, input: MutableList<CartProduct>?): Intent {
        return Intent(context, BillingInfoActivity::class.java).apply {
            putExtra("cartProducts", input as Serializable)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return when {
            resultCode != Activity.RESULT_OK -> false
            else -> intent?.getBooleanExtra("orderPlacementSuccessful", false)!!
        }
    }
}