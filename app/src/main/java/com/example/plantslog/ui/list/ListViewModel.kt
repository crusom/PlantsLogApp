package com.example.plantslog.ui.list


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.plantslog.data.TrefleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(repository: TrefleRepository): ViewModel() {


    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val data = currentQuery.switchMap { queryString ->
        Log.d("QUERY", queryString)
        repository.getResults(queryString).cachedIn(viewModelScope)
    }

    fun searchPlants(query: String) {
        currentQuery.value = query
    }


//    val data = repository.getResults("").cachedIn(viewModelScope)

    companion object {
        private const val DEFAULT_QUERY = ""
    }

}