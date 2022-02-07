package com.sikaplun.gb.kotlin.githubuseapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ItemUserBinding

class GitHubUserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private val list = ArrayList<User>()

    fun setList(users: ArrayList<User>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}