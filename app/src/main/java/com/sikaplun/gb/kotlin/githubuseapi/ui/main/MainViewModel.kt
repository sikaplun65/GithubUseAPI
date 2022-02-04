package com.sikaplun.gb.kotlin.githubuseapi.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sikaplun.gb.kotlin.githubuseapi.data.api.RetrofitClient
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel : ViewModel() {

    private val listUsers = BehaviorSubject.create<ArrayList<User>>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance.getSearchUsers(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                listUsers.onNext(response.items)
            }, { throwable ->
                Log.d("Failure", throwable.message.toString())
            })
    }

    val getSearchUsers: Observable<ArrayList<User>>
        get() = listUsers

}