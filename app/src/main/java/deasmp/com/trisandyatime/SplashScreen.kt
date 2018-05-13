package deasmp.com.trisandyatime

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

/**
 * Created by DEAS on 07/04/2018.
 */
class SplashScreen : AppCompatActivity() {

    private val splash_screen = 4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, splash_screen.toLong())


    }
}