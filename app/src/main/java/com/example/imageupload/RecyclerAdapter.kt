package com.example.imageupload

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<RecyclerAdapter.UsersHolder>()  {

    class UsersHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        var nameView: TextView
        var phoneView: TextView
        var emailView: TextView


        init {
            v.setOnClickListener(this)

            nameView = view.findViewById(R.id.name)
            phoneView = view.findViewById(R.id.phone)
            emailView = view.findViewById(R.id.email)

        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            //private val USER_KEY = "PHOTO"
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UsersHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return UsersHolder(view)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.nameView.text = users[position].name
        holder.phoneView.text = users[position].phone
        holder.emailView.text = users[position].email

    }

    override fun getItemCount() = users.size


}

