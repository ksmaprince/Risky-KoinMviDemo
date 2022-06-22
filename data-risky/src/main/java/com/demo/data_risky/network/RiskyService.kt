package com.demo.data_risky.network

import com.demo.data_risky.model.response.Character
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RiskyService {

    @GET(URL.CHARACTERS)
    fun getCharacter(
        @Query("page") page: Int
    ): Observable<Response<Character>>
}