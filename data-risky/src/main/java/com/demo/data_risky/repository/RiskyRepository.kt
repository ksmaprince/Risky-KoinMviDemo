package com.demo.data_risky.repository

import androidx.room.RoomDatabase
import com.demo.data_risky.database.RoomDbHelper
import com.demo.data_risky.remote.RiskyNetworkDatasource
import com.demo.data_risky.mapper.CharacterMapper
import com.demo.data_risky.model.delegate.GetCharacterDelegate
import io.reactivex.Observable

class RiskyRepository(
    private val riskyNetworkDatasource: RiskyNetworkDatasource,
    private val mDatabase: RoomDbHelper,
    private val riskyMapper: CharacterMapper
) {
    fun getCharacter(page:Int) : Observable<GetCharacterDelegate>{
        return riskyNetworkDatasource.getRiskyCharacter(page)
            .map {
                if(it.info!=null){
                    GetCharacterDelegate.Success(it)
                }else{
                    GetCharacterDelegate.Fail("Error Happen Here")
                }
            }
    }
}