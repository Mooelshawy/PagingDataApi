package com.omran.pagingdataapi.pageing

import android.util.Log
import androidx.paging.PagingSource
import com.omran.pagingdataapi.model.Passenger
import com.omran.pagingdataapi.remote.ApiServices

class PassengersDataSource(private val api:ApiServices) : PagingSource<Int, Passenger>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = api.getPassengersData(nextPageNumber)

            Log.d("TAG data source ", "load: ${response.body()}")
            LoadResult.Page(
                data = response.body()!!.data,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.body()!!.totalPages) nextPageNumber + 1 else null
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}