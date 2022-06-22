package com.demo.data_risky.viewstate

import com.demo.data_risky.model.delegate.GetCharacterDelegate

sealed class CharacterViewState {
    class Error(val t: Throwable) : CharacterViewState()

    object Progress : CharacterViewState()

    class CharacterResult(val character: GetCharacterDelegate) : CharacterViewState()
}
