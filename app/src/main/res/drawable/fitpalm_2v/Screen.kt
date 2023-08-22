package com.example.fitpalm_2v

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    var name:String,
    var icon: Int,
    var route:String
) {
    object BMI : Screen("BMI", R.drawable.round_scale_24, "BMI")
    object Calories : Screen("Calories", R.drawable.round_food_bank_24, "Calories")
    object Macro : Screen("Macro", R.drawable.round_calculate_24, "Macro")
    object Facts : Screen("Facts", R.drawable.round_facts_24, "Facts")
}

