package com.mohsin.onlineorder.model

data class MenuItem(
    val name: String,
    val description: String,
    val imageResId: Int, // Use drawable resource ID for images
    val price: String,
    val isDeal: Boolean = false,
    val dealItems: String = "" // Only for deals
)