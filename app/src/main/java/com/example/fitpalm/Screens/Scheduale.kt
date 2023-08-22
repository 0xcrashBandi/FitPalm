package com.example.fitpalm.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpalm.ui.theme.blue_main
import com.example.fitpalm.ui.theme.green_light_secondary


data class splits(
    var name: String,
    var numDays: Int,
    var split: String
)

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Scheduale() {

    Column(
        modifier = Modifier
            .background(color = green_light_secondary)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .align(CenterHorizontally)
        ) {
            DropDownMenu()
        }

    }


}

//function to display the details of the split
@Composable
fun ShowSplit(selectedSplitIndex: Int) {

    Card(modifier = Modifier
        .width(270.dp)
        .height(300.dp),
        colors = CardDefaults.cardColors(containerColor = blue_main),
        shape = RoundedCornerShape(size = 30.dp),
        ) {

        Text(
            color = Color.White,
            text = scheduale[selectedSplitIndex].split,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }

}

//dropdown menu function
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu() {
    val preferredDays = listOf("Three days", "Four Days", "Five days", "Six days")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(preferredDays[0]) }
    var selectedIndex by remember { mutableIntStateOf(0) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            modifier = Modifier,
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Pick your preference") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done))
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },

        ) {
            preferredDays.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        selectedIndex = preferredDays.indexOf(selectionOption)

                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
    Column(modifier = Modifier
        .padding(60.dp)
    ) {
        Spacer(modifier = Modifier.size(200.dp))
        ShowSplit(selectedSplitIndex = selectedIndex)
    }
}



val threeDays = splits("Three days", 3, "\n" +
        "    Day 1: Full body \n" +
        "    Day 2: Rest\n" +
        "    Day 4: Full body\n" +
        "    Day 5: Rest\n" +
        "    Day 6: Full body\n" +
        "    Day 7: Rest\n")

val fourDays = splits("Four Days", 4, "\n" +
        "    Day 1: Chest, triceps\n" +
        "    Day 2: Rest\n" +
        "    Day 3: Back, biceps\n" +
        "    Day 4: Rest\n" +
        "    Day 5: Legs, glutes\n" +
        "    Day 6: Rest\n" +
        "    Day 7: Abs, shoulders\n")

val fiveDays = splits("Five Days", 5, "\n" +
        "    Day 1: Chest, triceps\n" +
        "    Day 2: Back, biceps\n" +
        "    Day 3: Legs\n" +
        "    Day 4: Rest\n" +
        "    Day 5: Shoulders, abs\n" +
        "    Day 6: Legs, glutes\n" +
        "    Day 7: Rest\n" )

val sixDays =splits("Six Days", 6, "\n" +
        "    Day 1: Chest, triceps\n" +
        "    Day 2: Back, biceps\n" +
        "    Day 3: Rest\n" +
        "    Day 4: Legs, glutes\n" +
        "    Day 5: Shoulders, abs\n" +
        "    Day 6: Arms\n" +
        "    Day 7: Rest\n")

val scheduale = listOf(threeDays, fourDays, fiveDays, sixDays)

