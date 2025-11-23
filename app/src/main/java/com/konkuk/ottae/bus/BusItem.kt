package com.konkuk.ottae.bus

data class BusItem(
    val routeId: String,
    val routeName: String,
    val minutes: Int,
    val stopsAway: Int,
    val direction: String
)
