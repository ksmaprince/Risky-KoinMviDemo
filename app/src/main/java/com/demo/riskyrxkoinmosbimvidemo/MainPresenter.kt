package com.demo.riskyrxkoinmosbimvidemo

import com.demo.data_risky.interactor.CharacterInteractor
import com.demo.data_risky.viewstate.CharacterViewState
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import javax.inject.Inject

class MainPresenter @Inject constructor(private val characterInteractor: CharacterInteractor) :
    MviBasePresenter<MainView, CharacterViewState>() {
    override fun bindIntents() {
        val characterIntent: Observable<CharacterViewState> =
            intent(MainView::characterIntent)
                .flatMap { characterInteractor.executeCharacter(it) }

        val allIntents = Observable.mergeArray(
            characterIntent
        )

        subscribeViewState(allIntents, MainView::render)
    }
}