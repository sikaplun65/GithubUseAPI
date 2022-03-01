package com.sikaplun.gb.kotlin.githubuseapi.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sikaplun.gb.kotlin.githubuseapi.app.App
import com.sikaplun.gb.kotlin.githubuseapi.app.appComponent
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ActivityMainBinding
import com.sikaplun.gb.kotlin.githubuseapi.ui.adapter.GitHubUserAdapter
import com.sikaplun.gb.kotlin.githubuseapi.ui.detail.DetailUserActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: GitHubUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appComponent.inject(this)


        initAdapter()
        initRecyclerView()
        initSearchButton()
        initQueryEditText()
        showFoundUsers()

    }

    private fun showFoundUsers() {
        viewModel.getFoundUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAdapter() {
        adapter = GitHubUserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : GitHubUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }

        })

    }

    private fun initQueryEditText() {
        binding.queryEditText.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchUser()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun initSearchButton() {
        binding.searchButton.setOnClickListener {
            searchUser()
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            foundUsersRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            foundUsersRecyclerView.setHasFixedSize(true)
            foundUsersRecyclerView.adapter = adapter
        }
    }

    private fun searchUser() {
        binding.apply {
            val query = queryEditText.text.toString()
            if (query.isEmpty()) {
                return
            }
            showLoading(true)
            viewModel.findUsers(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}
