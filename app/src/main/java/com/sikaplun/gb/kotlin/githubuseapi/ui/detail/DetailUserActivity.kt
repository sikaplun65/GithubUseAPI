package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ActivityDetailUserBinding
import com.sikaplun.gb.kotlin.githubuseapi.ui.adapter.ReposListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    private val viewViewModel: DetailUserActivityViewModel by viewModel()

    private lateinit var reposAdapter: ReposListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        initReposAdapter()
        initReposRecyclerView()
        showUserDetails()
        showUserRepository()

        if (username != null) {
            viewViewModel.findUserDetail(username)
        }
    }

    private fun showUserRepository() {
        viewViewModel.getRepos().observe(this) { listUserRepository ->
            if (listUserRepository != null) {
                reposAdapter.setReposList(listUserRepository)
            }
        }
    }

    private fun showUserDetails() {
        viewViewModel.getUserDetail().observe(this) { detailedResponseAboutUser ->
            if (detailedResponseAboutUser != null) {
                binding.apply {
                    nameTextView.text = detailedResponseAboutUser.name
                    userNameTextView.text = detailedResponseAboutUser.login
                    showAvatarImage(detailedResponseAboutUser)

                }
            }
        }
    }

    private fun showAvatarImage(detailedResponseAboutUser: DetailUserResponse) {
        Glide.with(this@DetailUserActivity)
            .load(detailedResponseAboutUser.avatarUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.profileImageView)
    }


    private fun initReposRecyclerView() {
        binding.apply {
            reposRecyclerView.layoutManager = LinearLayoutManager(this@DetailUserActivity)
            reposRecyclerView.setHasFixedSize(true)
            reposRecyclerView.adapter = reposAdapter

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initReposAdapter() {
        reposAdapter = ReposListAdapter()
        reposAdapter.notifyDataSetChanged()
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

}