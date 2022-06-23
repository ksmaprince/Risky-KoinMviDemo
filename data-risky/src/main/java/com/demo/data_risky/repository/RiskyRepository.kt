package com.demo.data_risky.repository

import com.demo.data_risky.database.RoomDbHelper
import com.demo.data_risky.mapper.CharacterMapper
import com.demo.data_risky.model.delegate.GetCharacterDelegate
import com.demo.data_risky.remote.RiskyNetworkDatasource
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RiskyRepository @Inject constructor(
    private val riskyNetworkDatasource: RiskyNetworkDatasource,
    private val mDatabase: RoomDbHelper,
    private val riskyMapper: CharacterMapper
) {
    fun getCharacter(page: Int): Observable<GetCharacterDelegate> {
        return riskyNetworkDatasource.getRiskyCharacter(page)
            .map {
                if (it.info != null) {
                    GetCharacterDelegate.Success(it)
                } else {
                    GetCharacterDelegate.Fail("Error Happen Here")
                }
            }
    }
}