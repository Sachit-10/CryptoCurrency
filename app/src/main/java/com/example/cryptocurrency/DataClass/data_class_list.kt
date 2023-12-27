package com.example.cryptocurrency.DataClass

data class data_class_list(
    val success: Boolean,
//    val crypto: Map<String, cryptoinfo,
    val crypto: Map<String,cryptoinfo>,
    val fiat: Map<String, String>
)
