package what.a.pity.phone.call.paperthree.b.ac.light


import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.fast.KeyData

class RotatingGradientRectangleView : View {
    private lateinit var paint: Paint
    private var rotationAngle = 0f
    private var gradientColors = intArrayOf(
        Color.parseColor("#ECEA41"),
        Color.parseColor("#C3E76E"),
        Color.parseColor("#A1E691"),
        Color.parseColor("#7FDABA"),
        Color.parseColor("#63C8DD"),
        Color.parseColor("#53BDF2")
    )
    private var animationDuration = 500L
    private var strokeWidth = 50f
    private var cornerRadius = 20f

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = MeasureSpec.getSize(widthMeasureSpec)
        val desiredHeight = MeasureSpec.getSize(heightMeasureSpec)
        val width = resolveSizeAndState(desiredWidth, widthMeasureSpec, 0)
        val height = resolveSizeAndState(desiredHeight, heightMeasureSpec, 0)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            val centerX = width / 2f
            val centerY = height / 2f

            val gradient = SweepGradient(centerX, centerY, gradientColors, null)
            val matrix = Matrix()
            matrix.setRotate(rotationAngle, centerX, centerY)
            gradient.setLocalMatrix(matrix)
            paint.shader = gradient

            val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
            canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint)
        }
    }

    fun setAnimationDuration(duration: Long) {
        this.animationDuration = duration
        startRotationAnimation()
    }

    fun setStrokeWidth(width: Float) {
        this.strokeWidth = if (width <= 1.0) {
            1f
        } else {
            width
        }
        paint.strokeWidth = strokeWidth
        invalidate()
    }

    fun setCornerRadius(radius: Float) {
        this.cornerRadius = radius
        invalidate()
    }

    fun setGradientSetting() {
        gradientColors = if (PaperThreeApp.isGifImage) {
            nameToGradientColorsTop(KeyData.isImagePos)
        } else {
            nameToGradientColors(KeyData.isImagePos)
        }
        setStrokeWidth(KeyData.lightBorder)
        setAnimationDuration(KeyData.lightSpeed)
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startRotationAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks(null)
    }

    private fun startRotationAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.addUpdateListener { valueAnimator ->
            rotationAngle = valueAnimator.animatedValue as Float
            invalidate()
        }
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.duration = animationDuration
        animator.start()
    }

    private fun nameToGradientColors(pos: Int): IntArray {
        return when (pos) {
            0 -> {
                intArrayOf(
                    Color.parseColor("#ECEA41"),
                    Color.parseColor("#C3E76E"),
                    Color.parseColor("#A1E691"),
                    Color.parseColor("#7FDABA"),
                    Color.parseColor("#63C8DD"),
                    Color.parseColor("#53BDF2")
                )
            }

            1 -> {
                intArrayOf(
                    Color.parseColor("#DB4DF6"),
                    Color.parseColor("#E357C8"),
                    Color.parseColor("#EB5DA0"),
                    Color.parseColor("#F16778"),
                    Color.parseColor("#F66C59"),
                    Color.parseColor("#FC7135")
                )
            }

            2 -> {
                intArrayOf(
                    Color.parseColor("#41C7E1"),
                    Color.parseColor("#30CBAB"),
                    Color.parseColor("#28CE85"),
                    Color.parseColor("#1FD165"),
                    Color.parseColor("#12D442"),
                    Color.parseColor("#0AD814")
                )
            }

            3 -> {
                intArrayOf(
                    Color.parseColor("#F9CE23"),
                    Color.parseColor("#F9A718"),
                    Color.parseColor("#F98011"),
                    Color.parseColor("#F86E0C"),
                    Color.parseColor("#F9630B"),
                    Color.parseColor("#F74806")
                )
            }

            4 -> {
                intArrayOf(
                    Color.parseColor("#7655FF"),
                    Color.parseColor("#8B4AFF"),
                    Color.parseColor("#A23EFF"),
                    Color.parseColor("#AB39FF"),
                    Color.parseColor("#BA32FF"),
                    Color.parseColor("#CE27FF")
                )
            }

            5 -> {
                intArrayOf(
                    Color.parseColor("#F3C1AD"),
                    Color.parseColor("#D1ABB1"),
                    Color.parseColor("#BB9BB3"),
                    Color.parseColor("#9F87B6"),
                    Color.parseColor("#796EBC"),
                    Color.parseColor("#464AC3")
                )
            }

            6 -> {
                intArrayOf(
                    Color.parseColor("#FEB2BD"),
                    Color.parseColor("#F2C3BF"),
                    Color.parseColor("#E9D0BF"),
                    Color.parseColor("#E1DDBF"),
                    Color.parseColor("#D4EEC0"),
                    Color.parseColor("#D0F4C0")
                )
            }

            7 -> {
                intArrayOf(
                    Color.parseColor("#494BEA"),
                    Color.parseColor("#BB68A3"),
                    Color.parseColor("#EC7A7D"),
                    Color.parseColor("#EE7C7A"),
                    Color.parseColor("#FDA187"),
                    Color.parseColor("#F78769")
                )
            }

            8 -> {
                intArrayOf(
                    Color.parseColor("#33C698"),
                    Color.parseColor("#66A8A6"),
                    Color.parseColor("#8293AE"),
                    Color.parseColor("#9289B3"),
                    Color.parseColor("#A978BB"),
                    Color.parseColor("#D05DC7")
                )
            }

            9 -> {
                intArrayOf(
                    Color.parseColor("#BC4394"),
                    Color.parseColor("#9B449D"),
                    Color.parseColor("#8346A4"),
                    Color.parseColor("#6748AB"),
                    Color.parseColor("#414CB5"),
                    Color.parseColor("#0551C1")
                )
            }

            10 -> {
                intArrayOf(
                    Color.parseColor("#C6CC4B"),
                    Color.parseColor("#C49C70"),
                    Color.parseColor("#C254A7"),
                    Color.parseColor("#C725BB"),
                    Color.parseColor("#E32771"),
                    Color.parseColor("#FD282D")
                )
            }

            11 -> {
                intArrayOf(
                    Color.parseColor("#42FEB2"),
                    Color.parseColor("#34EAD4"),
                    Color.parseColor("#40CAE0"),
                    Color.parseColor("#6D7BE7"),
                    Color.parseColor("#AB2FDE"),
                    Color.parseColor("#F3339F")
                )
            }


            12 -> {
                intArrayOf(
                    Color.parseColor("#351731"),
                    Color.parseColor("#843255"),
                    Color.parseColor("#C1597E"),
                    Color.parseColor("#CF95A6"),
                    Color.parseColor("#D2AEB7"),
                    Color.parseColor("#DBD6D5")
                )
            }

            13 -> {
                intArrayOf(
                    Color.parseColor("#7048C9"),
                    Color.parseColor("#7D419E"),
                    Color.parseColor("#A03A6F"),
                    Color.parseColor("#B43B65"),
                    Color.parseColor("#CD3B59"),
                    Color.parseColor("#E53B4D")
                )
            }

            14 -> {
                intArrayOf(
                    Color.parseColor("#B428FF"),
                    Color.parseColor("#A23FFE"),
                    Color.parseColor("#796FFD"),
                    Color.parseColor("#5599FD"),
                    Color.parseColor("#35BDFD"),
                    Color.parseColor("#27CDFE")
                )
            }


            15 -> {
                intArrayOf(
                    Color.parseColor("#DA4DF5"),
                    Color.parseColor("#D3BC82"),
                    Color.parseColor("#D8D44A"),
                    Color.parseColor("#EC7C65"),
                    Color.parseColor("#C08D87"),
                    Color.parseColor("#4BE8D3")
                )
            }

            16 -> {
                intArrayOf(
                    Color.parseColor("#FF982C"),
                    Color.parseColor("#FC676E"),
                    Color.parseColor("#F435B7"),
                    Color.parseColor("#CB5AC5"),
                    Color.parseColor("#8C92DC"),
                    Color.parseColor("#3ADCFB")
                )
            }

            17 -> {
                intArrayOf(
                    Color.parseColor("#D5FAF7"),
                    Color.parseColor("#DBF7EB"),
                    Color.parseColor("#E3EFD4"),
                    Color.parseColor("#EDE8BE"),
                    Color.parseColor("#F4E4AD"),
                    Color.parseColor("#FADD9A")
                )
            }


            18 -> {
                intArrayOf(
                    Color.parseColor("#25F852"),
                    Color.parseColor("#2BC387"),
                    Color.parseColor("#2A7CD1"),
                    Color.parseColor("#6F58A7"),
                    Color.parseColor("#B14570"),
                    Color.parseColor("#F23038")
                )
            }

            19 -> {
                intArrayOf(
                    Color.parseColor("#FC56EA"),
                    Color.parseColor("#F56AA2"),
                    Color.parseColor("#C9AC6E"),
                    Color.parseColor("#A5E061"),
                    Color.parseColor("#5BE190"),
                    Color.parseColor("#13DAC3")
                )
            }

            else -> {
                intArrayOf(
                    Color.parseColor("#ECEA41"),
                    Color.parseColor("#C3E76E"),
                    Color.parseColor("#A1E691"),
                    Color.parseColor("#7FDABA"),
                    Color.parseColor("#63C8DD"),
                    Color.parseColor("#53BDF2")
                )
            }
        }
    }


    private fun nameToGradientColorsTop(pos: Int): IntArray {
        return when (pos) {
            0 -> {
                intArrayOf(
                    Color.parseColor("#8FA5FF"),
                    Color.parseColor("#B1A4C3"),
                    Color.parseColor("#CDA297"),
                    Color.parseColor("#E2A077"),
                    Color.parseColor("#EEA05F"),
                    Color.parseColor("#FF9E42")
                )
            }

            1 -> {
                intArrayOf(
                    Color.parseColor("#FF982C"),
                    Color.parseColor("#FC676E"),
                    Color.parseColor("#F435B7"),
                    Color.parseColor("#CB5AC5"),
                    Color.parseColor("#8C92DC"),
                    Color.parseColor("#3ADCFB")
                )
            }

            2 -> {
                intArrayOf(
                    Color.parseColor("#D5FAF7"),
                    Color.parseColor("#DBF7EB"),
                    Color.parseColor("#E3EFD4"),
                    Color.parseColor("#EDE8BE"),
                    Color.parseColor("#F4E4AD"),
                    Color.parseColor("#FADD9A")
                )
            }

            else -> {
                intArrayOf(
                    Color.parseColor("#ECEA41"),
                    Color.parseColor("#C3E76E"),
                    Color.parseColor("#A1E691"),
                    Color.parseColor("#7FDABA"),
                    Color.parseColor("#63C8DD"),
                    Color.parseColor("#53BDF2")
                )
            }
        }
    }

}




