package com.demo.data_risky.model.response

import com.demo.data_risky.model.response.Location
import com.demo.data_risky.model.response.Origin

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)