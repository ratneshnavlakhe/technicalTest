package com.example.android.technicaltest

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.technicaltest.model.DataEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_list_item.view.*


class DataAdapter(private val list: List<DataEntity>) :
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

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var data: DataEntity? = null

        fun bind(data: DataEntity) {
            this.data = data
            view.firstName.text = data.firstName
            view.lastName.text = data.lastName
            view.userTitle.text = data.title
            Picasso.with(view.context).load(data.picture).into(view.profilePic)
        }

        init {
            v.setOnClickListener(this)
        }

        companion object {
            private val USER_KEY = "USER_ID"
        }

        override fun onClick(p0: View?) {
            val context = itemView.context
            val showDataIntent = Intent(context, ShowDataActivity::class.java)
            showDataIntent.putExtra(USER_KEY, data?.id)
            context.startActivity(showDataIntent)
        }

    }

}
