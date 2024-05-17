package what.a.pity.phone.call.paperthree.a.utils

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.webkit.WebView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.Utils
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import what.a.pity.phone.call.paperthree.BuildConfig
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.b.ac.MainActivity
import what.a.pity.phone.call.paperthree.b.ac.PreViewActivity
import what.a.pity.phone.call.paperthree.d.ae.fb.PaperAppFireBaseUtils
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import java.util.UUID

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils
import what.a.pity.phone.call.paperthree.guide.ui.con.WallPaperActivity

class AppInitUtils {

    fun initAppLifeObserver(application: Application) {
        Utils.init(application)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val czca = Application.getProcessName()
            val xaxa = application.packageName
            if (xaxa != czca) {
                WebView.setDataDirectorySuffix(czca)
            }
        }
        GetWallDataUtils.getReferInformation(application)
        if (SPUtils.getInstance().getString(KeyData.phone_uuid).isBlank()) {
            SPUtils.getInstance().put(KeyData.phone_uuid, UUID.randomUUID().toString())
        }
        MobileAds.initialize(application)
        if (!BuildConfig.DEBUG) {
            Firebase.initialize(application)
            FirebaseApp.initializeApp(application)
            PaperAppFireBaseUtils.getFirebaseRemoteConfigData()
            PaperAppFireBaseUtils.fourAppWait4SecondsToGetData()
            MainScope().launch {
                delay(4000)
                PaperAppFireBaseUtils.appCircleToRequestFireData()
            }
        }
        application.registerActivityLifecycleCallbacks(AppLifeCallBack())
        ProcessLifecycleOwner.get().lifecycle.addObserver(PageTwoAppObserver())
        SPUtils.getInstance().remove(KeyData.local_preback)
        SPUtils.getInstance().remove(KeyData.local_preuse)
        SPUtils.getInstance().remove(KeyData.local_preenter)
        SPUtils.getInstance().remove(KeyData.local_rescontinue)
        WallNetDataUtils.getGid(application)
    }

    fun screenAndroid(context: Context) {
        val l: DisplayMetrics = context.resources.displayMetrics
        val td = l.heightPixels / 760f
        val dpi = (160 * td).toInt()
        l.density = td
        l.scaledDensity = td
        l.densityDpi = dpi
    }

    fun countDown(
        max: Int,
        time: Long,
        scope: CoroutineScope,
        onTick: (Int) -> Unit,
        onFinish: (() -> Unit)? = null,
    ): Job {
        return flow {
            for (num in 0..max) {
                emit(num)
                if (num != 0) delay(time)
            }
        }.flowOn(Dispatchers.Main)
            .onEach {
                onTick.invoke(it)
            }
            .onCompletion { cause ->
                if (cause == null)
                    onFinish?.invoke()
            }
            .launchIn(scope)
    }

    fun toMain(activity: AppCompatActivity) {
        if (PaperThreeVariable.isBackGround) PaperThreeVariable.isBackGround = false
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    private fun toGuide(activity: AppCompatActivity) {
        if (PaperThreeVariable.isBackGround) PaperThreeVariable.isBackGround = false
        val intent = Intent(activity, WallPaperActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    fun isMainOrGuide(activity: AppCompatActivity) {
        if (KeyData.checkTheType.isBlank()) {
            toGuide(activity)
        } else {
            toMain(activity)
        }
    }

    fun saveImg(activity: PreViewActivity, nextFun: () -> Unit) {
        if (!checkPer(activity)) {
            aaa(activity)
        } else {
            nextFun()
        }

    }

    private fun aaa(activity: PreViewActivity) {

        val perm = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        PaperThreeVariable.isToRequestPer = true
        EasyPermissions.requestPermissions(
            PermissionRequest.Builder(
                activity,
                200,
                perm
            )
                .build()
        )
    }

    private fun checkPer(activity: PreViewActivity): Boolean {
        return if (Build.VERSION.SDK_INT < 29) {
            EasyPermissions.hasPermissions(
                activity,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        } else {
            return true
        }
    }

    fun saveFreshAppImageToGallery(
        context: AppCompatActivity,
        imageResId: Int,
        nextFun: () -> Unit
    ) {
        val drawable = context.getDrawable(imageResId)

        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val displayName = "${System.currentTimeMillis()}.jpg"

            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
            }

            val contentResolver = context.contentResolver
            val collection =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                } else {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                }

            val item = contentResolver.insert(collection, values)

            item?.let { uri ->
                try {
                    val outputStream = contentResolver.openOutputStream(uri)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream?.close()
                    nextFun()
                    val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri)
                    context.sendBroadcast(mediaScanIntent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Save Failed!", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }
    }

    fun setFreshAppHomeWallpaper(
        activity: AppCompatActivity,
        bitmapDrawable: BitmapDrawable,
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val wallpaperManager = WallpaperManager.getInstance(activity)
                val bitmap = bitmapDrawable.bitmap
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
                WallNetDataUtils.postPotIntData(activity, "wa10ll", "fa", "home")
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(activity, "Failed to set wallpaper!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setFreshAppWallpaper(
        activity: AppCompatActivity,
        bitmapDrawable: BitmapDrawable,
        nextFun: () -> Unit
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val wallpaperManager = WallpaperManager.getInstance(activity)
                val bitmap = bitmapDrawable.bitmap
                wallpaperManager.setBitmap(bitmap)
                WallNetDataUtils.postPotIntData(activity, "wa10ll", "fa", "both")
                withContext(Dispatchers.Main) {
                    nextFun()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(activity, "Failed to set wallpaper!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun setFreshAppLockWallPaper(
        activity: PreViewActivity, bitmapDrawable: BitmapDrawable,
        type: String,
        isLock: Boolean = false
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val wallpaperManager = WallpaperManager.getInstance(activity)
            val bitmap = bitmapDrawable.bitmap

            try {
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                if (isLock) {
                    WallNetDataUtils.postPotIntData(activity, "wa10ll", "fa", type)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        activity,
                        "Failed to set Lockscreen Wallpaper!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
}

class PageTwoAppObserver : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        PaperThreeVariable.isAppForeground = true
    }

    override fun onStop(owner: LifecycleOwner) {
        PaperThreeVariable.isAppForeground = false
    }
}