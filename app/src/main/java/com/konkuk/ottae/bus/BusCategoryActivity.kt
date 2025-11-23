package com.konkuk.ottae.bus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.konkuk.ottae.databinding.ActivityBusCategoryBinding

data class BusStop(val stopId: String, val stopName: String)

class BusCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusCategoryBinding

    private val busStops = listOf(
        BusStop("CHB272060002", "건국대(시외방향)"),
        BusStop("CHB272064033", "건국대(시내방향)"),
        BusStop("CHB272060006", "건국대후문(시외방향)"),
        BusStop("CHB272060007", "건국대후문(시내방향)"),
        BusStop("CHB272064512", "KU스테이션(충주역·터미널 방향)"),
        BusStop("CHB272064513", "KU스테이션(건국대학교 후문·시내 방향)")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btns = listOf(
            binding.btnBus1,
            binding.btnBus2,
            binding.btnBus3,
            binding.btnBus4,
            binding.btnBus5,
            binding.btnBus6
        )

        btns.forEachIndexed { index, view ->
            view.setOnClickListener {
                val stop = busStops[index]
                val intent = Intent(this, BusArrivalActivity::class.java)
                intent.putExtra("stopId", stop.stopId)
                intent.putExtra("stopName", stop.stopName)
                startActivity(intent)
            }
        }

        binding.btnBack.setOnClickListener { finish() }
    }
}
