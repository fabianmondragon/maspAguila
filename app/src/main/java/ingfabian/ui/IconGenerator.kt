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
        //mBackground = new BubbleDrawable(mContext);

        mContainer =
            LayoutInflater.from(mContext).inflate(R.layout.amu_text_bubble, null) as ViewGroup

        //mRotationLayout = (RotationLayout) mContainer.getChildAt(0);
        mTextView = mContainer.findViewById(R.id.amu_text)
        mContentView = mTextView as View
        //setStyle(STYLE_DEFAULT)
    }



    /**
     * Sets the text content, then creates an icon with the current style.
     *
     * @param text the text content to display inside the icon.
     */
    fun makeIcon(text: CharSequence): Bitmap {
        if (mTextView != null) {
            mTextView!!.text = text
        }

        return makeIcon()
    }

    /**
     * Creates an icon with the current content and style.
     *
     *
     * This method is useful if a custom view has previously been set, or if text content is not
     * applicable.
     */
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

    /**
     * Sets the child view for the icon.
     *
     *
     * If the view contains a [TextView] with the id "text", operations such as [ ][.setTextAppearance] and [.makeIcon] will operate upon that [TextView].
     */
    fun setContentView(contentView: View) {
        /*  mRotationLayout.removeAllViews();
        mRotationLayout.addView(contentView);
        mContentView = contentView;
        final View view = mRotationLayout.findViewById(R.id.amu_text);
        mTextView = view instanceof TextView ? (TextView) view : null;*/
    }


    /**
     * Sets the text color, size, style, hint color, and highlight color from the specified
     * `TextAppearance` resource.
     *
     * @param resid the identifier of the resource.
     */
    fun setTextAppearance(context: Context, resid: Int) {
        if (mTextView != null) {
            mTextView!!.setTextAppearance(context, resid)
        }
    }

    /**
     * Sets the text color, size, style, hint color, and highlight color from the specified
     * `TextAppearance` resource.
     *
     * @param resid the identifier of the resource.
     */
    fun setTextAppearance(resid: Int) {
        setTextAppearance(mContext, resid)
    }

    /**
     * Sets the style of the icon. The style consists of a background and text appearance.
     */
    /*fun setStyle(style: Int) {
        setColor(getStyleColor(style))
        setTextAppearance(mContext, getTextStyle(style))
    }*/

    /**
     * Sets the background to the default, with a given color tint.
     *
     * @param color the color for the background tint.
     */
    fun setColor(color: Int) {
        //mBackground.setColor(color);
        //setBackground(mBackground);
    }


    val STYLE_DEFAULT = 1
    val STYLE_WHITE = 2
    val STYLE_RED = 3
    val STYLE_BLUE = 4
    val STYLE_GREEN = 5
    val STYLE_PURPLE = 6
    val STYLE_ORANGE = 7

    private fun getStyleColor(style: Int): Int {
        when (style) {
            STYLE_DEFAULT, STYLE_WHITE -> return -0x1
            STYLE_RED -> return -0x340000
            STYLE_BLUE -> return -0xff6634
            STYLE_GREEN -> return -0x996700
            STYLE_PURPLE -> return -0x66cc34
            STYLE_ORANGE -> return -0x7800
            else -> return -0x1
        }
    }

   /* private fun getTextStyle(style: Int): Int {
        when (style) {
            STYLE_DEFAULT, STYLE_WHITE -> return R.style.amu_Bubble_TextAppearance_Dark
            STYLE_RED, STYLE_BLUE, STYLE_GREEN, STYLE_PURPLE, STYLE_ORANGE -> return R.style.amu_Bubble_TextAppearance_Light
            else -> return R.style.amu_Bubble_TextAppearance_Dark
        }
    }*/
}