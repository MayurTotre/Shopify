package com.example.shopify.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.shopify.utils.SharedPreferencesHelper

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferencesHelper
    private lateinit var access_token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        sharedPreferences = SharedPreferencesHelper(this)

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val view = splashScreenView.view

            view.animate()
                .alpha(0f)
                .setDuration(3000)
                .withEndAction {
                    splashScreenView.remove()
                    navigateToNextScreen()
                }
                .start()
        }
    }

    private fun navigateToNextScreen() {
        access_token = sharedPreferences.getData().toString()
        Log.d("SplashDebug", "Access Token: '$access_token'")
        val isLoggedIn = !access_token.isNullOrBlank()

//        val isLoggedIn = false
        val nextActivity = if (isLoggedIn) {
            HomeActivity::class.java
        } else {
            MainActivity::class.java
        }
        startActivity(Intent(this, nextActivity))
        finish()
    }
}
