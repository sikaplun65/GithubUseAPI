package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sikaplun.gb.kotlin.githubuseapi.app.App
import com.sikaplun.gb.kotlin.githubuseapi.app.appComponent
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ActivityDetailUserBinding
import com.sikaplun.gb.kotlin.githubuseapi.ui.adapter.ReposListAdapter
import javax.inject.Inject

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    @Inject
    lateinit var detailUserActivityModelFactory: DetailUserActivityModelFactory

    private val viewModel: DetailUserActivityModel by viewModels{detailUserActivityModelFactory}

    private lateinit var reposAdapter: ReposListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appComponent.inject(this)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        initReposAdapter()
        initReposRecyclerView()
        showUserDetails()
        showUserRepository()

        if (username != null) {
            viewModel.findUserDetail(username)
        }
    }

    private fun showUserRepository() {
        viewModel.getRepos().observe(this) { listUserRepository ->
            if (listUserRepository != null) {
                reposAdapter.setReposList(listUserRepository)
            }
        }
    }

    private fun showUserDetails() {
        viewModel.getUserDetail().observe(this) { detailedResponseAboutUser ->
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