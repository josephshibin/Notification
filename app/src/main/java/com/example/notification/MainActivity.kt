package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "1"
    private lateinit var mainBinding: ActivityMainBinding
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        mainBinding.button.setOnClickListener {
            counter++
            mainBinding.button.text = counter.toString()
            if (counter % 5 == 0) {
                notification()
            }

        }
    }

    private fun notification() {
        val bulider = NotificationCompat.Builder(this@MainActivity, CHANNEL_ID)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, "1", NotificationManager.IMPORTANCE_DEFAULT)
            val manager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            bulider.setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Title")
                .setContentText("Hi, How ARE YOU ")

        } else {
            bulider.setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Title")
                .setContentText("Hi, How ARE YOU ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        }
        val notificationManagerCompat=NotificationManagerCompat.from(this@MainActivity)
        notificationManagerCompat.notify(1,bulider.build())

    }
}
