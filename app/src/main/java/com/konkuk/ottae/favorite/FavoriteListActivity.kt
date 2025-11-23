package com.konkuk.ottae.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.ottae.R

class FavoriteListActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FavoriteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)

        recycler = findViewById(R.id.recyclerFavorites)
        recycler.layoutManager = LinearLayoutManager(this)

        // 개발 단계: 더미 FavoriteEntity 데이터
        val dummyList = listOf(
            FavoriteEntity("스타벅스", "카페"),
            FavoriteEntity("홍콩반점", "중식"),
            FavoriteEntity("맘스터치", "버거"),
            FavoriteEntity("백다방", "카페")
        )

        adapter = FavoriteListAdapter(dummyList)
        recycler.adapter = adapter
    }
}
