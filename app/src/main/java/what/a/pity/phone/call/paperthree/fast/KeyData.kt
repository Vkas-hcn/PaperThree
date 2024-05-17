package what.a.pity.phone.call.paperthree.fast

import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import what.a.pity.phone.call.paperthree.BuildConfig
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.Yyq

object KeyData {
    const val ad_key = "fa_c_crev"
    const val ad_blocking_key = "fa_c_wert"
    const val ref_online = "fa_c_pot"

    const val phone_ref = "phone_ref"
    const val phone_black = "phone_black"
    const val phone_uuid = "phone_uuid"
    const val phone_gidData = "phone_gidData"

    const val local_preback = "local_preback"
    const val local_preuse = "local_preuse"
    const val local_preenter = "local_preenter"
    const val local_rescontinue = "local_rescontinue"

    const val openFirst = "openFirst"


    const val ad_user_state = "ump_state_dialog"
    val tba_wall_url = if (BuildConfig.DEBUG) {
        "https://test-loess.khdwallpaper.com/aquinas/info"
    } else {
        "https://loess.khdwallpaper.com/six/burst/hysteric"
    }
    const val haveWallInstall = "haveWallInstall"
    const val gidDataWall = "gidDataWall"
    const val isOpenLightPermission = "isOpenLightPermission"


    var lightWallData = 0
        set(value) {
            SPUtils.getInstance().put("lightWallData", value)
            field = value
        }
        get() = SPUtils.getInstance().getInt("lightWallData", 0)


    var isImagePos = 0
        set(value) {
            SPUtils.getInstance().put("isImagePos", value)
            field = value
        }
        get() = SPUtils.getInstance().getInt("isImagePos", 0)
    var lightSpeed = 40L
        set(value) {
            SPUtils.getInstance().put("lightSpeed", value)
            field = value
        }
        get() = SPUtils.getInstance().getLong("lightSpeed", 40L)


    var lightBorder = 50f
        set(value) {
            SPUtils.getInstance().put("lightBorder", value)
            field = value
        }
        get() = SPUtils.getInstance().getFloat("lightBorder", 50f)


    var isImagePosApp = isImagePos
        set(value) {
            SPUtils.getInstance().put("isImagePosApp", value)
            field = value
        }
        get() = SPUtils.getInstance().getInt("isImagePosApp", isImagePos)

    var lightSpeedApp = lightSpeed
        set(value) {
            SPUtils.getInstance().put("lightSpeedApp", value)
            field = value
        }
        get() = SPUtils.getInstance().getLong("lightSpeedApp", lightSpeed)


    var lightBorderApp = lightBorder
        set(value) {
            SPUtils.getInstance().put("lightBorderApp", value)
            field = value
        }
        get() = SPUtils.getInstance().getFloat("lightBorderApp", lightBorder)

    var yyq_online = ""
        set(value) {
            SPUtils.getInstance().put("yyq_online", value)
            field = value
        }
        get() = SPUtils.getInstance().getString("yyq_online", "")

    var yyq_load = ""
        set(value) {
            SPUtils.getInstance().put("yyq_load", value)
            field = value
        }
        get() = SPUtils.getInstance().getString("yyq_load", "")
    var checkTheType: String = ""
        set(value) {
            SPUtils.getInstance().put("checkTheType", value)
            field = value
        }
        get() = SPUtils.getInstance().getString("checkTheType", "")

    private fun getYyqData(): Yyq {
        val dataJson = yyq_online.let {
            if (it.isEmpty()) {
                PaperThreeConstant.yyq
            } else {
                it
            }
        }
        return try {
            Gson().fromJson(dataJson, object : TypeToken<Yyq>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
            Gson().fromJson(
                PaperThreeConstant.yyq,
                object : TypeToken<Yyq>() {}.type
            )
        }
    }

    fun isLockWall(
        activity: AppCompatActivity,
        resourceId: Int,
        isWallPaper: Boolean = true
    ): Boolean {
        val resourceName = activity.resources.getResourceName(resourceId)
        val fileName = resourceName.substringAfterLast("/")
        return if (isWallPaper) {
            getYyqData().wp.contains(fileName, true) && !getLocalRvData(activity, resourceId)
        } else {
            getYyqData().bb.contains(fileName, true) && !getLocalRvData(activity, resourceId)
        }
    }

    fun setLocalRvData(
        activity: AppCompatActivity,
        resourceId: Int
    ) {
        val resourceName = activity.resources.getResourceName(resourceId)
        val fileName = resourceName.substringAfterLast("/")
        if (yyq_load.contains(fileName, true)) {
            return
        }
        yyq_load = if (yyq_load.isBlank()) {
            fileName
        } else {
            "$yyq_load,$fileName"
        }
    }

    private fun getLocalRvData(
        activity: AppCompatActivity,
        resourceId: Int
    ): Boolean {
        val resourceName = activity.resources.getResourceName(resourceId)
        val fileName = resourceName.substringAfterLast("/")
        return yyq_load.contains(fileName, true)
    }

    fun Int.getFileName(activity: AppCompatActivity): String {
        val resourceName = activity.resources.getResourceName(this)
        return resourceName.substringAfterLast("/")
    }

    fun getAdImage(fileName: String): Int {
        return when (fileName) {
            "ic_ad_2" -> R.drawable.ic_ad_2_img
            "ic_ad_3" -> R.drawable.ic_ad_3_img
            "ic_ad_4" -> R.drawable.ic_ad_4_img
            "ic_ad_5" -> R.drawable.ic_ad_5_img
            "ic_ad_6" -> R.drawable.ic_ad_6_img
            "ic_ad_7" -> R.drawable.ic_ad_7_img
            else -> R.drawable.ic_ad_2_img
        }
    }

    fun getClassNameList(): MutableList<String> {
        val list = mutableListOf("Nature", "Beautiful", "Art", "Animals", "City", "Cool")
        if (list.contains(checkTheType)) {
            list.remove(checkTheType)
            list.add(0, checkTheType)
        }
        return list
    }

    fun getNameData(name: String): List<Int> {
        return ArrayList(
            when (name) {
                "Nature" -> PaperThreeVariable.imageListNature
                "Beautiful" -> PaperThreeVariable.imageListBeautiful
                "Art" -> PaperThreeVariable.imageListArt
                "Animals" -> PaperThreeVariable.imageListAnime
                "City" -> PaperThreeVariable.imageListCity
                "Cool" -> PaperThreeVariable.imageListCool
                else -> mutableListOf()
            }
        )
    }
}