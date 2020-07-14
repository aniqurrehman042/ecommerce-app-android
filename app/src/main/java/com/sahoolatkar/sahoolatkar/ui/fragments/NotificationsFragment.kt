package com.sahoolatkar.sahoolatkar.ui.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.NotificationAdapter
import com.sahoolatkar.sahoolatkar.models.NotificationModel
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var mainActivity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = requireActivity()

        init()
    }

    private fun init() {
        setUpNotificationsRecycler()
    }

    private fun setUpNotificationsRecycler() {
        val notifications = ArrayList<NotificationModel>()
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        notifications.add(NotificationModel("Sahoolat Kar Installments", "With no credit required, you can apply using our intuitive online procss in-store or at home while shopping online.", "02:30 pm", "Installment"))
        rvNotifications.layoutManager = LinearLayoutManager(requireContext())
        rvNotifications.adapter = NotificationAdapter(notifications)

    }
}