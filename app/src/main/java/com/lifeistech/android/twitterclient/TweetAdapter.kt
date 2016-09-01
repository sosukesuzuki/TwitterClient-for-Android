package com.lifeistech.android.twitterclient

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.twitter.sdk.android.core.models.Tweet

/**
 * Created by suzuki on 2016/08/20.
 */
class TweetAdapter(context: Context) : ArrayAdapter<Tweet>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        // View
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false)
        val userText: TextView = view.findViewById(R.id.userText) as TextView
        val bodyText: TextView = view.findViewById(R.id.bodyText) as TextView
        val imageView: ImageView = view.findViewById(R.id.imageView) as ImageView

        // Contents
        val tweet: Tweet = getItem(position)
        userText.text = tweet.user.name
        bodyText.text = tweet.text
        Picasso.Builder(context).build().load(tweet.user.profileImageUrl).into(imageView)

        return view
    }

}