package com.ys.trendyoltech

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ys.trendyoltech.retrofit.APIClient
import com.ys.trendyoltech.tools.l
import com.ys.trendyoltech.tools.showErrorMsg
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tvTitle).setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    try {
                        val res = APIClient.retrofit.getWidgets().execute()
                        l(res.body())
                    } catch (e: Exception) {
                        showErrorMsg("${e.message}}")
                    }
                }
            }
        }
    }
}