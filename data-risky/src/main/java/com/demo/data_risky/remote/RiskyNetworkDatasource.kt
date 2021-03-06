package com.demo.data_risky.remote

import android.content.Context
import com.demo.data_risky.exception.NetworkException
import com.demo.data_risky.model.response.Character
import com.demo.data_risky.network.RestAdapter
import com.demo.data_risky.network.RiskyService
import com.google.gson.Gson
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RiskyNetworkDatasource @Inject constructor(context: Context) {

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