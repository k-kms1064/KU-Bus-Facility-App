package com.konkuk.ottae.favorite

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import com.konkuk.ottae.AppDatabase
import com.konkuk.ottae.R

class FavoriteListActivity : AppCompatActivity() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        val dao = AppDatabase.getDatabase(this).favoriteDao()
        val repository = FavoriteRepository(dao)
        viewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(repository)
        )[FavoriteViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.favoriteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = FavoriteListAdapter(emptyList(), viewModel)
        recyclerView.adapter = adapter

        viewModel.getAllFavorites { list ->
            adapter.updateList(list)
        }
    }
}
