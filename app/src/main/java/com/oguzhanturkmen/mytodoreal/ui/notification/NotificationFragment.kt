package com.oguzhanturkmen.mytodoreal.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.oguzhanturkmen.mytodoreal.R
import com.oguzhanturkmen.mytodoreal.databinding.FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var viewModel: NotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notificationFragment = this

    }

    fun doneFabClicked() {
        val year = binding.dateP.year
        val month = binding.dateP.month
        val day = binding.dateP.dayOfMonth
        val hour = binding.timeP.hour
        val minute = binding.timeP.minute

        viewModel.scheduleNotification(year, month, day, hour, minute)

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage != null) {
                Snackbar.make(binding.coordinatorL, errorMessage, Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.notificationMessage.observe(viewLifecycleOwner) { notificationMessage ->
            if (notificationMessage != null) {
                Snackbar.make(binding.coordinatorL, notificationMessage, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }
}