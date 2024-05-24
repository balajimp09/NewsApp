package com.sur.newsapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.sur.newsapp.BR

class NewsDataAdapter<T>(
    val layoutId: Int,
    val br: Int = -1,
    var list: ArrayList<T>? = null,
    var clickListener: ((View, Int) -> Unit)? = null,
    val activityContext : Activity? =null
) : RecyclerView.Adapter<NewsDataAdapter<T>.ViewHolder>() {

    override fun getItemCount() = list?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (br != -1) holder.binding.setVariable(br, list?.get(position))

        holder.binding.setVariable(
            BR.click,
            View.OnClickListener { v -> clickListener?.invoke(v, position) })
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}