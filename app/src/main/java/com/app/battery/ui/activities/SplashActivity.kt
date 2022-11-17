package com.app.battery.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.battery.MainActivity
import com.app.battery.R

class SplashActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		Handler().postDelayed({
				startActivity(Intent(this@SplashActivity, MainActivity::class.java))
				finish()

		}, 3000)
	}
}