package com.konkuk.ottae.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteEntity>

    @Query("SELECT * FROM favorites WHERE facilityId = :facilityId LIMIT 1")
    suspend fun getFavoriteByFacilityId(facilityId: Int): FavoriteEntity?
}
