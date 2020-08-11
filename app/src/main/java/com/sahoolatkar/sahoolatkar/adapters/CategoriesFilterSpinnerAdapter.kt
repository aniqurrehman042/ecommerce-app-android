package com.sahoolatkar.sahoolatkar.adapters

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.Category
import com.sahoolatkar.sahoolatkar.utils.ViewUtils

class CategoriesFilterSpinnerAdapter(val activity: Activity, val layoutId: Int, val categories: List<Category>) :
    ArrayAdapter<Category>(activity, layoutId, categories) {

    val selectedCategories: MutableList<String> = ArrayList()

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent, true)!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent, false)!!
    }

    private fun getCustomView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
        dropDown: Boolean
    ): View? {
        var view = convertView ?: LayoutInflater.from(activity).inflate(layoutId, parent, false)

        val cbCategory = view!!.findViewById<CheckBox>(R.id.cbCategory)

        if (position == 0) {
            if (dropDown) {
                val tv = TextView(activity)
                tv.height = 0
                ViewUtils.hideView(tv)
                return tv
            } else {
                cbCategory.buttonDrawable = ColorDrawable(Color.TRANSPARENT)
            }
        }

        if (cbCategory == null) {
            view = LayoutInflater.from(activity).inflate(layoutId, parent, false)
        }

        cbCategory?.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                selectedCategories.add(categories[position].id)
            } else {
                selectedCategories.remove(categories[position].id)
            }
        }

        cbCategory?.text = categories[position].name

        return view
    }


}