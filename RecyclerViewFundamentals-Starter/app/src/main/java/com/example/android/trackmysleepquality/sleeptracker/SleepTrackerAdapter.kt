package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight
import java.util.zip.Inflater

class SleepTrackerAdapter: RecyclerView.Adapter<TextViewHolder>(){
    var logs = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.textview_item, parent, false) as TextView
        return TextViewHolder(view)
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.textView.text = logs[position].sleepQuality.toString()
    }

}

class TextViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
