package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.content.Intent
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.b.ac.MainActivity
import what.a.pity.phone.call.paperthree.b.ac.PaperThreeActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object BIBIUBADDDDUtils {

    var snlveijnaielv = 30
    var clickLimitBKUBOUIBI = 5
    val startOpenBOIBOIUBU = EveryADLocObjectBUB(ADType.FULL_One)
    val interHaHaHaOPNNOPIN = EveryADLocObjectBUB(ADType.INNNNNNNN_PAPER)
    val mainNativeBOUVIY = EveryADLocObjectBUB(ADType.MMMMMMMAin)

    fun initializeAdConfig(adConfigJson: String? = null) {
        var json = adConfigJson
        if (json.isNullOrBlank()) json = PaperThreeConstant.nfskjnkkk
        val advertiseEntity = try {
            GsonUtils.fromJson(json, AdvertiseEntity::class.java)
        } catch (e: Exception) {
            null
        }
        LogUtils.e("advertiseEntity data :", advertiseEntity)
        startOpenBOIBOIUBU.initializeSource(advertiseEntity?.start)
        interHaHaHaOPNNOPIN.initializeSource(advertiseEntity?.inter)
        mainNativeBOUVIY.initializeSource(advertiseEntity?.main)
        snlveijnaielv = advertiseEntity?.showMax ?: 0
        clickLimitBKUBOUIBI = advertiseEntity?.clickMax ?: 0
    }

    fun countAD(s: Boolean = false, c: Boolean=false) {
        val showaaaaa = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) + "show"
        val showcccccccc =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) + "click"
        val dayShow: Int = SPUtils.getInstance().getInt(showaaaaa, 0)
        val dayClick: Int = SPUtils.getInstance().getInt(showcccccccc, 0)
        if (s && dayShow < snlveijnaielv) {
            SPUtils.getInstance().put(showaaaaa, dayShow + 1)
        }
        if (c && dayClick < clickLimitBKUBOUIBI) {
            SPUtils.getInstance().put(showcccccccc, dayClick + 1)
        }
    }


    private fun show(): Boolean {
        val showaaaaa = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) + "show"
        return SPUtils.getInstance().getInt(
            showaaaaa, 0
        ) < snlveijnaielv
    }

    private fun click(): Boolean {
        val showcccccccc =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) + "click"
        return SPUtils.getInstance().getInt(
            showcccccccc, 0
        ) < clickLimitBKUBOUIBI
    }

    fun canShowAD(): Boolean {
        if (!show() || !click()) {
            LogUtils.e("AD LIMIT")
        }
        return show() && click()
    }

    fun showOpenAdIfCan(activity: PaperThreeActivity) {
        if (startOpenBOIBOIUBU.haveCache && activity.isActivityResumed()) {
            activity.job?.cancel()
            startOpenBOIBOIUBU.showFullScreenAdBIUYBUI(activity) {
                activity.startActivity(Intent(activity, MainActivity::class.java))
                activity.finish()
            }
        }
    }
}
