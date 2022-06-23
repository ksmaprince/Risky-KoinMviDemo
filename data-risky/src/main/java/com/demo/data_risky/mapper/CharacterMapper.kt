package com.demo.data_risky.mapper

import com.demo.data.database.entity.Character
import com.demo.data_risky.model.response.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterMapper @Inject constructor(){

    fun mapCharacter(results: List<Result>): List<Character> {
        val charList = arrayListOf<Character>()
        results.forEach {
            charList.add(
                Character(
                    id = 0,
                    name = it.name,
                    status = it.status,
                    species = it.species,
                    type = it.type,
                    gender = it.gender
                )
            )
        }
        return charList
    }
}