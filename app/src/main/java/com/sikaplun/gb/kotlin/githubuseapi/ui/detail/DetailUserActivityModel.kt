package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserRepository
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.DetailUserRepositoryRequest
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class DetailUserActivityModel(
    private val user: MutableLiveData<DetailUserResponse>,
    private val repos: MutableLiveData<ArrayList<DetailUserRepository>>,
    private val userDetail: DetailUserRepositoryRequest
) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    fun findUserDetail(username: String) {
        userDetail.findUserDetail(username)
        userDetail.findUserRepos(username)
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        compositeDisposable.add(
            userDetail.getUserDetail().subscribeBy(
            onNext = {
                user.postValue(it)
            },
            onError = { it.printStackTrace() },
            onComplete = { Log.i("Complete", "onCompleted: COMPLETED!") }
        ))
        return user
    }

    fun getRepos(): LiveData<ArrayList<DetailUserRepository>>{
        compositeDisposable.add( userDetail.getRepos().subscribeBy(
            onNext = {
                repos.postValue(it)
            },
            onError = { it.printStackTrace() },
            onComplete = { Log.i("Complete", "onCompleted: COMPLETED!") }
        ))
        return repos
    }

    override fun onCleared() {
        if (compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}