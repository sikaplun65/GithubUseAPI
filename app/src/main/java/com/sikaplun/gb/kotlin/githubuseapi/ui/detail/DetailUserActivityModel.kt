package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sikaplun.gb.kotlin.githubuseapi.data.api.RetrofitClient
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserRepository
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.DetailUserRepositoryRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivityModel: ViewModel() {

    private val user = MutableLiveData<DetailUserResponse>()
    private val repos = MutableLiveData<ArrayList<DetailUserRepository>>()
    private val userDetail by lazy { DetailUserRepositoryRequest() }

    private var compositeDisposable = CompositeDisposable()

    fun findUserDetail(username: String){
        userDetail.findUserDetail(username)
        userDetail.findUserRepos(username)
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        compositeDisposable.add( userDetail.getUserDetail().subscribeBy(
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