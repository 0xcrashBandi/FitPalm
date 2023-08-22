package com.example.fitpalm.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpalm.R
import com.example.fitpalm.ui.theme.blue_main
import com.example.fitpalm.ui.theme.green_light_secondary
import com.example.fitpalm.ui.theme.very_light_green
import kotlin.math.absoluteValue
import kotlin.math.pow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMI() {
    var bmi = remember { mutableStateOf(0.0f) }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = green_light_secondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            //height
            OutlinedTextField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done,
                ),
                label = { Text(text = "Enter your height (cm)")},
                value = height,
                onValueChange = { height = it }
            )
            Spacer(modifier = Modifier.padding(8.dp))

            //weight
            OutlinedTextField(
                label = { Text(text = "Enter your weight (kg)")},
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                value = weight,
                onValueChange = { weight = it }
            )
            Spacer(modifier = Modifier.padding(8.dp))

            //button stuff
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(containerColor = blue_main),
                onClick = {
                calculateBMI(height, weight, bmi)
                bmi.value = bmi.value // update the bmi state after the calculation
            }) {
                Text(text = "  Calculate BMI", textAlign = TextAlign.Center, color = Color.White)
                Spacer(modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.padding(7.dp))
            Card(
                shape = RoundedCornerShape(size = 30.dp),
                colors = CardDefaults.cardColors(containerColor = blue_main),
                modifier = Modifier
                    .padding(6.dp)
                    .align(CenterHorizontally)
                    .width(200.dp)
                    .height(300.dp)
            ) {

                if (bmi.value > 0.0f) {
                    if (bmi.value < 18.5){
                        Text(
                            color = Color.White,
                            fontSize = 15.sp,
                            text = ("Your BMI is %.2f, [Underweight] \n " +
                                    "A BMI below the normal range indicates being underweight. This may suggest that a person " +
                                    "has a lower amount of body fat and muscle mass than is considered healthy. Being " +
                                    "underweight can be associated with potential health risks such as weakened immune " +
                                    "system, and decreased energy levels." +
                                    "").format(bmi.value),
                            modifier = Modifier.padding(16.dp) // Add padding to the Text composable
                                .fillMaxWidth() // Set the width of the Text composable to fill the available space
                                .wrapContentHeight() // Set the height of the Text composable to wrap its content
                        )
                    }
                    else if (bmi.value > 18.5f && bmi.value < 24.9f){
                        Text(
                            color = Color.White,
                            fontSize = 15.sp,
                            text = ("Your BMI is %.2f, [Normal weight] \n " +
                                    "Falling within the normal BMI range suggests that a person has a healthy body weight " +
                                    "relative to their height. It indicates that the individual has a balanced amount of " +
                                    "body fat and muscle mass, which is generally associated with lower risk of certain " +
                                    "health conditions." +
                                    "").format(bmi.value),
                            modifier = Modifier.padding(16.dp) // Add padding to the Text composable
                                .fillMaxWidth() // Set the width of the Text composable to fill the available space
                                .wrapContentHeight() // Set the height of the Text composable to wrap its content
                        )
                    }
                    else if (bmi.value > 25.0f){
                        Text(
                            color = Color.White,
                            fontSize = 15.sp,
                            text = ("Your BMI is %.2f, [Overweight] \n " +
                                    "When BMI exceeds the normal range, it indicates being overweight. This suggests that " +
                                    "a person has an excess amount of body fat relative to their height. Being overweight " +
                                    "can increase the risk of various health issues such as heart disease, diabetes, and " +
                                    "joint problems." +
                                    "").format(bmi.value),
                            modifier = Modifier.padding(16.dp) // Add padding to the Text composable
                                .fillMaxWidth() // Set the width of the Text composable to fill the available space
                                .wrapContentHeight() // Set the height of the Text composable to wrap its content
                        )
                    }
                }
            }
        }
    }
}

fun calculateBMI(height: String, weight: String, bmiState: MutableState<Float>) {
    val h = height.toFloatOrNull() ?: return
    val w = weight.toFloatOrNull() ?: return
    val bmi = w / ((h/100)*(h/100))
    bmiState.value = bmi // use bmiState.value instead of bmiState
}

@Preview
@Composable
fun BmiView() {
    BMI()
}