package com.demo.data.datasource.remote

import android.content.Context
import com.demo.data.exception.NetworkException
import com.demo.data.model.response.Character
import com.demo.data.network.RestAdapter
import com.demo.data.network.RiskyService
import com.google.gson.Gson
import io.reactivex.Observable

class RiskyNetworkDatasource(context: Context) {

    private val riskyService = RestAdapter.get(context).create(RiskyService::class.java)

    fun getRiskyCharacter(page: Int): Observable<Character>{
        return riskyService.getCharacter(page)
            .flatMap {
                if (it.isSuccessful){
                    Observable.just(it.body())
                }else{
                    val errorResponse = Gson().fromJson(
                        it.errorBody()?.charStream(),
                        Character::class.java
                    )

                    when{
                        it.code() >= 500 -> throw NetworkException.ApiException(
                            ""
                        )
                        else -> Observable.just(errorResponse)
                    }
                }
            }
    }
}