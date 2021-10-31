package com.example.android.technicaltest

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.technicaltest.model.DataListEntity
import kotlinx.android.synthetic.main.data_list_item.view.*


class DataAdapter(private val list: List<DataListEntity>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.data_list_item, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var data: DataListEntity? = null

        fun bind(data: DataListEntity) {
            this.data = data
            view.helloworld.text = data.firstName
        }

    }

}