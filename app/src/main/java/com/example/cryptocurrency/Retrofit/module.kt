package com.example.cryptocurrency.Retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


    @InstallIn(SingletonComponent::class)
    @Module
    class module {

        @Singleton
        @Provides
        fun providesRetrofit() : Retrofit {
            return Retrofit.Builder().baseUrl(constants.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        fun retrofit_api(retrofit: Retrofit): api_interface {
            return retrofit.create(api_interface::class.java)
        }
    }
