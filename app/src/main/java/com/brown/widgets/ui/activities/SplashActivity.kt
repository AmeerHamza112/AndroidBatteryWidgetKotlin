package com.brown.widgets.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.brown.widgets.MainActivity
import com.brown.widgets.R

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