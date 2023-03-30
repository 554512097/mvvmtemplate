import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ListDecoration(
    context: Context,
    @DrawableRes drawableResId: Int,
    private val marginStart: Int,
    private val marginEnd: Int,
    private val height: Int
) : RecyclerView.ItemDecoration() {

    private val divider: Drawable = ContextCompat.getDrawable(context, drawableResId)!!

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + marginStart
        val right = parent.width - parent.paddingRight - marginEnd

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + height

            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }
    }
}
