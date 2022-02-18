package com.sikaplun.gb.kotlin.githubuseapi.data.repositories

import android.util.Log
import com.sikaplun.gb.kotlin.githubuseapi.data.api.RetrofitClient
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserRepository
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserRepositoryRequest {

    private val user = BehaviorSubject.create<DetailUserResponse>()
    private val repos = BehaviorSubject.create<ArrayList<DetailUserRepository>>()

    fun findUserDetail(username: String){
        RetrofitClient.apiInstance.getUserDetailRF(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful){
                        user.onNext(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun findUserRepos(username: String){
        RetrofitClient.apiInstance.getRepoRF(username)
            .enqueue(object : Callback<ArrayList<DetailUserRepository>> {
                override fun onResponse(
                    call: Call<ArrayList<DetailUserRepository>>,
                    response: Response<ArrayList<DetailUserRepository>>
                ) {
                    if (response.isSuccessful){
                        repos.onNext(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<DetailUserRepository>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getUserDetail(): Observable<DetailUserResponse> = user
    fun getRepos(): Observable<ArrayList<DetailUserRepository>> = repos
}