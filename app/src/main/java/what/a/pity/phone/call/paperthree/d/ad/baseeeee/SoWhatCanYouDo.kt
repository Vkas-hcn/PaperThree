package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.app.Activity
import android.view.ViewGroup
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


abstract class SoWhatCanYouDo(
    val adBean: EveryADBean,
    var loadTime: Long = System.currentTimeMillis()
) {

    abstract fun loadHowAreYou(
        onAdLoaded: () -> Unit = {},
        onAdLoadFailed: (msg: String?) -> Unit = {}
    )

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

    var where: String? = null,

    val adCacheInvalidTime: Int = 2,
)

enum class ADType(val placeName: String) {
    FULL_One("aiesbvaoubveaubvauwevb"),
    Nav_1("anckejsnacliecnliejncalicnalw"),
    Nav_2("acnncalicliejanliecnckejsnalw"),
    INNNNNNNN_1("amvpoesinvapiwbevnaoewubvaourbvaouwehv"),
    INNNNNNNN_2("vaouwehvnaoewamvpwbeuoesinvapibvaourbv"),

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

    @SerializedName("fa_c_pre")
    val main2: MutableList<EveryADBean>?,
    @SerializedName("fa_c_inter")
    val inter2: MutableList<EveryADBean>?,
)

@Keep
data class AdBlockingBean(
    val tsdesk: String?= "2",
    val quoswer: String?= "1",
    val preback: String?= "2",
    val preuse: String?= "1",
    val preenter: String?= "2",
    val rescontinue: String?= "0",

)