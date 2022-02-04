package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sikaplun.gb.kotlin.githubuseapi.data.api.RetrofitClient
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import com.sikaplun.gb.kotlin.githubuseapi.data.model.UserRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class DetailUserActivityModel : ViewModel() {

    private val user = BehaviorSubject.create<DetailUserResponse>()
    private val repos = BehaviorSubject.create<ArrayList<UserRepository>>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance.getUserDetail(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userDetail ->
                user.onNext(userDetail)
            }, { throwable ->
                Log.d("Failure", throwable.message.toString())
            })
    }


    fun setUserRepos(username: String) {
        RetrofitClient.apiInstance.getRepo(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repo ->
                repos.onNext(repo)
            }, { throwable ->
                Log.d("Failure", throwable.message.toString())
            })
    }

    val getUserDetail: Observable<DetailUserResponse>
        get() = user

    val getRepos: Observable<ArrayList<UserRepository>>
        get() = repos

}