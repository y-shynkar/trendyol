package com.ys.trendyoltech.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

object Dialog {

    fun show(context: Context, title: String, bitmap: Bitmap) {
        val builder = Dialog(context)
        with(builder) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setOnDismissListener {
                it.dismiss()
            }
            val imageView = ImageView(context)
            imageView.setImageBitmap(bitmap)

            addContentView(TextView(context).apply { text = title },
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT))
            addContentView(imageView,
                RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT))
            show()
        }
    }
}