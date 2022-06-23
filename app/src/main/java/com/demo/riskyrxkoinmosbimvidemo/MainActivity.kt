package com.demo.riskyrxkoinmosbimvidemo

import android.os.Bundle
import android.widget.Toast
import com.demo.data_risky.interactor.CharacterInteractor
import com.demo.data_risky.model.delegate.GetCharacterDelegate
import com.demo.data_risky.viewstate.CharacterViewState
import com.demo.riskyrxkoinmosbimvidemo.feature.base.BaseMviActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseMviActivity<CharacterViewState, MainView, MainPresenter>(), MainView {
    private val TAG = "MainActivity"

    @Inject
    lateinit var mMainPresenter: MainPresenter

    private val mGetCharacterRelay: PublishSubject<CharacterInteractor.CharacterIntent> =
        PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        mGetCharacterRelay.onNext(CharacterInteractor.CharacterIntent(0))
    }

    override fun createPresenter(): MainPresenter {
        return mMainPresenter
    }

    override fun render(viewState: CharacterViewState) {
        when (viewState) {
            is CharacterViewState.Progress -> showLoading()
            is CharacterViewState.Error -> showPromptDialog(viewState.t.message.toString())
            is CharacterViewState.CharacterResult -> renderCharacterResult(viewState.character)
        }
    }

    override fun characterIntent(): Observable<CharacterInteractor.CharacterIntent> {
        return mGetCharacterRelay
    }

    private fun renderCharacterResult(charDelegate: GetCharacterDelegate) {
        when (charDelegate) {
            is GetCharacterDelegate.Success -> {
                val response = charDelegate.character
                Toast.makeText(this, response.results.size.toString(), Toast.LENGTH_LONG).show()
            }
            is GetCharacterDelegate.Fail -> {
                showPromptDialog(charDelegate.errorMessage)
            }
        }
        hideLoading()
    }
}