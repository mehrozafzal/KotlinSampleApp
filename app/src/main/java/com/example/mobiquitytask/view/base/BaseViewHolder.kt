package com.example.mobiquitytask.view.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder(itemView: View?) : ViewHolder(itemView!!) {
    abstract fun onBind(position: Int)
}