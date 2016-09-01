package com.lifeistech.android.twitterclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import android.content.Intent

import io.fabric.sdk.android.Fabric
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    //ログインボタン
    private var loginButton: TwitterLoginButton by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        loginButton = findViewById(R.id.twitter_login_button) as TwitterLoginButton
        loginButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                val session = result.data
                val msg = "@" + session.userName + "logged in! (#" + session.userId + ")"
                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
                Intent(this@MainActivity, TimelineActivity::class.java).let {
                    startActivity(it)
                }
            }

            override fun failure(exception: TwitterException) {
                Log.d("TwitterKit", "Login with Twitter failure", exception)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode,resultCode,data)
        loginButton.onActivityResult(requestCode, resultCode, data)
    }
}
