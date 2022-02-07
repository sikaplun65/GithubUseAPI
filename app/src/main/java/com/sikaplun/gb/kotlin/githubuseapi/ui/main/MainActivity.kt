package com.sikaplun.gb.kotlin.githubuseapi.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sikaplun.gb.kotlin.githubuseapi.adapter.GitHubUserAdapter
import com.sikaplun.gb.kotlin.githubuseapi.databinding.ActivityMainBinding

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


        viewModel.getSearchUsers().observe(this, {
            if (it !== null) {
                adapter.setList(it)
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
