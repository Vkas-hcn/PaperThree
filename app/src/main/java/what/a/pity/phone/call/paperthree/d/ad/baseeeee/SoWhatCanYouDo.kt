package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.app.Activity
import android.view.ViewGroup
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


abstract class SoWhatCanYouDo(val adBean: EveryADBean, var loadTime: Long = System.currentTimeMillis()) {

    abstract fun loadHowAreYou(onAdLoaded: () -> Unit = {}, onAdLoadFailed: (msg: String?) -> Unit = {})

    abstract fun showMyNameIsHei(
        activity: Activity,
        nativeParent: ViewGroup? = null,
        onAdDismissed: () -> Unit = {}
    )
    open fun gandiao() {}
}
@Keep
data class EveryADBean(
    @SerializedName("fa_c_eous")
    var adYype: String? = null,
    @SerializedName("fa_c_ail")
    var adNetOperator: String,
    @SerializedName("fa_c_les")
    var adIdKKKK: String,
    @SerializedName("fa_c_oute")
    var adWeightHAHHA: Int = 0,
    val adCacheInvalidTime: Int = 2,
)

enum class ADType(val placeName: String) {
    FULL_One("aiesbvaoubveaubvauwevb"),
    MMMMMMMAin("anckejsnacliecnliejncalicnalw"),
    INNNNNNNN_PAPER("amvpoesinvapiwbevnaoewubvaourbvaouwehv"),
}
@Keep
data class AdvertiseEntity(
    @SerializedName("fa_c_local")
    val showMax: Int = 0,
    @SerializedName("fa_c_firma")
    val clickMax: Int = 0,
    @SerializedName("fa_c_ongre")
    val start: MutableList<EveryADBean>?,
    @SerializedName("fa_c_pach")
    val main: MutableList<EveryADBean>?,
    @SerializedName("fa_c_osur")
    val inter: MutableList<EveryADBean>?,
)