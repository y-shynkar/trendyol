package com.ys.trendyoltech.retrofit

import com.ys.trendyoltech.tools.l
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

/** Postman Collection: https://www.postman.com/collections/d1237ad063fd00215cca  */

const val BASE_URL = "https://browsingpublic.trendyol.com/"
const val QUERY_WIDGETS = "mobile-zeus-zeus-service/widget/display/personalized?widgetPageName=interview&platform=android"

interface ApiService {
    @Headers("Content-Type: application/json", "Build: 500")
    @GET(QUERY_WIDGETS)
    fun getWidgets(): Call<JsonData>
}

object APIClient {
    private val httpLogging = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            l("chain.request: ${chain.request()}")
            chain.proceed(chain.request())
        }.addInterceptor(httpLogging)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}