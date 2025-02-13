package com.example.assignment_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignment_1.ui.theme.Assignment1Theme

// Data class representing a stop in the journey
data class Stop(val name: String, val visaRequirement: String, val distance: Double)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1Theme {
                MainScreen()
            }
        }
    }
}
@Composable
fun MainScreen() {
    var currentStopIndex by remember { mutableStateOf(0) } // Track the current stop
    var isKm by remember { mutableStateOf(true) } // Toggle between km and miles
    var distanceCovered by remember { mutableStateOf(0.0) } // Track distance covered

    // List of stops
    val stopsList = listOf(
        Stop("New York", "Visa Required", 500.0),
        Stop("London", "No Visa Required", 700.0),
        Stop("Paris", "Visa Required", 300.0)
    )

    val totalDistance = stopsList.sumOf { it.distance } // Total journey distance
    val distanceLeft = totalDistance - distanceCovered // Remaining distance
    val progress = (distanceCovered / totalDistance).toFloat() // Progress calculation

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display current stop
        Text(
            text = "Current Stop: ${if (currentStopIndex < stopsList.size) stopsList[currentStopIndex].name else "Journey Completed"}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Progress Bar for journey completion
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().height(10.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // List of stops
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(stopsList) { stop -> StopItem(stop) }
        }

        // Distance details
        Text(text = "Distance Left: ${if (isKm) distanceLeft else distanceLeft * 0.621371} ${if (isKm) "km" else "miles"}")
        Text(text = "Distance Covered: ${if (isKm) distanceCovered else distanceCovered * 0.621371} ${if (isKm) "km" else "miles"}")
        Spacer(modifier = Modifier.height(8.dp))

        // Button to toggle km/miles
        Button(onClick = { isKm = !isKm }) {
            Text(text = if (isKm) "Switch to Miles" else "Switch to Km")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Button to move to next stop
        Button(onClick = {
            if (currentStopIndex < stopsList.size) {
                distanceCovered += stopsList[currentStopIndex].distance
                currentStopIndex++
            }
        }, enabled = currentStopIndex < stopsList.size) {
            Text(text = "Next Stop Reached")
        }
    }
}

//@Composable
//fun MainScreen() {
//    var currentStopIndex by remember { mutableStateOf(0) } // Keeps track of the current stop
//    var isKm by remember { mutableStateOf(true) } // Determines the unit of distance (km or miles)
//    var distanceCovered by remember { mutableStateOf(0.0) } // Tracks distance covered
//
//    // List of stops with their visa requirements and distances
//    val stopsList = listOf(
//        Stop("New York", "Visa Required", 500.0),
//        Stop("London", "No Visa Required", 700.0),
//        Stop("Paris", "Visa Required", 300.0)
//    )
//
//    val totalDistance = stopsList.sumOf { it.distance } // Calculate total journey distance
//    val distanceLeft = totalDistance - distanceCovered // Calculate remaining distance
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Display current stop or journey completion message
//        Text(
//            text = "Current Stop: ${if (currentStopIndex < stopsList.size) stopsList[currentStopIndex].name else "Journey Completed"}",
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // List of stops
//        LazyColumn(modifier = Modifier.weight(1f)) {
//            items(stopsList) { stop -> StopItem(stop) }
//        }
//
//        // Display distance left and distance covered
//        Text(text = "Distance Left: ${if (isKm) distanceLeft else distanceLeft * 0.621371} ${if (isKm) "km" else "miles"}")
//        Text(text = "Distance Covered: ${if (isKm) distanceCovered else distanceCovered * 0.621371} ${if (isKm) "km" else "miles"}")
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Button to toggle between km and miles
//        Button(onClick = { isKm = !isKm }) {
//            Text(text = if (isKm) "Switch to Miles" else "Switch to Km")
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Button to progress to the next stop
//        Button(onClick = {
//            if (currentStopIndex < stopsList.size) {
//                distanceCovered += stopsList[currentStopIndex].distance
//                currentStopIndex++
//            }
//        }, enabled = currentStopIndex < stopsList.size) {
//            Text(text = "Next Stop Reached")
//        }
//    }
//}

@Composable
fun StopItem(stop: Stop) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display stop name, visa requirement, and distance
            Text(text = stop.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = "Visa Requirement: ${stop.visaRequirement}")
            Text(text = "Distance: ${stop.distance} km")
        }
    }
}
