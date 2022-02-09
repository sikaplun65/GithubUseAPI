package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ActivityDetailUserBinding
import com.sikaplun.gb.kotlin.githubuseapi.ui.adapter.ReposListAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    private val viewModel by lazy { ViewModelProvider(this).get(DetailUserActivityModel::class.java) }
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

                    Glide.with(this@DetailUserActivity)
                        .load(detailedResponseAboutUser.avatarUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(profileImageView)
                }
            }
        }
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