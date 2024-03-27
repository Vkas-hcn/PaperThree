package what.a.pity.phone.call.paperthree.a.utils

import android.app.Application
import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.DisplayMetrics
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import what.a.pity.phone.call.paperthree.BuildConfig
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.b.ac.DetailSetActivity
import what.a.pity.phone.call.paperthree.b.ac.MainActivity
import what.a.pity.phone.call.paperthree.b.ac.PreViewActivity
import what.a.pity.phone.call.paperthree.d.ae.fb.PaperAppFireBaseUtils


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
        MobileAds.initialize(application)
        if (!BuildConfig.DEBUG) {
            PaperAppFireBaseUtils.getFirebaseRemoteConfigData()
            PaperAppFireBaseUtils.fourAppWait4SecondsToGetData()
            MainScope().launch {
                delay(4000)
                PaperAppFireBaseUtils.appCircleToRequestFireData()
            }
        }
        application.registerActivityLifecycleCallbacks(AppLifeCallBack())
        ProcessLifecycleOwner.get().lifecycle.addObserver(PageTwoAppObserver())
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

    fun saveImg(curImg: Int, activity: PreViewActivity) {
        if (!checkPer(activity)) {
            aaa(activity, curImg)
        } else {
            saveFreshAppImageToGallery(activity, curImg)
        }

    }

    private fun aaa(activity: PreViewActivity, curImg: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                saveFreshAppImageToGallery(activity, curImg)
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.data = Uri.parse("package:" + activity.packageName)
                activity.startActivityForResult(intent, 200)
            }
        } else {
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
    }

    private fun checkPer(activity: PreViewActivity): Boolean {
        return if (Build.VERSION.SDK_INT >= 30) {
            EasyPermissions.hasPermissions(
                activity,
                android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
            )
        } else {
            EasyPermissions.hasPermissions(
                activity,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    @AfterPermissionGranted(200)
    fun saveFreshAppImageToGallery(context: PreViewActivity, imageResId: Int) {
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
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            val item = contentResolver.insert(collection, values)

            item?.let { uri ->
                try {
                    val outputStream = contentResolver.openOutputStream(uri)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream?.close()
                    Toast.makeText(context, "Save successfully!", Toast.LENGTH_SHORT).show()
                    val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                    mediaScanIntent.data = uri
                    context.sendBroadcast(mediaScanIntent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Save Failed!", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }
    }

    fun goSet(curImg: Int, activity: AppCompatActivity) {
        val intent = Intent(activity, DetailSetActivity::class.java)
        intent.putExtra("intentImgResID", curImg)
        activity.startActivity(intent)
    }


    fun setFreshAppWallpaper(
        context: Context,
        bitmapDrawable: BitmapDrawable,
        isShowTip: Boolean = true
    ) {
        if (isShowTip) ToastUtils.showLong("Setting...")
        try {
            val wallpaperManager = WallpaperManager.getInstance(context)
            val bitmap = bitmapDrawable.bitmap
            wallpaperManager.setBitmap(bitmap)
            if (isShowTip) Toast.makeText(
                context,
                "Wallpaper set successfully!",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to set wallpaper!", Toast.LENGTH_SHORT).show()
        }
    }


    fun setFreshAppLockWallPaper(context: Context, bitmapDrawable: BitmapDrawable) {
        ToastUtils.showLong("Setting...")
        val wallpaperManager = WallpaperManager.getInstance(context)
        val bitmap = bitmapDrawable.bitmap
        try {
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
            Toast.makeText(context, "Lockscreen wallpaper set successfully!", Toast.LENGTH_SHORT)
                .show()
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to set Lockscreen Wallpaper!", Toast.LENGTH_SHORT)
                .show()
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