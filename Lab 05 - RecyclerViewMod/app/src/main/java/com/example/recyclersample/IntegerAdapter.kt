/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.recyclersample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class IntegerAdapter(val intList: IntArray) :
    RecyclerView.Adapter<IntegerAdapter.IntegerViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class IntegerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val integerTextView: TextView = itemView.findViewById(R.id.integerText)

        fun bind(value: Int) {
            integerTextView.text = value.toString()
            val color = ContextCompat.getColor(integerTextView.context, R.color.colorPrimary);
            integerTextView.setTextColor(color)
        }
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return intList.size
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntegerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.integer_item, parent, false)

        return IntegerViewHolder(view)
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: IntegerViewHolder, position: Int) {
        holder.bind(intList[position])
    }
}