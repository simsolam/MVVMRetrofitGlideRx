package com.example.mvvmkotlin.viewmodels

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observer
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlin.network.BookListModel
import com.example.mvvmkotlin.network.RetroInstance
import com.example.mvvmkotlin.network.RetroService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel: ViewModel() {
    var bookList:MutableLiveData<BookListModel> = MutableLiveData()

    fun getBookListObserver():MutableLiveData<BookListModel>{
        return bookList
    }

    fun makeApiCall(query:String){
        val retroInstance=RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getBookListFromApi(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    private fun getBookListObserverRx(): Observer<BookListModel>{
        return object: Observer<BookListModel>{

            override fun onComplete() {
                //hide progress indicator
            }

            override fun onError(e: Throwable) {
                bookList.postValue(null)
            }

            override fun onNext(t: BookListModel) {
                bookList.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
                //start progress indicator
            }


        }
    }
}