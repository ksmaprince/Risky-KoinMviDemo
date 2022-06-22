package com.demo.riskyrxkoinmosbimvidemo.feature.base

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.demo.data_risky.network.interceptor.HttpErrorEvent
import com.demo.data_risky.storage.InternalStorageManager
import com.demo.riskyrxkoinmosbimvidemo.R
import com.demo.riskyrxkoinmosbimvidemo.utils.LocaleHelper
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.Colors
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.Colors.colors
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.sprite.SpriteContainer
import com.demo.riskyrxkoinmosbimvidemo.utils.loader.style.ThreeBounce
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.hannesdorfmann.mosby3.mvi.MviPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.koin.java.KoinJavaComponent.inject

abstract class BaseMviActivity<VS, VIEW : MvpView, PRESENTER : MviPresenter<VIEW, VS>> :
    MviActivity<VIEW, PRESENTER>() {


    protected var isLoading = false
    private var mProgressBar: ProgressDialog? = null
    private var dialog: Dialog? = null

    lateinit var alertDialog: AlertDialog

    private val compositeDisposable = CompositeDisposable()


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(R.color.teal_700)
        add(
            HttpErrorEvent.subscribe().observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    handle(
                        it
                    )
                })
        )
    }


    private fun handle(event: HttpErrorEvent.Event) {
        Log.i(javaClass.simpleName, event.type.toString())
        when (event.type) {
            HttpErrorEvent.Type.UNAUTHORIZED -> {
                /*val intent = SignInActivity.newIntent(applicationContext)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)*/
                finish()
                startAnimationIntent()
            }
            HttpErrorEvent.Type.SERVER_ERROR -> {
                hideLoading()
                //   showPromptDialog("Something went wrong. Please try again later.")
            }

            HttpErrorEvent.Type.MAINTENANCE -> {
                //TODO: Redirect to Maintenance screen
            }
        }
    }


    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun showToast(message: String) {
        if (message != "") Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(message: String) {
        if (message != "") Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    protected fun showLoading() {
        if (!isLoading) {
//            mProgressBar = ProgressDialog.show(this, "", "loading...")
//            if (mProgressBar != null) {
//                mProgressBar!!.show()
//            }
//            isLoading = true
            dialog = Dialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog!!.setCancelable(false)
            dialog!!.setContentView(R.layout.custom_loading_layout)
            val doubleBounce = ThreeBounce()
            doubleBounce.setBounds(0, 0, 100, 100)
            doubleBounce.color = colors[0]
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
//            mProgressBar = ProgressDialog.show(this, "", message)
//            if (mProgressBar != null) {
//                mProgressBar!!.show()
//            }
//            isLoading = true
            dialog = Dialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog!!.setCancelable(false)
            dialog!!.setContentView(R.layout.custom_loading_layout)
            val doubleBounce = ThreeBounce()
            doubleBounce.setBounds(0, 0, 100, 100)
            doubleBounce.color = colors[0]
            dialog!!.findViewById<ProgressBar>(R.id.progressBar).indeterminateDrawable =
                doubleBounce

            if (dialog != null) {
                dialog!!.show()
            }
            isLoading = true
        }
    }

    protected fun showLoading(isBackable: Boolean) {
        if (!isLoading) {
            dialog = Dialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog!!.setCancelable(false)
            dialog!!.setContentView(R.layout.custom_loading_layout)
            if (isBackable) {
                dialog?.setOnKeyListener { dialog, keyCode, _ ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog?.dismiss()
                        true
                    }
                    false
                }
            }
            val doubleBounce = ThreeBounce()
            doubleBounce.setBounds(0, 0, 100, 100)
            doubleBounce.color = colors[0]
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
//            if (mProgressBar != null && mProgressBar!!.isShowing) {
//                mProgressBar!!.dismiss()
//            }
//            isLoading = false
            if (dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
            }
            isLoading = false

        }
    }

    private fun showSessionTimeoutDialog(errorMsg: String) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    dialog.dismiss()
                    /*val intent = SignInActivity.newIntent(applicationContext)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)*/
                    finish()
                    startAnimationIntent()
                }
            }
            builder.create()
        }
        alertDialog!!.setMessage(errorMsg)
        if (!isFinishing) {
            alertDialog.show()
        }
    }

    protected fun showPromptDialog(errorMsg: String) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it)
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
        alertDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setMessage(errorMsg)
        if (!isFinishing) {
            alertDialog.show()
        }
    }

    protected fun showPromptDialog(errorMsg: String, listener: Listener) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    listener.customOkClicked()
                    alertDialog.dismiss()
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setMessage(errorMsg)
        if (!isFinishing) {
            alertDialog.show()
        }
    }

    protected fun showPromptDialog(title: String, errorMsg: String) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it)
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
        if (!isFinishing) {
            alertDialog.show()
        }
    }

    protected fun showPromptDialogAndFinish(message: String, finish: Boolean) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    dialog.dismiss()
                    if (finish) {
                        setResult(1000)
                        finish()
                        endAnimationIntent()
                    }
                }
            }
            builder.create()
        }
        alertDialog.setMessage(message)
        if (!isFinishing) {
            alertDialog.show()
        }
    }


    protected fun showPromptDialogAndGoNext(message: String, finish: Boolean, intent: Intent) {
        hideLoading()
        alertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    dialog.dismiss()
                    startActivity(intent)
                    if (finish) {
                        finish()
                        endAnimationIntent()
                    }
                }
            }
            builder.create()
        }
        alertDialog.setMessage(message)
        if (!isFinishing) {
            alertDialog.show()
        }
    }


    fun getAlert(): AlertDialog {
        return this.alertDialog
    }

    fun getVersionName(): String {
        val pkInfo = packageManager.getPackageInfo(packageName, 0)
        return pkInfo.versionName
    }


    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) compositeDisposable.clear()
    }

    protected fun startAnimationIntent() {
        overridePendingTransition(R.anim.right_in, R.anim.left_out)
    }

    protected fun endAnimationIntent() {
        overridePendingTransition(R.anim.left_in, R.anim.right_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        endAnimationIntent()
    }

    fun setStatusBarColor() {

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(applicationContext, android.R.color.white)

        //window.navigationBarColor = ContextCompat.getColor(applicationContext, android.R.color.white)
    }

    fun setStatusBarColor(colors: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, colors)
    }


    fun setNavigationBarColor(colors: Int = R.color.white) {
        window.navigationBarColor = ContextCompat.getColor(this, colors)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }

        }
    }

    fun getBounce(): SpriteContainer {
        val doubleBounce = ThreeBounce()
        doubleBounce.setBounds(0, 0, 100, 100)
        doubleBounce.color = Colors.colors[0]
        return doubleBounce
    }


    interface Listener {
        fun customOkClicked()
    }
}