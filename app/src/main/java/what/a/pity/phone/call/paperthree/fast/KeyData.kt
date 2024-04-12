package what.a.pity.phone.call.paperthree.fast

import com.blankj.utilcode.util.SPUtils
import what.a.pity.phone.call.paperthree.BuildConfig

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

}