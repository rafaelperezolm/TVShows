package com.ironbit.tvshows.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironbit.tvshows.data.Repository
import com.ironbit.tvshows.domain.detail.DetailItem
import com.ironbit.tvshows.framework.common.utils.toDetailItemList
import kotlinx.coroutines.launch

// ViewModel for the list screen fragment
class ListViewModel(
    private val repository: Repository
) : ViewModel() {

    // Handle the obtained schedule data
    private val _schedule = MutableLiveData<List<DetailItem>>()
    val schedule: LiveData<List<DetailItem>>
        get() = _schedule

    // Calls the services for the schedule data
    fun getSchedule(
        country: String,
        date: String
    ) {
        viewModelScope.launch {
            val response = repository.getSchedule(
                country = country,
                date = date
            )
            _schedule.value = response.toDetailItemList()
        }
    }

    // Calls the services for the search data
    fun getSearch(
        query: String
    ) {
        viewModelScope.launch {
            val response = repository.getSearch(
                query = query
            )
            _schedule.value = response.toDetailItemList()
        }
    }

}