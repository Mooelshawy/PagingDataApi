package com.omran.pagingdataapi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omran.pagingdataapi.remote.ApiServices

@Suppress("UNCHECKED_CAST")
class PassengersViewModelFactory : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }
}