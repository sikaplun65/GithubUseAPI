package com.sikaplun.gb.kotlin.githubuseapi.data.repositories

import android.util.Log
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import com.sikaplun.gb.kotlin.githubuseapi.data.model.UserResponse
import com.sikaplun.gb.kotlin.githubuseapi.di.module.RetrofitModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GitRepositoryRequest @Inject constructor(private val retrofit: RetrofitModule) {

    private val listUsers = BehaviorSubject.create<ArrayList<User>>()

    fun findUsers(query: String) {

        retrofit.provideApiInstance()
            .getSearchUsersRF(query)
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