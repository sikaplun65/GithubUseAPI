package com.sikaplun.gb.kotlin.githubuseapi.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ItemUserBinding

class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.apply {
            Glide.with(itemView)
                .load(user.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(userImageView)

            userNameTextView.text = user.login
        }
    }
}