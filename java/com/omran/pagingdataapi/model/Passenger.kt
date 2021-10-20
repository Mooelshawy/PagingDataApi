package com.omran.pagingdataapi.model

data class Passenger (

    val _id: String,
    val name: String,
    val trips: Long,
    val airline: List<Airline> ,
    val _v: Long
)