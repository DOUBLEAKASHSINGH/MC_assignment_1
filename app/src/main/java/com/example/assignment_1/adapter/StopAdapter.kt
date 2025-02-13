package com.example.assignment_1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_1.R
import com.example.assignment_1.Stop

class StopsAdapter(private val stops: List<Stop>) : RecyclerView.Adapter<StopsAdapter.StopViewHolder>() {

    class StopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StopViewHolder, position: Int) {
        val stop = stops[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = stops.size
}
