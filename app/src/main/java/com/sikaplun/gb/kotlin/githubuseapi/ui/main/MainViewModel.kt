package com.sikaplun.gb.kotlin.githubuseapi.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.GitRepositoryRequest
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainViewModel(
    private val repos: GitRepositoryRequest
) : ViewModel() {

    private val listUsers by lazy { MutableLiveData<ArrayList<User>>()}
    private lateinit var disposable: Disposable

    fun findUsers(query: String) {
        repos.findUsers(query)
    }

    fun getFoundUsers(): MutableLiveData<ArrayList<User>> {
        disposable = repos.getSearchUsers().subscribeBy(
            onNext = {
                listUsers.postValue(it)
            },
            onError = { it.printStackTrace() },
            onComplete = { Log.i("Complete", "onCompleted: COMPLETED!") }
        )
        return listUsers
    }

    override fun onCleared() {
        if (!disposable.isDisposed){
            disposable.dispose()
        }
        super.onCleared()
    }
}