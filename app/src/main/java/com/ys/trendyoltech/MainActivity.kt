package com.ys.trendyoltech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ys.trendyoltech.recycler.RAdapter
import com.ys.trendyoltech.retrofit.APIClient
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
    var adapter = RAdapter(emptyList())
    var widgetsList: List<Widgets> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)

        loadData()
    }

    private fun loadData() {
        APIClient.retrofit.getWidgets().enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                widgetsList = response.body()?.widgets ?: return

                adapter = RAdapter(widgetsList)
                recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                recycler.adapter = adapter
                adapter.notifyItemInserted(widgetsList.size)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                showErrorMsg(t.localizedMessage)
                CoroutineScope(Dispatchers.Default).launch {
                    delay(TimeUnit.MINUTES.toMillis(1))
                    loadData()
                }
            }
        })
    }
}


