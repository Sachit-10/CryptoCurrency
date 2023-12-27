package com.example.cryptocurrency.DataClass

import com.google.gson.JsonArray

data class data_class_live(
    val privacy: String,
    val rates: Map<String,Double>,
    val success: Boolean,
    val target: String,
    val terms: String,
    val timestamp: Int
)