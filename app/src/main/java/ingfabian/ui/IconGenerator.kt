package ingfabian.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ingfabian.aguilamaps.R

class IconGenerator (context: Context){
      var mContext: Context

      var mContainer: ViewGroup

    private var mTextView: TextView? = null
      var mContentView: View

    init {
        mContext = context
        mContainer =
            LayoutInflater.from(mContext).inflate(R.layout.amu_text_bubble, null) as ViewGroup
        mTextView = mContainer.findViewById(R.id.amu_text)
        mContentView = mTextView as View
    }

    fun makeIcon(text: CharSequence): Bitmap {
        if (mTextView != null) {
            mTextView!!.text = text
        }

        return makeIcon()
    }

    fun makeIcon(): Bitmap {
        val measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        mContainer.measure(measureSpec, measureSpec)
        val measuredWidth = 200
        val measuredHeight = 200
        mContainer.layout(0, 0, measuredWidth, measuredHeight)
        val r = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
        r.eraseColor(Color.TRANSPARENT)
        val canvas = Canvas(r)
        mContainer.draw(canvas)
        return r
    }
}