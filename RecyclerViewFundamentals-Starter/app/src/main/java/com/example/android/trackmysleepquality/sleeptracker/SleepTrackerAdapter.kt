package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepTrackerAdapter: RecyclerView.Adapter<SleepTrackerAdapter.ViewHolder>(){
    var logs = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = logs[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(view: View): RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.sleep_image)
        val date = view.findViewById<TextView>(R.id.sleep_date)
        val quality = view.findViewById<TextView>(R.id.sleep_quality)

        fun bind(item: SleepNight) {
            setTextViewDisplay(this, item)
            setImageViewDisplay(this, item)
        }

        private fun setTextViewDisplay(holder: ViewHolder, item: SleepNight) {
            val resources = holder.itemView.context.resources
            holder.date.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, resources)
            holder.quality.text = convertNumericQualityToString(item.sleepQuality, resources)
        }

        private fun setImageViewDisplay(holder: ViewHolder, item: SleepNight) {
            holder.image.setImageResource((when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            }))
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.list_item_night_sleep, parent, false)
                return ViewHolder(view)
            }
        }
    }

}
