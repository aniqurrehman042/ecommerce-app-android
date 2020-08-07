package com.sahoolatkar.sahoolatkar.globals

class GlobalVariables {
    companion object {

        // Shared Preferences File Keys
        const val CUSTOMER_PREFS_KEY: String = "customer"

        // Shared Preferences Variable Keys
        const val CUSTOMER_ID: String = "customerId"
        const val CUSTOMER_FIRST_NAME: String = "customerFirstName"
        const val CUSTOMER_LAST_NAME: String = "customerLastName"
        const val CUSTOMER_EMAIL: String = "customerEmail"
        const val CUSTOMER_USERNAME: String = "customerUsername"
        const val CUSTOMER_CNIC: String = "customerCnic"
        const val CUSTOMER_PIN: String = "customerPin"
        const val CUSTOMER_ADDRESS: String = "customerAddress"
        const val CUSTOMER_CITY: String = "customerCity"
        const val CUSTOMER_STATE: String = "customerState"
        const val CUSTOMER_PHONE: String = "customerPhone"

        const val FIRST_RUN: String = "firstRun"

        // Request Codes
        const val BILLING_INFO_REQUEST_CODE: Int = 1

        // Fragment Names
        const val PRODUCT_CATALOG_FRAGMENT: String = "Products Catalog Fragment"
        const val APPLY_LOAD_FRAGMENT: String = "Apply Loan Fragment"
        const val GETTING_STARTED_PAY_LATER_FRAGMENT: String = "Getting Started Pay Later Fragment"
        const val GETTING_STARTED_PURCHASING_FRAGMENT: String = "Getting Started Purchasing Fragment"
        const val GETTING_STARTED_REGISTRATION_FRAGMENT: String = "Getting Started Registration Fragment"
        const val GETTING_STARTED_VERIFICATION_FRAGMENT: String = "Getting Started Verification Fragment"
        const val HOME_FRAGMENT: String = "Home Fragment"
        const val INSTALLMENTS_FRAGMENT: String = "Installments Fragment"
        const val PRODUCT_DETAILS_FRAGMENT: String = "Product Details Fragment"
        const val PRODUCT_OVERVIEW_FRAGMENT: String = "Product Overview Fragment"
        const val PRODUCT_SPECIFICATIONS_FRAGMENT: String = "Products Specifications Fragment"
        const val PROFILE_FRAGMENT: String = "Profile Fragment"
        const val STORE_LOCATOR_FRAGMENT: String = "Store Locator Fragment"
        const val WISH_LIST_FRAGMENT: String = "Wish List Fragment"

        // Woocommerce Api Keys
        const val WOOCOMMERCE_CONSUMER_KEY = "ck_b97e6cc6aed641a5710cb3c593043a620549bf04"
        const val WOOCOMMERCE_CONSUMER_SECRET = "cs_f1497609313036ace6c276a12a50683245555069"

        // Woocommerce API global settings
        const val PRODUCTS_PAGE_SIZE: Int = 10

        // Woocommerce API category IDs
        const val CATEGORY_MOBILES_ID = "21"
        const val CATEGORY_HOME_APPLIANCES_ID = "242"
        const val CATEGORY_OFFERS: String = "243"

        // Installments
        const val INSTALLMENTS_1 = 0
        const val INSTALLMENTS_3 = 1
        const val INSTALLMENTS_6 = 2
        const val INSTALLMENTS_9 = 3
        const val INSTALLMENTS_12 = 4

    }
}