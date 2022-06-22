package com.demo.data_risky.model.delegate

import com.demo.data_risky.model.response.Character

sealed class GetCharacterDelegate{
    data class Success(val character: Character): GetCharacterDelegate()
    data class Fail(val errorMessage: String): GetCharacterDelegate()
}
