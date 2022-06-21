package com.demo.data.repository

import androidx.room.RoomDatabase
import com.demo.data.database.entity.Character
import com.demo.data.datasource.remote.RiskyNetworkDatasource
import com.demo.data.mapper.CharacterMapper
import com.demo.data.model.delegate.GetCharacterDelegate
import com.demo.data.storage.InternalStorageManager
import io.reactivex.Observable

class RiskyRepository(
    private val riskyNetworkDatasource: RiskyNetworkDatasource,
    private val internalStorageManager: InternalStorageManager,
    private val mDatabase: RoomDatabase,
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