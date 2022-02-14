package com.ys.trendyoltech.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ys.trendyoltech.R
import com.ys.trendyoltech.retrofit.Widgets


class RAdapter(private var dataSet: List<Widgets>) : RecyclerView.Adapter<RAdapter.ViewHolder>() {

    enum class DispType { SINGLE, CAROUSEL, SLIDER, LISTING }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvTitle)
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun getItemViewType(position: Int): Int {
        return try {
            DispType.valueOf(dataSet[position].displayType).ordinal
        } catch (e: Exception) {
            DispType.SINGLE.ordinal
        }
    }

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ViewHolder {
        val resID = when (viewType) {
            DispType.CAROUSEL.ordinal -> R.layout.widget_carousel
            DispType.SLIDER.ordinal -> R.layout.widget_slider
            DispType.LISTING.ordinal -> R.layout.widget_listing
            else -> R.layout.widget_single
        }

        val view = LayoutInflater.from(vg.context).inflate(resID, vg, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val widget = dataSet[position]
        when (vh.itemViewType) {
            DispType.SINGLE.ordinal -> {
                vh.textView.text = widget.bannerContents?.firstOrNull().let { it?.title } ?: ""
                widget.bannerContents?.firstNotNullOfOrNull { banner ->
                    Picasso.with(vh.itemView.context)
                        .load(banner.imageUrl)
//                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.stub)
                        .into(vh.image)
                }
            }
        }
    }

    override fun getItemCount() = dataSet.size
}
