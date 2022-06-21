package com.demo.data.mapper

import com.demo.data.database.entity.Character
import com.demo.data.model.response.Result

class CharacterMapper {

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