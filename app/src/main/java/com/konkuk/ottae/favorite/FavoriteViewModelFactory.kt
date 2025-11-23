package com.konkuk.ottae.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {

    fun addFavorite(favorite: FavoriteEntity) {
        viewModelScope.launch {
            repository.addFavorite(favorite)
        }
    }

    fun removeFavorite(favorite: FavoriteEntity) {
        viewModelScope.launch {
            repository.removeFavorite(favorite)
        }
    }

    fun isFavorite(facilityId: Int, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = repository.isFavorite(facilityId)
            callback(result)
        }
    }

    fun getAllFavorites(callback: (List<FavoriteEntity>) -> Unit) {
        viewModelScope.launch {
            val result = repository.getAllFavorites()
            callback(result)
        }
    }
}
