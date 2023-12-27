package com.example.cryptocurrency.Retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.DataClass.cryptoinfo
import com.example.cryptocurrency.DataClass.data_class_list
import com.example.cryptocurrency.DataClass.data_class_live
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class mainviewmodel @Inject constructor(private val repository: repository) : ViewModel() {

    val productsLiveData: MutableLiveData<Response<data_class_live>> = MutableLiveData()
    val productdetailedinfo: MutableLiveData<Response<data_class_list>> = MutableLiveData()
//      val cryptoData: MutableLiveData<Response<Map<String, Pair<Double, cryptoinfo>>>> = MutableLiveData()


    fun getProductViewModel() {
        viewModelScope.launch {

            productsLiveData.value = repository.getdetail()

        }
    }

    fun getdetailedinfo(){
        viewModelScope.launch {

            productdetailedinfo.value = repository.getdetailedinfo()

        }
    }

//    private val viewModelScope = CoroutineScope(Dispatchers.Default)
//    suspend fun fetchCryptoData() {
//        viewModelScope.launch {
//
//            val rates = repository.getdetail().body()?.rates
//
//            rates?.keys?.forEach{
//                sympbol ->
//
//                viewModelScope.launch(Dispatchers.IO){
//                    val result = repository.getdetailedinfo().body()?.crypto
//                    val cry = result?.symbol
//                    val r = rates[sympbol]
//
//                }
//            }
//
//        }
//
//        }


}