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
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val viewModel by lazy { ViewModelProvider(this).get(DetailUserActivityModel::class.java) }
    private lateinit var reposAdapter: ReposListAdapter

    private lateinit var disposeUserDetail: Disposable
    private lateinit var disposeRepos: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        initReposAdapter()
        initReposRecyclerView()

        if (username != null) {
            viewModel.setUserDetail(username)
            viewModel.setUserRepos(username)
        }

        disposeRepos = viewModel.getRepos.subscribeBy(
            onNext = { listUserRepository ->
                reposAdapter.setReposList(listUserRepository)
            },
            onError = { it.printStackTrace() },
            onComplete = { Log.i("Complete", "onCompleted: COMPLETED!") }
        )

        disposeUserDetail = viewModel.getUserDetail.subscribeBy(
            onNext = { detailedResponseAboutUser ->
                    binding.apply {
                        nameTextView.text = detailedResponseAboutUser.name
                        userNameTextView.text = detailedResponseAboutUser.login

                        Glide.with(this@DetailUserActivity)
                            .load(detailedResponseAboutUser.avatarUrl)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .centerCrop()
                            .into(profileImageView)
                    }
            },
            onError = { it.printStackTrace() },
            onComplete = { Log.i("Complete", "onCompleted: COMPLETED!") }
        )
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

    override fun onDestroy() {
        if (!disposeRepos.isDisposed) {
            disposeRepos.dispose()
        }
        if (!disposeUserDetail.isDisposed) {
            disposeUserDetail.dispose()
        }
        super.onDestroy()
    }
}