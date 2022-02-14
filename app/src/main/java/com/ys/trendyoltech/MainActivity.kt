package com.ys.trendyoltech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.ys.trendyoltech.recycler.RAdapter
import com.ys.trendyoltech.recycler.SliderAdapter
import com.ys.trendyoltech.retrofit.APIClient
import com.ys.trendyoltech.retrofit.BannerContents
import com.ys.trendyoltech.retrofit.JsonData
import com.ys.trendyoltech.retrofit.Widgets
import com.ys.trendyoltech.tools.showErrorMsg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var recycler: RecyclerView
    lateinit var adapter: RAdapter
    var widgetsList: List<Widgets> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
    }

    override fun onStart() {
        super.onStart()
        updateWidgets()
    }

    private fun updateWidgets() {
        APIClient.retrofit.getWidgets().enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                widgetsList = response.body()?.widgets ?: return

                adapter = RAdapter(widgetsList)
                recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                recycler.adapter = adapter
//                recycler.itemAnimator = DefaultItemAnimator()
                adapter.notifyItemInserted(widgetsList.size - 1)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                showErrorMsg(t.message)
                CoroutineScope(Dispatchers.Default).launch {
                    delay(TimeUnit.MINUTES.toMillis(1))
                    updateWidgets()
                }
            }
        })
    }
}


