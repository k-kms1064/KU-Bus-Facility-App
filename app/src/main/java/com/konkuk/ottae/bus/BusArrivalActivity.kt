package com.konkuk.ottae.bus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.ottae.R

class BusArrivalActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private val adapter = BusAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_arrival)

        recycler = findViewById(R.id.recyclerBusArrival)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }
}
