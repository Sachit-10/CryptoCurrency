package com.example.cryptocurrency.Retrofit

import com.example.cryptocurrency.DataClass.data_class_list
import com.example.cryptocurrency.DataClass.data_class_live
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton


interface api_interface {

    @GET("live?access_key=${constants.API_ID}")
    suspend fun getdetails() : Response<data_class_live>

    @GET("list?access_key=${constants.API_ID}")
    suspend fun getdetailedinfo(): Response<data_class_list>


}