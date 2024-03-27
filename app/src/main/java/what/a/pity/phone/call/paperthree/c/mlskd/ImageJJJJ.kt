package what.a.pity.phone.call.paperthree.c.mlskd

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.widget.ImageView
import com.blankj.utilcode.util.SizeUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.request.RequestOptions
import java.security.MessageDigest


object ImageKKKK {
    fun loadRoundedImage(context: Context?, url: Int?, view: ImageView?, cornors: Float? = 12f) {
        val transformation = SoWhyAreYou(
            context,
            SizeUtils.dp2px(cornors ?: 12f).toFloat()
        )
        val options = RequestOptions()
        context?.let {
            view?.let { it1 ->
                Glide.with(it).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .apply(options)
                    .transform(transformation)
                    .into(it1)
            }
        }
    }
}

class SoWhyAreYou(context: Context?, radius: Float) : Transformation<Bitmap?> {
    private val mBitmapPool: BitmapPool
    private var radius: Float

    init {
        mBitmapPool = Glide.get(context!!).bitmapPool
        this.radius = radius
    }

    override fun transform(
        context: Context,
        resource: Resource<Bitmap?>,
        outWidth: Int,
        outHeight: Int
    ): Resource<Bitmap?> {
        val source = resource.get()
        var finalWidth: Int
        var finalHeight: Int
        var ratio: Float
        if (outWidth > outHeight) {
            ratio = outHeight.toFloat() / outWidth.toFloat()
            finalWidth = source.width
            finalHeight = (source.width.toFloat() * ratio).toInt()
            if (finalHeight > source.height) {
                ratio = outWidth.toFloat() / outHeight.toFloat()
                finalHeight = source.height
                finalWidth = (source.height.toFloat() * ratio).toInt()
            }
        } else if (outWidth < outHeight) {
            ratio = outWidth.toFloat() / outHeight.toFloat()
            finalHeight = source.height
            finalWidth = (source.height.toFloat() * ratio).toInt()
            if (finalWidth > source.width) {
                ratio = outHeight.toFloat() / outWidth.toFloat()
                finalWidth = source.width
                finalHeight = (source.width.toFloat() * ratio).toInt()
            }
        } else {
            finalHeight = source.height
            finalWidth = finalHeight
        }

        radius *= finalHeight.toFloat() / outHeight.toFloat()
        var outBitmap: Bitmap? = mBitmapPool[finalWidth, finalHeight, Bitmap.Config.ARGB_8888]
        if (outBitmap == null) {
            outBitmap = Bitmap.createBitmap(finalWidth, finalHeight, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(outBitmap!!)
        val paint = Paint()
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val width = (source.width - finalWidth) / 2
        val height = (source.height - finalHeight) / 2
        if (width != 0 || height != 0) {
            val matrix = Matrix()
            matrix.setTranslate((-width).toFloat(), (-height).toFloat())
            shader.setLocalMatrix(matrix)
        }
        paint.shader = shader
        paint.isAntiAlias = true
        val rectF = RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)
        return BitmapResource.obtain(outBitmap, mBitmapPool)!!
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}


}
