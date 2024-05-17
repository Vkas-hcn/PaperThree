package what.a.pity.phone.call.paperthree.b.ac.light

import android.content.Context
import android.graphics.Canvas
import android.graphics.Movie
import android.graphics.Paint
import android.graphics.RectF
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import java.io.InputStream

class MyGifImageView : AppCompatImageView {

    private var movie: Movie? = null
    private var startTime: Long = 0
    private var pausedTime: Long = 0
    private var duration: Int = 0
    private var paused: Boolean = false
    private val paint by lazy { Paint() }
    private val aa by lazy { resources.displayMetrics }

    constructor(context: Context) : super(context){

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    fun setGifResource(resId: Int) {
        movie = Movie.decodeStream(resources.openRawResource(resId))
        duration = movie?.duration() ?: 0
        startTime = System.currentTimeMillis()
        invalidate()
    }

    fun setGifInputStream(inputStream: InputStream) {
        movie = Movie.decodeStream(inputStream)
        duration = movie?.duration() ?: 0
        startTime = System.currentTimeMillis()
        invalidate()
    }


    fun play() {
        if (paused) {
            startTime += System.currentTimeMillis() - pausedTime
            paused = false
            invalidate()
        }
    }

    fun pause() {
        if (!paused) {
            pausedTime = System.currentTimeMillis()
            paused = true
        }
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        movie?.let {
            val now = System.currentTimeMillis()
            if (startTime == 0L) {
                startTime = now
            }
            val elapsedTime = if (!paused) (now - startTime) % duration else 0
            it.setTime(elapsedTime.toInt())
            val scaleWidth = width.toFloat() / it.width()
            val scaleHeight = height.toFloat() / it.height()
            canvas.scale(scaleWidth, scaleHeight)
            it.draw(canvas, 0f, 0f)
            if (!paused) {
                invalidate()
            }
        }
    }
}