package com.audiolly

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.section_item.view.*

class SectionItem(v: View) : RecyclerView.ViewHolder(v) {
    val title: TextView = v.section_title
}