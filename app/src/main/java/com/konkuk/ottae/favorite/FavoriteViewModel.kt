package com.konkuk.ottae.favorite

import androidx.lifecycle.ViewModel

class FavoriteViewModel(
    private val repository: FavoriteRepository = FavoriteRepository()
) : ViewModel() {

    fun loadFavorites(): List<FavoriteEntity> {
        return repository.getFavorites()
    }

    fun add(item: FavoriteEntity) {
        repository.addFavorite(item)
    }

    fun remove(item: FavoriteEntity) {
        repository.removeFavorite(item)
    }
}
