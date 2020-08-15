package com.sahoolatkar.sahoolatkar.api_utils

import com.sahoolatkar.sahoolatkar.api_models.NadraResponse
import com.sahoolatkar.sahoolatkar.api_models.customer.Customer
import com.sahoolatkar.sahoolatkar.api_models.order.Order
import com.sahoolatkar.sahoolatkar.api_models.product.Product
import com.sahoolatkar.sahoolatkar.api_models.subscription.Subscription
import com.sahoolatkar.sahoolatkar.apis_clients.SahoolatkarApiClient
import com.sahoolatkar.sahoolatkar.apis_clients.SahoolatkarGeneralApiClient
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.http_services.SahoolatkarGeneralService
import com.sahoolatkar.sahoolatkar.http_services.SahoolatkarWoocommerceApiService
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class SahoolatKarApiUtils {

    companion object {

        private val sahoolatkarApiClient: SahoolatkarApiClient =
            SahoolatkarWoocommerceApiService.createService(
                SahoolatkarApiClient::class.java,
                GlobalVariables.WOOCOMMERCE_CONSUMER_KEY,
                GlobalVariables.WOOCOMMERCE_CONSUMER_SECRET
            )

        private val sahoolatkarGeneralApiClient: SahoolatkarGeneralApiClient =
            SahoolatkarGeneralService.createService(SahoolatkarGeneralApiClient::class.java)

        suspend fun getProducts(
            categoryId: String? = null,
            featured: Boolean? = null,
            pageNo: Int,
            pageSize: Int = 20
        ): Response<List<Product>> {
            return try {
                sahoolatkarApiClient.getProducts(categoryId, featured, pageNo, pageSize = pageSize)
            } catch (e: Exception) {
                getProducts(categoryId, featured, pageNo, pageSize = pageSize)
            }
        }

        suspend fun postOrder(order: Order) : Response<Order> {
            return try {
                sahoolatkarApiClient.postOrder(order)
            } catch (e: Exception) {
                postOrder(order)
            }
        }

        suspend fun getOrders(cusId: Int) : Response<List<Order>> {
            return try {
                sahoolatkarApiClient.getOrders(cusId)
            } catch (e: Exception) {
                getOrders(cusId)
            }
        }

        suspend fun createCustomer(customer: Customer) : Response<Customer> {
            return try {
                sahoolatkarApiClient.createCustomer(customer)
            } catch (e: Exception) {
                createCustomer(customer)
            }
        }

        suspend fun getAllCustomers() : Response<List<Customer>> {
            return try {
                sahoolatkarApiClient.getAllCustomers()
            } catch (e: Exception) {
                getAllCustomers()
            }
        }

        suspend fun createCustomerPin(customer: Customer) : Response<Boolean> {
            return try{
                sahoolatkarGeneralApiClient.createCustomerPin(customer)
            } catch (e: Exception) {
                createCustomerPin(customer)
            }
        }

        suspend fun getNadraDetails(cnic: String) : Response<NadraResponse> {
            return try{
                sahoolatkarGeneralApiClient.getNadraDetails(cnic)
            } catch (e: Exception) {
                getNadraDetails(cnic)
            }
        }

        suspend fun getCustomer(cnic: String) : Response<Customer> {
            return try{
                sahoolatkarGeneralApiClient.getCustomer(cnic)
            } catch (e: Exception) {
                getCustomer(cnic)
            }
        }

        suspend fun getWooCommerceCustomer(id: Int) : Response<Customer> {
            return try{
                sahoolatkarApiClient.getCustomer(id)
            } catch (e: Exception) {
                getWooCommerceCustomer(id)
            }
        }

        suspend fun createSubscription(subscription: Subscription) : Response<Subscription> {
            return try{
                sahoolatkarApiClient.createSubscription(subscription)
            } catch (e: Exception) {
                createSubscription(subscription)
            }
        }

    }

}