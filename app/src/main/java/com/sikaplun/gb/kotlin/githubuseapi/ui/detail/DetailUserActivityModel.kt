package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sikaplun.gb.kotlin.githubuseapi.data.api.RetrofitClient
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import com.sikaplun.gb.kotlin.githubuseapi.data.model.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivityModel : ViewModel() {

    val user = MutableLiveData<DetailUserResponse>()
    val repos = MutableLiveData<ArrayList<UserRepository>>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }


    fun setUserRepos(username: String) {
        RetrofitClient.apiInstance
            .getRepo(username)
            .enqueue(object : Callback<ArrayList<UserRepository>> {
                override fun onResponse(
                    call: Call<ArrayList<UserRepository>>,
                    response: Response<ArrayList<UserRepository>>
                ) {
                    if (response.isSuccessful) {
                        repos.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserRepository>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }

    fun getRepos(): LiveData<ArrayList<UserRepository>> {
        return repos
    }
}