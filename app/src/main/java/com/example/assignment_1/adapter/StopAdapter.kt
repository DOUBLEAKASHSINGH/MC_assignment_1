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
        val tvStopName: TextView = itemView.findViewById(R.id.tv_stop_name)
        val tvVisaInfo: TextView = itemView.findViewById(R.id.tv_visa_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stop, parent, false)
        return StopViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StopViewHolder, position: Int) {
        val stop = stops[position]
        holder.tvStopName.text = stop.name
        holder.tvVisaInfo.text = "Visa Requirement: ${stop.visaRequirement}"
    }

    override fun getItemCount(): Int = stops.size
}
