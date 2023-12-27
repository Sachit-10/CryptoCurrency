package com.example.cryptocurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.Adapter.item_adapter
import com.example.cryptocurrency.Retrofit.constants
import com.example.cryptocurrency.Retrofit.mainviewmodel
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.google.gson.JsonArray
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var binding:ActivityMainBinding?=null
    val viewmodel: mainviewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //setting up the toolbar
        setSupportActionBar(binding?.toolbar)


        //checking internet connection
        if (constants.isnetworkavailable(this)) {
            Toast.makeText(this, "You are connected to the internet", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "You are not connected to the internet", Toast.LENGTH_SHORT).show()
        }



        //function for calling the api
        callingtheapi()


        // when swipe up is used
        binding?.swipeup?.setOnRefreshListener(){
            callingtheapi()
            binding?.swipeup?.isRefreshing = false
        }

    }




// fetching the data
    private fun callingtheapi(){

        //updating the time
        val lastrefreshtime = String.format("Last Refresh Time: ${converttime(System.currentTimeMillis())}")
        binding?.lastrefresh?.text = lastrefreshtime
        countdownimer()


        //calling the api to fetch live data
        viewmodel.getProductViewModel()
        viewmodel.productsLiveData.observe(this, Observer {

            if(it.isSuccessful) {
                val ratesmap = it.body()!!.rates

                // calling list api to get remaining info
                viewmodel.getdetailedinfo()
                viewmodel.productdetailedinfo.observe(this, Observer{result ->

                    if(result.isSuccessful){

                        val allinfomap = result.body()!!.crypto
                        binding?.itemRv?.layoutManager = LinearLayoutManager(this)
                        binding?.itemRv?.adapter = item_adapter(this, ratesmap, allinfomap.toList())

                    }
                    else {
                        Toast.makeText(this , "Failed" , Toast.LENGTH_LONG).show()
                    }

                })
            }
            else {
                Toast.makeText(this , "Failed" , Toast.LENGTH_LONG).show()
            }

        })
    }



// function fro timer
    private fun countdownimer(){

        object : CountDownTimer(180000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli

                val timer = String.format("Auto Refresh in ${elapsedMinutes}:${elapsedSeconds}")
                binding?.autorefresh?.text = timer


                // the timer to reset the timer when swipe up feature is used in between
                binding?.swipeup?.setOnRefreshListener {
                    cancel()
                    callingtheapi()
                    Toast.makeText(this@MainActivity,"Refreshed!", Toast.LENGTH_SHORT).show()
                    binding?.swipeup?.isRefreshing = false
                }

            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Refreshed!", Toast.LENGTH_SHORT).show()
                callingtheapi()
            }
        }.start()
    }





    private fun converttime(timestamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return sdf.format(calendar.time)
    }
}