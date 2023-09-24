package com.enrech.tarisearch.common.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import com.enrech.tarisearch.common.navigation.Constant.ACTIVITY_MAIN
import com.enrech.tarisearch.common.navigation.Constant.ACTIVITY_SPLASH

class IntentNavigator(private val context: Context) {

    fun openMain(clearStack: Boolean = true, deepLink: Uri? = null) = openIntentPackageName(
        getIntentForClassName(
            ACTIVITY_MAIN,
            deepLink
        )
    ).also {
            if (clearStack) {
                (context as? ComponentActivity)?.finish()
            }
        }

    private fun getMainIntent(flags: Int? = null, uri: Uri? = null): Intent = getIntentForClassName(ACTIVITY_MAIN, uri).apply {
        setPackage(context.packageName)
        flags?.let(::addFlags)
    }

    private fun getSplashIntent(flags: Int? = null, uri: Uri? = null): Intent = getIntentForClassName(
        ACTIVITY_SPLASH, uri
    ).apply {
        setPackage(context.packageName)
        flags?.let(::addFlags)
    }

    private fun openIntentPackageName(intent: Intent) =
        context.startActivity(intent.setPackage(context.packageName))

    private fun openIntent(intent: Intent) = context.startActivity(intent)

    private fun getIntentForClassName(activityName: String, uri: Uri? = null): Intent =
        Intent().apply {
            setClassName(context.applicationContext.packageName, activityName)
            data = uri
        }
}