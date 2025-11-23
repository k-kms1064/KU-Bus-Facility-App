package com.konkuk.ottae.facility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.ottae.R

class FacilityListActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FacilityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facility_list)

        recycler = findViewById(R.id.recyclerFacilities)
        recycler.layoutManager = LinearLayoutManager(this)

        val dummyList = listOf(
            Facility("스타벅스", "카페"),
            Facility("홍콩반점", "중식"),
            Facility("맘스터치", "버거"),
            Facility("백다방", "카페"),
            Facility("이삭토스트", "간식")
        )

        adapter = FacilityAdapter(dummyList) { clickedItem ->
            Toast.makeText(this, "${clickedItem.name} 클릭됨", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = adapter
    }
}
