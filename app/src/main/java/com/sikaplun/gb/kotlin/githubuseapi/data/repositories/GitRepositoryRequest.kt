package com.sikaplun.gb.kotlin.githubuseapi.data.repositories

import android.util.Log
import com.sikaplun.gb.kotlin.githubuseapi.data.api.ApiGitHub
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import com.sikaplun.gb.kotlin.githubuseapi.data.model.UserResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitRepositoryRequest(private val apiGitHub: ApiGitHub) {

    private val listUsers = BehaviorSubject.create<ArrayList<User>>()

    fun findUsers(query: String) {

        apiGitHub.getSearchUsersRF(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.onNext(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getSearchUsers(): Observable<ArrayList<User>> = listUsers
}