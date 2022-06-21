package com.demo.data.model.delegate

import com.demo.data.model.response.Character
import com.demo.data.model.response.Result

sealed class GetCharacterDelegate{
    data class Success(val character: Character): GetCharacterDelegate()
    data class Fail(val errorMessage: String): GetCharacterDelegate()
}
