package com.example.fitpalm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material3.NavigationBar
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            Scaffold(bottomBar = {
                BottomNavigationBar(items = listOf(
                    BottomNavItems(
                        name = "BMI",
                        route = "BMI",
                        icon = ImageVector.vectorResource(id = R.drawable.round_scale_24)
                    ),
                    BottomNavItems(
                        name = "Calories",
                        route = "Calories",
                        icon = ImageVector.vectorResource(id = R.drawable.round_food_bank_24)
                    ),
                    BottomNavItems(
                        name = "Scheduale",
                        route = "Scheduale",
                        icon = ImageVector.vectorResource(id = R.drawable.baseline_scheduale_24)
                    )
                ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )


            })


            {
                NavigationGraph(navController = navController)
            }


        }
    }
}
