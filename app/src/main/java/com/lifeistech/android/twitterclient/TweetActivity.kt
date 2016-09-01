package com.lifeistech.android.twitterclient

/**
 * Created by suzuki on 2016/08/19.
 */

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Tweet
import android.view.View
import android.widget.EditText
import android.widget.Toast

class TweetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)
    }

    fun setTweetMessage (v: View){
        val tweetMsg = findViewById(R.id.tweetMsg) as EditText
        val msg = tweetMsg.getText().toString()
        tweetMsg.setText("")
        postTweet((msg))
        Intent(this@TweetActivity, TimelineActivity::class.java).let {
            startActivity(it)
        }
    }

    fun postTweet (msg: String){
        val twitterSession = TwitterCore.getInstance().sessionManager.activeSession
        val statusService = TwitterApiClient(twitterSession).statusesService
        statusService.update(msg, null, false, null, null, null, false, false, null).enqueue(object: Callback<Tweet>() {
            override fun success(result: Result<Tweet>?) {
                Toast.makeText(applicationContext, "ツイート成功", Toast.LENGTH_LONG).show()
            }
            override fun failure(exception: TwitterException?) {
                Toast.makeText(applicationContext, "ツイート失敗", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun back (v: View) {
        Intent(this@TweetActivity, TimelineActivity::class.java).let {
            startActivity(it)
        }
    }
}