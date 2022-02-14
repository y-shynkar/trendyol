package com.ys.trendyoltech.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import com.ys.trendyoltech.R
import java.util.*

class SliderAdapter(context: Context, private var imagesUrls: List<String>) : PagerAdapter() {

    private var mLayoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount() = imagesUrls.size

    override fun isViewFromObject(view: View, a: Any): Boolean {
        return view === a as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater.inflate(R.layout.item, container, false)
        val imageView = itemView.findViewById<View>(R.id.ivBanner) as ImageView
        Picasso.get().load(imagesUrls[position]).into(imageView)
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, a: Any) {
        container.removeView(a as LinearLayout)
    }


}