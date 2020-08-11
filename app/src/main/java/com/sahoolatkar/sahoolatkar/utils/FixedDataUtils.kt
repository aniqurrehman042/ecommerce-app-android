package com.sahoolatkar.sahoolatkar.utils

import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.Category

class FixedDataUtils {

    companion object {

        fun getCategoryList(): MutableList<Category> {
            val categories: MutableList<Category> = ArrayList()
            categories.add(
                Category(
                    "Plastic Furniture",
                    "",
                    "https://learningtoys.pk/wp-content/uploads/2019/08/1-4-300x300.jpg",
                    R.drawable.ic_ic1_cat_furniture
                )
            )
            categories.add(
                Category(
                    "Mobile Phones",
                    "21",
                    "https://youngwomenshealth.org/wp-content/uploads/2014/02/fast-food.jpg",
                    R.drawable.ic_ic2_cat_mobile
                )
            )
            categories.add(
                Category(
                    "Deep Freezers",
                    "218",
                    "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                    R.drawable.ic_ic3_cat_deepfreezer
                )
            )
            categories.add(
                Category(
                    "Home Appliances",
                    "242",
                    "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                    R.drawable.ic_ic4_cat_home_appliances
                )
            )
            categories.add(
                Category(
                    "Air Conditioners",
                    "233",
                    "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                    R.drawable.ic_ic5_cat_ac
                )
            )
            categories.add(
                Category(
                    "Room Coolers",
                    "238",
                    "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                    R.drawable.ic_ic6_cat_roomcooler
                )
            )
            categories.add(
                Category(
                    "Water Dispenser",
                    "62",
                    "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                    R.drawable.ic_ic7_cat_waterdespensor
                )
            )
            categories.add(
                Category(
                    "Motorcycle",
                    "41",
                    "https://newmobiles.com.pk/wp-content/uploads/2020/06/infinix-note-7-pakistan-300x300.jpg",
                    R.drawable.ic_ic8_cat_motorcycle
                )
            )
            return categories
        }

    }

}