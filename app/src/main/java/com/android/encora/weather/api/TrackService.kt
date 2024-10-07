package com.android.encora.weather.api

import retrofit2.Response
import retrofit2.http.GET
import com.android.encora.weather.api.data.Result
/*
Track Service is the interface for API services
*/
interface TrackService {

    @GET("search?term=star&country=au&media=movie&;all")
    suspend fun getTrackFromApi(): Response<Result>

}