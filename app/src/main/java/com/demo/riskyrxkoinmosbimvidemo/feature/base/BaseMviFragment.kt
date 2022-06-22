package com.demo.riskyrxkoinmosbimvidemo.feature.base

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.demo.riskyrxkoinmosbimvidemo.R
import com.demo.riskyrxkoinmosbimvidemo.feature.mvi.MviXFragment
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.Colors
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.sprite.SpriteContainer
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.ThreeBounce
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseMviFragment<VS, VIEW : MvpView, PRESENTER : MviBasePresenter<VIEW, VS>> :
    MviXFragment<VIEW, PRESENTER>() {

    protected var isLoading = false
    private var mProgressBar: ProgressDialog? = null
    private var dialog: Dialog? = null

    lateinit var alertDialog: AlertDialog

    private val compositeDisposable = CompositeDisposable()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun showToast(message: String) {
        if (message != "") Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


    fun showLongToast(message: String) {
        if (message != "") Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    protected fun showLoading() {
        if (!isLoading) {
            dialog = Dialog(requireContext())
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog!!.setCancelable(false)
            dialog!!.setContentView(R.layout.custom_loading_layout)
            val doubleBounce = ThreeBounce()
            doubleBounce.setBounds(0, 0, 100, 100)
            doubleBounce.color = Colors.colors[0]
            dialog!!.findViewById<ProgressBar>(R.id.progressBar).indeterminateDrawable =
                doubleBounce
            if (dialog != null) {
                dialog!!.show()
            }
            isLoading = true

        }
    }

    protected fun showLoading(message: String) {
        if (!isLoading) {
            dialog = Dialog(requireContext())
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog!!.setCancelable(false)
            dialog!!.setContentView(R.layout.custom_loading_layout)
            val doubleBounce = ThreeBounce()
            doubleBounce.setBounds(0, 0, 100, 100)
            doubleBounce.color = Colors.colors[0]
            dialog!!.findViewById<ProgressBar>(R.id.progressBar).indeterminateDrawable =
                doubleBounce

            if (dialog != null) {
                dialog!!.show()
            }
            isLoading = true
        }
    }

    protected fun hideLoading() {
        if (isLoading) {
            if (dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
            }
            isLoading = false

        }
    }

    private fun showSessionTimeoutDialog(errorMsg: String) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it.requireContext())
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    dialog.dismiss()
                    /*val intent = SignInActivity.newIntent(requireActivity())
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)*/
                    requireActivity().finish()
                    startAnimationIntent()
                }
            }
            builder.create()
        }
        alertDialog.setMessage(errorMsg)
        if (!requireActivity().isFinishing) {
            alertDialog.show()
        }
    }

    protected fun showPromptDialog(errorMsg: String) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it.requireContext())
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    alertDialog.dismiss()
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog.setMessage(errorMsg)
        if (!requireActivity().isFinishing) {
            alertDialog.show()
        }
    }

    protected fun showPromptDialog(title: String, errorMsg: String) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it.requireContext())
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    alertDialog.dismiss()
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog.setTitle(title)
        alertDialog.setMessage(errorMsg)
        if (!requireActivity().isFinishing) {
            alertDialog.show()
        }
    }

    protected fun startAnimationIntent() {
        requireActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out)
    }

    protected fun endAnimationIntent() {
        requireActivity().overridePendingTransition(R.anim.left_in, R.anim.right_out)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) compositeDisposable.clear()
    }

    fun getBounce(): SpriteContainer {
        val doubleBounce = ThreeBounce()
        doubleBounce.setBounds(0, 0, 100, 100)
        doubleBounce.color = Colors.colors[0]
        return doubleBounce
    }
}
