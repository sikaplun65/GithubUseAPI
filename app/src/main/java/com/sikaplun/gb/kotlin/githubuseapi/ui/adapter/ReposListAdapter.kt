package com.sikaplun.gb.kotlin.githubuseapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserRepository
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ListItemRepoBinding
import javax.inject.Inject

class ReposListAdapter: RecyclerView.Adapter<ReposListAdapter.ReposViewHolder>() {

    private val reposList = ArrayList<DetailUserRepository>()

    fun setReposList(repos: ArrayList<DetailUserRepository>) {
        reposList.clear()
        reposList.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = ListItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(reposList[position])
    }

    override fun getItemCount(): Int = reposList.size


    inner class ReposViewHolder(private val binding: ListItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: DetailUserRepository) {
            binding.apply {
                repoNameTextView.text = repo.name
                repoDescriptionTextView.text = repo.description
                repoLanguageTextView.text = repo.language
            }
        }
    }
}