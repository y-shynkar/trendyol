package com.ys.trendyoltech.retrofit

import com.google.gson.annotations.SerializedName

data class JsonData(
    @SerializedName("widgets") var widgets: List<Widgets>? = null,
    @SerializedName("pagination") var pagination: Pagination? = null,
)

data class Widgets(
    @SerializedName("bannerContents") var bannerContents: ArrayList<BannerContents>? = null,
    @SerializedName("displayType") var displayType: String = "SINGLE",
    @SerializedName("eventKey") var eventKey: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("displayCount") var displayCount: Int? = null,
    @SerializedName("displayOptions") var displayOptions: DisplayOptions? = DisplayOptions(),
    @SerializedName("startDate") var startDate: String? = null,
    @SerializedName("endDate") var endDate: String? = null,
    @SerializedName("marketing") var marketing: Marketing? = Marketing(),
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("refreshRequired") var refreshRequired: Boolean? = null,
) {
    override fun toString(): String {
        return "Widgets(bannerContents=$bannerContents \n displayType='$displayType' \n id=$id \n type=$type \n " +
                "displayCount=$displayCount \n width=$width \n height=$height)"
    }
}

data class Pagination(
    @SerializedName("currentPage") var currentPage: Int? = null,
    @SerializedName("pageSize") var pageSize: Int? = null,
    @SerializedName("totalCount") var totalCount: Int? = null,
)

data class Marketing(
    @SerializedName("delphoi") var delphoi: Delphoi? = Delphoi(),
    @SerializedName("order") var order: Int? = null,
)

data class DisplayOptions(
    @SerializedName("showProductPrice") var showProductPrice: Boolean? = null,
    @SerializedName("showProductFavoredButton") var showProductFavoredButton: Boolean? = null,
    @SerializedName("showClearButton") var showClearButton: Boolean? = null,
    @SerializedName("showCountdown") var showCountdown: Boolean? = null,
    @SerializedName("showCountOnTitle") var showCountOnTitle: Boolean? = null,
)

data class Delphoi(
    @SerializedName("tv070") var tv070: String? = null,
    @SerializedName("tv072") var tv072: String? = null,
    @SerializedName("tv073") var tv073: String? = null,
    @SerializedName("tv097") var tv097: String? = null,
)

data class BannerContents(
    @SerializedName("bannerEventKey") var bannerEventKey: String? = null,
    @SerializedName("displayOrder") var displayOrder: Int? = null,
    @SerializedName("imageUrl") var imageUrl: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("marketing") var marketing: Marketing? = Marketing(),
)