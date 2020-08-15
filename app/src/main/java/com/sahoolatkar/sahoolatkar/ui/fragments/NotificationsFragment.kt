package com.sahoolatkar.sahoolatkar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.adapters.NotificationAdapter
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.DBUtils
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = requireActivity() as MainActivity

        init()
    }

    private fun init() {
        setUpNotificationsRecycler()
    }

    private fun setUpNotificationsRecycler() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val notifications = DBUtils.getDBInstance().notificationDao().getAll()
                withContext(Dispatchers.Main) {
                    rvNotifications.layoutManager = LinearLayoutManager(requireContext())
                    rvNotifications.adapter = NotificationAdapter(mainActivity, notifications)
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        mainActivity.hideSearchBar()
    }

    override fun onPause() {
        super.onPause()
        mainActivity.showSearchBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }
}