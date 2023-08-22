package com.example.fitpalm.Screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpalm.NinjaAPI
import com.example.fitpalm.R
import com.example.fitpalm.client
import com.example.fitpalm.nutritionData
import com.example.fitpalm.ui.theme.blue_main
import com.example.fitpalm.ui.theme.green_light_secondary
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun Calories() {
    var foodName by remember { mutableStateOf("") }
    var foodquantity by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }
    var mystring = remember { mutableStateOf("") }

    Column(modifier = Modifier
        .background(color = green_light_secondary)
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = CenterHorizontally
    ) {

        //Textfield for food name
        OutlinedTextField(
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text("Enter a food name") }
        )

        //Textfield for food quantity
        OutlinedTextField(
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done,
            ),
            value = foodquantity,
            onValueChange = { foodquantity = it },
            label = { Text("Enter how many grams") }
        )

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = blue_main),
            onClick = {
                isLoading = true
                error = false
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        if (foodName.isNotEmpty() && foodquantity.isNotEmpty()) {
                            withContext(Dispatchers.Main) {
                                isLoading = true
                            }
                            getMyNutrition(foodName, foodquantity, mystring)
                        } else {
                            error = true
                        }
                    } catch (e: Exception) {
                        error = true
                    } finally {
                        withContext(Dispatchers.Main) {
                            isLoading = false
                        }
                    }
                }
            },
            enabled = !isLoading
        ) {
            Text("Get Nutrition Information")
        }

        if (isLoading) {
            CircularProgressIndicator()
        }

        if (error) {
            Text("Error occurred while fetching data")
        }


        Card(
            shape = RoundedCornerShape(size = 30.dp),
            modifier = Modifier
                .padding(5.dp)
                .align(CenterHorizontally)
                .width(200.dp)
                .height(300.dp),
                    colors = CardDefaults.cardColors(containerColor = blue_main),
        ) {
            Text(
                color = Color.White,
                modifier = Modifier.padding(all = 16.dp),
                text = mystring.value,
                fontSize = 22.sp
            )
        }

    }
}

fun getMyNutrition(foodName: String, foodQuantity: String ,  nutritionString: MutableState<String>) {
    val client = OkHttpClient()
    val url = "https://api.calorieninjas.com/v1/nutrition?query=$foodQuantity g $foodName"
    val request = Request.Builder()
        .url(url)
        .header("X-Api-Key", "o70plLGqWu9lqaPCZFMQcw==lwtxeT86FR8z3lXE")
        .header("X-Api-Host", "api.calorieninjas.com")
        .build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            Log.d(TAG, "onFailure: ${e.message}")
        }

        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            if (!response.isSuccessful) {
                Log.d(TAG, "onResponse: HTTP error ${response.code}")
                return
            }

            val json = response.body?.string()
            val data = JSONObject(json)
            val items = data.getJSONArray("items")
            if (items.length() > 0) {
                val item = items.getJSONObject(0)
                val calories = item.getDouble("calories")
                val fats = item.getDouble("fat_total_g")
                val protein = item.getDouble("protein_g")
                val carbs = item.getDouble("carbohydrates_total_g")

                val allInfo = "Nutritional Info: \n\n" +
                        "Calories: $calories \n" +
                        "Fats: $fats g \n" +
                        "Protein: $protein g \n" +
                        "Carbs: $carbs g \n"
                updateMyString(allInfo, nutritionString)

            } else {
                Log.d(TAG, "No items found in response")
            }
        }

    })
}


fun updateMyString(mystring: String, nutritionString: MutableState<String> ) {

    nutritionString.value = mystring

}

@Preview
@Composable
fun CaloriesView() {
    Calories()
}