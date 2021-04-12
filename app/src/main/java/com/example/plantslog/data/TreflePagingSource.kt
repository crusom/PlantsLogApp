package com.example.plantslog.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.plantslog.api.TrefleApi
import okio.ByteString.Companion.toByteString
import retrofit2.HttpException
import java.io.IOException

private const val TREFLE_STARTING_PAGE_INDEX = 1

class TreflePagingSource(
    private val trefleApi: TrefleApi,
    private val query: String?
    ): PagingSource<Int, PlantData.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlantData.Data> {
        val position = params.key ?: TREFLE_STARTING_PAGE_INDEX


        return try {
            Log.d("QUERY", query.toString())
            val response = if(query != ""){
                trefleApi.searchPlants(query = query!!, page = position)
            } else{
                trefleApi.getPlants(page = position)
            }

            Log.d("PLANTSSS", response.toString())
            LoadResult.Page(
                data = response.data,
                prevKey = if(position == TREFLE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if(response.data.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            Log.d("EXCEPTION", exception.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.d("EXCEPTION", exception.toString())
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlantData.Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }
}