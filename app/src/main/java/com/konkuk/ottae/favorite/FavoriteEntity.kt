package com.konkuk.ottae.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val facilityId: Int,
    val facilityName: String
)
