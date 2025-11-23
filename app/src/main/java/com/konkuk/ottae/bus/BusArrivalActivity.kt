package com.konkuk.ottae.bus

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.WindowInsetsCompat
import com.konkuk.ottae.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer

class BusArrivalActivity : AppCompatActivity() {

    private lateinit var adapter: BusAdapter
    private lateinit var recyclerArrival: RecyclerView
    private lateinit var txtTitle: TextView
    private lateinit var txtUpdated: TextView
    private lateinit var txtEmpty: TextView
    private lateinit var btnBack: ImageView

    private val items = mutableListOf<BusItem>()
    private val client = OkHttpClient()
    private val handler = Handler(Looper.getMainLooper())

    private lateinit var stopId: String
    private lateinit var stopName: String

    private val TAGO_KEY =
        "azCePyhyAKs9%2B%2Bf8ZIcgSALFt8XO7Z8YnVzNWeMbYiujBO8QxniYGRZpYgmUDKwnUxsHFebEq0QUslGQLVe%2FNQ%3D%3D"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = android.graphics.Color.parseColor("#006633")

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContentView(R.layout.activity_bus_arrival)

        txtTitle = findViewById(R.id.txtTitle)
        recyclerArrival = findViewById(R.id.recyclerArrival)
        txtEmpty = findViewById(R.id.txtEmpty)
        txtUpdated = findViewById(R.id.txtUpdated)
        btnBack = findViewById(R.id.btnBack)

        stopId = intent.getStringExtra("stopId") ?: ""
        stopName = intent.getStringExtra("stopName") ?: ""

        txtTitle.text = "$stopName 실시간 도착정보"

        recyclerArrival.layoutManager = LinearLayoutManager(this)
        adapter = BusAdapter(items)
        recyclerArrival.adapter = adapter

        btnBack.setOnClickListener { finish() }

        loadArrivals()

        timer(period = 15000) {
            handler.post { loadArrivals() }
        }
    }


    private fun loadArrivals() {
        val url =
            "https://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList" +
                    "?serviceKey=$TAGO_KEY&_type=json&cityCode=33020" +
                    "&nodeId=$stopId&pageNo=1&numOfRows=30"

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                handler.post {
                    items.clear()
                    adapter.notifyDataSetChanged()

                    txtEmpty.text = "데이터를 불러올 수 없습니다."
                    txtEmpty.visibility = View.VISIBLE

                    updateTime()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string() ?: return
                val parsed = parseTago(body)

                handler.post {
                    items.clear()
                    items.addAll(parsed)
                    adapter.notifyDataSetChanged()

                    updateTime()

                    txtEmpty.visibility =
                        if (items.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        })
    }

    private fun updateTime() {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.KOREA)
        txtUpdated.text = "업데이트: ${sdf.format(Date())}"
    }

    private fun parseTago(json: String): List<BusItem> {
        val root = JSONObject(json)
        val body = root.optJSONObject("response")
            ?.optJSONObject("body") ?: return emptyList()

        val total = body.optInt("totalCount", 0)
        if (total == 0) return emptyList()

        val itemsField = body.optJSONObject("items")?.opt("item") ?: return emptyList()

        val list = mutableListOf<BusItem>()

        when (itemsField) {
            is JSONObject -> list.add(parseOne(itemsField))
            is org.json.JSONArray -> {
                for (i in 0 until itemsField.length()) {
                    list.add(parseOne(itemsField.getJSONObject(i)))
                }
            }
        }

        return list.sortedBy { it.minutes }
    }

    private fun parseOne(j: JSONObject): BusItem {
        val sec = j.optString("arrtime", "0").toInt()
        val minutes = Math.ceil(sec / 60.0).toInt()
        val stops = j.optString("arrprevstationcnt", "0").toInt()
        val direction = if (j.optString("updown") == "0") "상행" else "하행"

        return BusItem(
            routeId = j.optString("routeid", ""),
            routeName = j.optString("routeno", ""),
            minutes = minutes,
            stopsAway = stops,
            direction = direction
        )
    }
}
