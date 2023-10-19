package com.rival.muslimnews.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rival.muslimnews.data.model.NewsResponse
import com.rival.muslimnews.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    private val _commonMuslimNews = MutableLiveData<NewsResponse>()
    val commonMuslimNews: LiveData<NewsResponse> = _commonMuslimNews

    private val _aboutAlQuranNews = MutableLiveData<NewsResponse>()
    val aboutAlQuranNews: LiveData<NewsResponse> = _aboutAlQuranNews

    private val _alJazeeraNews = MutableLiveData<NewsResponse>()
    val alJazeeraNews: LiveData<NewsResponse> = _alJazeeraNews

    private val _warningForMuslimNews = MutableLiveData<NewsResponse>()
    val warningForMuslimNews: LiveData<NewsResponse> = _warningForMuslimNews

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private val _searchNews = MutableLiveData<NewsResponse>()
    val searchNews: LiveData<NewsResponse> = _searchNews

    fun getCommonMuslimNews() {
        _isLoading.value = true
        ApiClient.provideApiService().getCommonMuslimNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.i("Repository", "onResponse: ${response.body()}")
                            _commonMuslimNews.postValue(response.body())
                        }
                    } else {
                        _isLoading.value = false
                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code " + response.code()
                        )
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure: " + t.localizedMessage)
                }
            })
    }

    fun getAboutAlQuranNews() {
        _isLoading.value = true
        ApiClient.provideApiService().getAlQuranNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.i("Repository", "onResponse: ${response.body()}")
                            _aboutAlQuranNews.postValue(response.body())
                        }
                    } else {
                        _isLoading.value = false

                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code " + response.code()
                        )
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure: " + t.localizedMessage)
                }
            })
    }

    fun getAlJazeraNews() {
        _isLoading.value = true
        ApiClient.provideApiService().getAlJazeeraNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.i("Repository", "onResponse: ${response.body()}")
                            _alJazeeraNews.postValue(response.body())
                        }
                    } else {
                        _isLoading.value = false

                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code " + response.code()
                        )
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure: " + t.localizedMessage)
                }
            })
    }

    fun getwarningNews() {
        _isLoading.value = true
        ApiClient.provideApiService().getWarningForMuslimNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.i("Repository", "onResponse: ${response.body()}")
                            _warningForMuslimNews.postValue(response.body())
                        }
                    } else {
                        _isLoading.value = false

                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code " + response.code()
                        )
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure: " + t.localizedMessage)
                }
            })
    }

    fun getSearchNews(q: String) {
        _isLoading.value = true
        ApiClient.provideApiService().getSearchNews(q)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.i("Repository", "onResponse: ${response.body()}")
                            _searchNews.postValue(response.body())
                        }
                    } else {
                        _isLoading.value = false

                        Log.e(
                            "Repository",
                            "onResponse: Call error with HTTP status code " + response.code()
                        )
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure: " + t.localizedMessage)
                }
            })
    }
}