package com.oguzhanturkmen.mytodoreal.ui.notification

import android.app.Application
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.oguzhanturkmen.mytodoreal.R
import com.oguzhanturkmen.mytodoreal.ui.notification.work.NotifyWork
import com.oguzhanturkmen.mytodoreal.ui.notification.work.NotifyWork.Companion.NOTIFICATION_ID
import com.oguzhanturkmen.mytodoreal.ui.notification.work.NotifyWork.Companion.NOTIFICATION_WORK
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(val app:Application) :ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _notificationMessage = MutableLiveData<String>()
    val notificationMessage: LiveData<String>
        get() = _notificationMessage


    fun scheduleNotification(year: Int, month: Int, day: Int, hour: Int, minute: Int) {
        val customCalendar = Calendar.getInstance()
        customCalendar.set(year, month, day, hour, minute, 0)

        val customTime = customCalendar.timeInMillis
        val currentTime = System.currentTimeMillis()
        if (customTime > currentTime) {
            val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
            val delay = customTime - currentTime
            scheduleNotificationWork(delay, data)

            val patternNotificationSchedule = app.getString(R.string.notification_schedule_pattern)
            _notificationMessage.value = app.getString(R.string.notification_schedule_title) +
                    SimpleDateFormat(patternNotificationSchedule, Locale.getDefault()).format(customCalendar.time)
        } else {
            _errorMessage.value = app.getString(R.string.notification_schedule_title)
        }
    }



    private fun scheduleNotificationWork(delay: Long, data: Data) {
        val notificationWork = OneTimeWorkRequest.Builder(NotifyWork::class.java)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS).setInputData(data).build()

        val instanceWorkManager = WorkManager.getInstance(getApplication(app))
        instanceWorkManager.beginUniqueWork(NOTIFICATION_WORK,
            ExistingWorkPolicy.REPLACE, notificationWork).enqueue()
    }
}