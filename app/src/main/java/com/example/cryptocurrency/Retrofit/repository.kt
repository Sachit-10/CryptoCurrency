package com.example.cryptocurrency.Retrofit

import com.example.cryptocurrency.DataClass.data_class_list
import com.example.cryptocurrency.DataClass.data_class_live
import retrofit2.Response
import javax.inject.Inject

class repository @Inject constructor(private val apiInterface: api_interface) {


    suspend fun getdetail(): Response<data_class_live> {
        return apiInterface.getdetails()

    }

    suspend fun getdetailedinfo(): Response<data_class_list>{
        return apiInterface.getdetailedinfo()
    }

}