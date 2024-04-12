package what.a.pity.phone.call.paperthree.fast.light

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LightBroadcast : BroadcastReceiver() {
    private val SYSTEM_DIALOG_REASON_KEY = "reason"
    private val SYSTEM_DIALOG_REASON_HOME_KEY = "homekey"
    private val SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps"

    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action
        if (action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
            val reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY) ?: return

            // Home键
            if (reason == SYSTEM_DIALOG_REASON_HOME_KEY) {
//                LightWindow.InstanceHelper.lockWindowHelper.closeThePasswordBox()
            }

            // 最近任务列表键
            if (reason == SYSTEM_DIALOG_REASON_RECENT_APPS) {
//                LightWindow.InstanceHelper.lockWindowHelper.closeThePasswordBox()
            }
        }
    }
}