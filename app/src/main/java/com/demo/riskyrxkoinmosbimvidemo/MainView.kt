package com.demo.riskyrxkoinmosbimvidemo

import com.demo.data_risky.interactor.CharacterInteractor
import com.demo.data_risky.viewstate.CharacterViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface MainView : MvpView {
    fun render(viewState: CharacterViewState)

    fun characterIntent(): Observable<CharacterInteractor.CharacterIntent>
}