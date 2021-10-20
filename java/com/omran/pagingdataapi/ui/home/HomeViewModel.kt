package com.omran.pagingdataapi.ui.home


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omran.pagingdataapi.model.Passenger
import com.omran.pagingdataapi.pageing.PassengersDataSource
import com.omran.pagingdataapi.remote.ApiServices
import com.omran.pagingdataapi.remote.Builder
import kotlinx.coroutines.flow.*

class HomeViewModel: ViewModel() {
    private val api = Builder.invoke().create(ApiServices::class.java)

    fun getListData(): Flow<PagingData<Passenger>> {


        //number of page display
        /*
        Flow is api from google better than channel
        working with cold stream meaning not working until have three steps connect with them
        flows can also produce data asynchronously.


        Producer   ***    Intermediate  *** collector
        *Producer : step to produce data like a channel
         Pager is producer for produce data as stream
         *** different from produce in channel ***
         1-not working until have collector connected
         2-producer and intermediate working with them in same coroutine have observe on them
         and have there collector connected with them

        InterMediate : Step make some operation on data to make it  ready for collector
        DataSource class is intermediate


        *** to avoid paralism in working we use Buffer to put data in it ***
        1-producer and intermeidater produce data and store it in buffer
        working in split coroutine  and retrive data form it and store it

        2-collector not wating data from producer he take her data from buffer and working
        working in coroutine and make her job

        3-we have save a time not wating for data


        Collector : is a Coroutine observe on data to make a operation on it
        Coroutine is ViewModel Scope cached data in memory



        * */
        //flow builder to cached data in memory and make ViewModelScope observe on it
        return Pager(PagingConfig(pageSize = 10)) {
            //flow intermediate to make operation  on data
            PassengersDataSource(api)
        }.flow.cachedIn(viewModelScope)
    }
}