package com.example.cryptocurrency.Retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object constants {

    const val API_ID:String = "8353b29c775f55ff5c336828c95527a5"
    const val baseurl:String = "http://api.coinlayer.com/"



    fun isnetworkavailable(context: Context):Boolean {

        var connectingmanager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectingmanager.activeNetwork ?: return false
        val activenetwork = connectingmanager.getNetworkCapabilities(network) ?:return false

        return  when {
            activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            else -> false
        }

    }


}