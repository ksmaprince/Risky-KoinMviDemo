package com.demo.data.interactor

import com.demo.data.executor.BackgroundThread
import com.demo.data.executor.PostExecutionThread
import com.demo.data.repository.RiskyRepository
import com.demo.data.viewstate.CharacterViewState
import io.reactivex.Observable

class CharacterInteractor(
    private val repository: RiskyRepository,
    private val mainThread: PostExecutionThread,
    private val backgroundThread: BackgroundThread
) {

    data class CharacterIntent(
        val page: Int
    )

    fun executeCharacter(characterIntent: CharacterIntent): Observable<CharacterViewState> {
        return repository.getCharacter(characterIntent.page)
            .map { CharacterViewState.CharacterResult(it) }
            .cast(CharacterViewState::class.java)
            .onErrorReturn { CharacterViewState.Error(it) }
            .startWith(CharacterViewState.Progress)
            .subscribeOn(backgroundThread.getSchedular())
            .observeOn(mainThread.getSchedular())
    }

}