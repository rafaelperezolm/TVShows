package com.ironbit.tvshows.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironbit.tvshows.data.Repository
import com.ironbit.tvshows.domain.cast.CastResponseItem
import com.ironbit.tvshows.domain.show.ShowResponse
import kotlinx.coroutines.launch

// ViewModel for the detail screen fragment
class DetailViewModel(
    private val repository: Repository
) : ViewModel() {

    // Handle the obtained show data
    private val _show = MutableLiveData<ShowResponse>()
    val show: LiveData<ShowResponse>
        get() = _show

    // Handle the obtained cast data
    private val _cast = MutableLiveData<List<CastResponseItem>>()
    val cast: LiveData<List<CastResponseItem>>
        get() = _cast

    // Calls the services for the show data
    fun getShow(
        id: Int
    ) {
        viewModelScope.launch {
            val response = repository.getShow(
                id = id
            )
            _show.value = response
        }
    }

    // Calls the services for the cast data
    fun getCast(
        id: Int
    ) {
        viewModelScope.launch {
            val response = repository.getCast(
                id = id
            )
            _cast.value = response
        }
    }

}