package com.example.android.technicaltest

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.technicaltest.model.DataEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_list_item.view.*
import java.util.*


class UserListAdapter(private val list: List<DataEntity>?) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.data_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        if (list.isNullOrEmpty()) {
            return 0
        } else {
            return list.size
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var data: DataEntity? = null

        fun bind(data: DataEntity) {
            this.data = data
            view.welcomeMessage.text = view.context.getString(
                R.string.welcome_message,
                data.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                data.firstName,
                data.lastName
            )
            Picasso
                .with(view.context)
                .load(data.picture)
                .resize(170, 170)
                .centerCrop()
                .into(view.profilePic)
        }

        init {
            v.setOnClickListener(this)
        }

        companion object {
            private val USER_KEY = "USER_ID"
        }

        override fun onClick(p0: View?) {
            val context = itemView.context
            val showDataIntent = Intent(context, ShowUserActivity::class.java)
            showDataIntent.putExtra(USER_KEY, data?.id)
            context.startActivity(showDataIntent)
        }

    }

}
