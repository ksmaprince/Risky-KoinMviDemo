package com.demo.data.viewstate

import com.demo.data.model.delegate.GetCharacterDelegate

sealed class CharacterViewState {
    class Error(val t: Throwable) : CharacterViewState()

    object Progress : CharacterViewState()

    class CharacterResult(val character: GetCharacterDelegate) : CharacterViewState()
}
