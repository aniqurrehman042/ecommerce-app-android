package com.sahoolatkar.sahoolatkar.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.NotificationModel


class NotificationAdapter //Constructor For Adapter
    (// List with String type
    private val activity: Activity,
    private val notificationList: MutableList<NotificationModel>
) :
    RecyclerView.Adapter<NotificationAdapter.MyView>() {

    // View Holder class which
    // extends RecyclerView.ViewHolder
    inner class MyView(view: View) : RecyclerView.ViewHolder(view) {
        var notificationTitle: TextView = view.findViewById(R.id.tvNotificationTitle)
        var notificationDescription: TextView = view.findViewById(R.id.tvNotificationDescription)
        var notificationTime: TextView = view.findViewById(R.id.tvNotificationTime)
        var notificationIcon: ImageView = view.findViewById(R.id.ivIcon)
        var clNotification: ConstraintLayout = view.findViewById(R.id.clNotification)
    }

    // Override onCreateViewHolder which deals
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {

        // Inflate item.xml using LayoutInflator
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.layout_notification_item,
                parent,
                false
            )

        // return itemView
        return MyView(itemView)
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    override fun onBindViewHolder(
        holder: MyView,
        position: Int
    ) {

        // Set the text of each item of
        // Recycler view with the list items
        holder.notificationTitle.text = notificationList[position].title
        holder.notificationDescription.text = notificationList[position].description
        holder.notificationTime.text = notificationList[position].time
        holder.notificationIcon.setImageResource(R.drawable.ic_cat_ac)

        if (position % 2 == 0) {
            holder.clNotification.background = activity.getDrawable(R.color.blue)
        } else if (position % 3 == 0) {
            holder.clNotification.background = activity.getDrawable(R.color.red)
        }
    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    override fun getItemCount(): Int {
        return notificationList.size
    }

}