package com.gajanan.rocketapp.network

import com.gajanan.rocketapp.modalClass.RocketDetailResponse
import com.gajanan.rocketapp.modalClass.RocketsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApiInterface {

    @GET(Endpoints.GET_ROCKETS)
    suspend fun getAllRockets() : Response<RocketsResponse>

    @GET("${Endpoints.GET_ROCKETS}/{id}")
    suspend fun getRocketDetails(
        @Path("id") id : String
    ) : Response<RocketDetailResponse>

}