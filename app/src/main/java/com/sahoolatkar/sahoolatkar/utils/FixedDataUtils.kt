package com.sahoolatkar.sahoolatkar.utils

import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.models.Category

class FixedDataUtils {

    companion object {

        fun getCategoryList(): MutableList<Category> {
            val categories: MutableList<Category> = ArrayList()

            categories.add(
                Category(
                    "Inverter AC",
                    GlobalVariables.CATEGORY_INVERTER_AC_ID,
                    "",
                    R.drawable.ic_ic5_cat_ac
                )
            )
            categories.add(
                Category(
                    "Mobile Phones",
                    GlobalVariables.CATEGORY_MOBILES_ID,
                    "",
                    R.drawable.ic_ic2_cat_mobile
                )
            )
            categories.add(
                Category(
                    "Air Coolers",
                    GlobalVariables.CATEGORY_AIR_COOLERS_ID,
                    "",
                    R.drawable.ic_ic6_cat_roomcooler
                )
            )
            categories.add(
                Category(
                    "Furniture",
                    GlobalVariables.CATEGORY_FURNITURE_ID,
                    "",
                    R.drawable.ic_ic1_cat_furniture
                )
            )
            categories.add(
                Category(
                    "Deep Freezers",
                    GlobalVariables.CATEGORY_DEEP_FREEZERS_ID,
                    "",
                    R.drawable.ic_ic3_cat_deepfreezer
                )
            )
            categories.add(
                Category(
                    "Electric Geysers",
                    GlobalVariables.CATEGORY_ELECTRIC_GEYSERS_ID,
                    "",
                    R.drawable.ic_ic4_cat_home_appliances
                )
            )
            categories.add(
                Category(
                    "Microwaves",
                    GlobalVariables.CATEGORY_MICROWAVES_ID,
                    "",
                    R.drawable.ic_ic4_cat_home_appliances
                )
            )
            categories.add(
                Category(
                    "Motor Bikes",
                    GlobalVariables.CATEGORY_MOTOR_BIKES_ID,
                    "",
                    R.drawable.ic_ic8_cat_motorcycle
                )
            )
            categories.add(
                Category(
                    "Water Dispensers",
                    GlobalVariables.CATEGORY_WATER_DISPENSERS_ID,
                    "",
                    R.drawable.ic_ic7_cat_waterdespensor
                )
            )
            categories.add(
                Category(
                    "Basic LED",
                    GlobalVariables.CATEGORY_BASIC_LED_ID,
                    "",
                    R.drawable.ic_ic11_cat_gaming
                )
            )
            return categories
        }

    }

}