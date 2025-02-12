package com.example.assignment_1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_1.adapter.StopsAdapter

// A simple data class for a stop on your journey.
data class Stop(val name: String, val visaRequirement: String, val distance: Double)

// Extension function to format a Double to 2 decimals.
fun Double.format(digits: Int) = "%.${digits}f".format(this)

class MainActivity : AppCompatActivity() {

    // UI elements from activity_main.xml
    private lateinit var tvCurrentStop: TextView
    private lateinit var btnNextStop: Button
    private lateinit var tvDistanceCovered: TextView
    private lateinit var tvDistance: TextView
    private lateinit var btnSwitchUnit: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var rvStops: RecyclerView

    // Data & state
    private var isKm = true
    private var currentStopIndex = 0
    private lateinit var stopsList: List<Stop>
    private var totalDistance: Double = 0.0
    private var distanceCovered: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use the XML layout
        setContentView(R.layout.activity_main)

        // Get references to the UI elements via their IDs.
        tvCurrentStop = findViewById(R.id.tv_current_stop)
        btnNextStop = findViewById(R.id.btn_next_stop)
        tvDistance = findViewById(R.id.tv_distance)
        tvDistanceCovered = findViewById(R.id.tv_distance_covered)
        btnSwitchUnit = findViewById(R.id.btn_switch_unit)
        progressBar = findViewById(R.id.progress_bar)
        rvStops = findViewById(R.id.rv_stops)

        // Read the stops from the raw resource file. Make sure res/raw/stops.txt exists.
        stopsList = readStopsFromResource()

        // If there are more than 3 stops, show and set up the RecyclerView.
        if (stopsList.size > 3) {
            rvStops.visibility = View.VISIBLE
            setupRecyclerView()
        } else {
            rvStops.visibility = View.GONE
        }

        // Calculate the total distance (assuming each stop’s 'distance' is the distance from the previous stop).
        totalDistance = stopsList.sumOf { it.distance }

        // Set initial UI texts.
        updateCurrentStopUI()
        updateDistanceUI()
        updateProgressBar()

        // Button to switch between km and miles.
        btnSwitchUnit.setOnClickListener {
            isKm = !isKm
            updateDistanceUI()
            btnSwitchUnit.text = if (isKm) "Switch to Miles" else "Switch to Km"
        }

        // Button to mark the next stop as reached.
        btnNextStop.setOnClickListener {
            if (currentStopIndex < stopsList.size) {

                updateCurrentStopUI()

                // Update the distance covered.

                distanceCovered += stopsList[currentStopIndex].distance
                currentStopIndex++

                updateDistanceUI()
                updateProgressBar()
            }
        }
    }

    // Reads stops from a text file in res/raw/stops.txt.
    private fun readStopsFromResource(): List<Stop> {
        val stops = mutableListOf<Stop>()
        try {
            // Make sure you create the file res/raw/stops.txt (see sample content above).
            val inputStream = resources.openRawResource(R.raw.stops)
            inputStream.bufferedReader().forEachLine { line ->
                // Expected format for each line: stopName,visaRequirement,distance
                val parts = line.split(",")
                if (parts.size >= 3) {
                    val name = parts[0].trim()
                    val visaRequirement = parts[1].trim()
                    val distance = parts[2].trim().toDoubleOrNull() ?: 0.0
                    stops.add(Stop(name, visaRequirement, distance))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stops
    }

    // Sets up the RecyclerView with a simple adapter.
    private fun setupRecyclerView() {
        rvStops.layoutManager = LinearLayoutManager(this)
        rvStops.adapter = StopsAdapter(stopsList)
    }

    // Updates the current stop text.
    private fun updateCurrentStopUI() {
        if (currentStopIndex < stopsList.size) {
            tvCurrentStop.text = "Current Stop: ${stopsList[currentStopIndex].name}"
        } else {
            tvCurrentStop.text = "Journey Completed"
            btnNextStop.isEnabled = false
        }
    }

    // Updates the distance left in either km or miles.
    private fun updateDistanceUI() {
        val distanceLeft = totalDistance - distanceCovered
        val distanceText = if (isKm) {
            "${distanceLeft.format(2)} km"
        } else {
            "${(distanceLeft * 0.621371).format(2)} miles"
        }
        val distanceCov = if (isKm) {
            "${distanceCovered.format(2)} km"
        } else {
            "${(distanceCovered * 0.621371).format(2)} miles"
        }
        tvDistance.text = "Distanc Left: $distanceText"

        tvDistanceCovered.text = "Distance Covered: $distanceCov"
    }

    // Updates the progress bar based on the number of stops reached.
    private fun updateProgressBar() {
        val progress = if (stopsList.isNotEmpty()) (currentStopIndex * 100) / stopsList.size else 0
        progressBar.progress = progress
    }
}
