package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ActivityDetailUserBinding
import com.sikaplun.gb.kotlin.githubuseapi.ui.adapter.ReposListAdapter

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserActivityModel
    private lateinit var reposAdapter: ReposListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        initReposAdapter()
        initReposRecyclerView()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailUserActivityModel::class.java
        )
        if (username != null) {
            viewModel.setUserDetail(username)
            viewModel.setUserRepos(username)
        }

        viewModel.getUserDetail().observe(this, { detailedResponseAboutUser ->
            if (detailedResponseAboutUser != null) {
                binding.apply {
                    nameTextView.text = detailedResponseAboutUser.name
                    userNameTextView.text = detailedResponseAboutUser.login

                    Glide.with(this@DetailUserActivity)
                        .load(detailedResponseAboutUser.avatarUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(profileImageView)
                }
            }
        })

        viewModel.getRepos().observe(this, { listUserRepository ->
            if (listUserRepository != null) {
                reposAdapter.setReposList(listUserRepository)
            }
        })

    }

    private fun initReposRecyclerView() {
        binding.apply {
            reposRecyclerView.layoutManager = LinearLayoutManager(this@DetailUserActivity)
            reposRecyclerView.setHasFixedSize(true)
            reposRecyclerView.adapter = reposAdapter

        }
    }

    private fun initReposAdapter() {
        reposAdapter = ReposListAdapter()
        reposAdapter.notifyDataSetChanged()
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
}