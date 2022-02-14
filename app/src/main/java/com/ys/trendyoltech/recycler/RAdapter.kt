package com.ys.trendyoltech.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.ys.trendyoltech.R
import com.ys.trendyoltech.retrofit.Widgets


class RAdapter(private var dataSet: List<Widgets>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mViewPager: ViewPager? = null
    var imagesList = emptyList<String>()
    var mViewPagerAdapter: SliderAdapter? = null

    enum class DispType { SINGLE, CAROUSEL, SLIDER, LISTING }

    inner class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvTitle)
        val image: ImageView = view.findViewById(R.id.ivBanner)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
    }

    inner class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvTitle)
        val image: ImageView = view.findViewById(R.id.ivBanner)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
    }

    inner class ListingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvTitle)
    }

    inner class SingleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvTitle)
        val image: ImageView = view.findViewById(R.id.ivBanner)
    }


    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DispType.CAROUSEL.ordinal -> CarouselViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.widget_carousel, vg, false))
            DispType.SLIDER.ordinal -> SliderViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.widget_slider, vg, false))
            DispType.LISTING.ordinal -> ListingViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.widget_listing, vg, false))
            else -> SingleViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.widget_single, vg, false))
        }
    }

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) {
        val widget = dataSet[position]
        when (vh.itemViewType) {
            DispType.CAROUSEL.ordinal -> {
                val urlList = widget.bannerContents?.mapNotNull { it.imageUrl }
                urlList?.let { (vh as CarouselViewHolder).viewPager.adapter = SliderAdapter(vh.itemView.context, urlList) }
            }
            DispType.LISTING.ordinal -> {
                (vh as ListingViewHolder).textView.text = widget.title
            }
            DispType.SLIDER.ordinal -> {
                (vh as SliderViewHolder).textView.text = widget.title
            }
            else -> {
                val b = widget.bannerContents?.firstNotNullOfOrNull {
                    (vh as SingleViewHolder).textView.text = it.title
                    Picasso
                        .with(vh.itemView.context)
                        .load(it.imageUrl)
                        .error(R.drawable.stub)
                        .into((vh as SingleViewHolder).image)
                }
            }
        }
    }
    
    override fun getItemCount() = dataSet.size
}
