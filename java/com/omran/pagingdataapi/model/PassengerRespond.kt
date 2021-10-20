package com.omran.pagingdataapi.model

data class PassengerRespond (
    val totalPassengers: Long,
    val totalPages: Long,
    val data: List<Passenger>
)
