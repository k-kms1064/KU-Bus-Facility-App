package com.konkuk.ottae.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    private val _favorites = MutableLiveData<List<FavoriteEntity>>(emptyList())
    val favorites: LiveData<List<FavoriteEntity>> get() = _favorites

    fun loadFavorites() {

        _favorites.value = emptyList()
    }

    fun add(item: FavoriteEntity) {

    }

    fun remove(item: FavoriteEntity) {

    }
}
