package com.lifeistech.android.twitterclient

import android.app.Application
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import io.fabric.sdk.android.Fabric

/**
 * Created by suzuki on 2016/08/19.
 */
class MyApplication : Application() {

    companion object {
        // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
        private const val TWITTER_KEY = "9GWh1c6Cs2y4oFueD26PbT8RB"
        private const val TWITTER_SECRET = "aTccvw6wqzt1Zak7AWYfK4E3DsKOLLgxdAdChBF7ryBwAIMkDe"
    }

    override fun onCreate() {
        super.onCreate()
        val authConfig = TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET)
        Fabric.with(this, Twitter(authConfig))
    }

}