package com.example.plantslog.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.plantslog.api.TrefleApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrefleRepository @Inject constructor(private val trefleApiService: TrefleApi) {

    fun getResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TreflePagingSource(trefleApiService, query)}
        ).liveData

}