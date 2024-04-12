package what.a.pity.phone.call.paperthree.fast.light

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.AppUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import what.a.pity.phone.call.paperthree.R
import java.util.Calendar

class LightService : Service() {
    private var mLockJob: Job? = null
    override fun onCreate() {
        super.onCreate()
        mLockJob = GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                LightWindow.getInstance().initWindow(applicationContext)
            }
        }
        val messageNotificatioManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    "44858",
                    AppUtils.getAppName(),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            channel.enableLights(true)
            channel.lightColor = Color.GREEN
            channel.setShowBadge(false)
            messageNotificatioManager.createNotificationChannel(channel)
        }
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
        builder.setContentText("${AppUtils.getAppName()} is working")
        builder.setWhen(System.currentTimeMillis())
        builder.setChannelId("44858")
        val notification: Notification = builder.build()

        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    fun getForegroundPackageName(context: Context): String? {
        //Get the app record in the last month
        val calendar: Calendar = Calendar.getInstance()
        val end: Long = calendar.timeInMillis
        calendar.add(Calendar.MONTH, -1)
        val start: Long = calendar.timeInMillis
        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val usageEvents = usageStatsManager.queryEvents(start, end)
        val event = UsageEvents.Event()
        var packageName: String? = null
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event)
            if (event.eventType == UsageEvents.Event.ACTIVITY_RESUMED) {
                packageName = event.packageName
            }
        }
        return packageName
    }
}