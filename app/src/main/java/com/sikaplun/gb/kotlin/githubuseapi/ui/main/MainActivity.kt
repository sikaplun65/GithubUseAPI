package com.sikaplun.gb.kotlin.githubuseapi.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import com.sikaplun.gb.kotlin.githubuseapi.ui.adapter.GitHubUserAdapter
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ActivityMainBinding
import com.sikaplun.gb.kotlin.githubuseapi.ui.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GitHubUserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initViewModel()
        initRecyclerView()
        initSearchButton()
        initQueryEditText()


        viewModel.getSearchUsers().observe(this, { listUsers ->
            if (listUsers !== null) {
                adapter.setList(listUsers)
                showLoading(false)
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
    }

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
            viewModel.setSearchUsers(query)
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
