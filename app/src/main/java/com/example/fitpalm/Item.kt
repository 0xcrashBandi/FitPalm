package com.example.fitpalm

data class Item(
    val calories: Int,
    val carbohydrates_total_g: Double,
    val cholesterol_mg: Int,
    val fat_saturated_g: Int,
    val fat_total_g: Double,
    val fiber_g: Double,
    val name: String,
    val potassium_mg: Int,
    val protein_g: Double,
    val serving_size_g: Int,
    val sodium_mg: Int,
    val sugar_g: Double
)