package com.lifeistech.android.twitterclient

/**
 * Created by suzuki on 2016/08/19.
 */
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AbsListView
import android.widget.ListView
import android.widget.Toast
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import kotlin.properties.Delegates

class TimelineActivity : AppCompatActivity(), AbsListView.OnScrollListener {

    var adapter: TweetAdapter by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        val listView = findViewById(R.id.listView) as ListView
        adapter = TweetAdapter(this)
        listView.adapter = adapter
        listView.setOnScrollListener(this)
        loadNextTweet()
    }

    override fun onScroll(parent: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if(firstVisibleItem + visibleItemCount >= totalItemCount) {

            val lastTweetId: Long? = if (adapter.count > 0) {
                adapter.getItem(adapter.count - 1).id - 1
            } else {
                null
            }
            loadNextTweet(lastTweetId)
        }
    }

    override fun onScrollStateChanged(parent: AbsListView?, state: Int) {

    }

    private fun loadNextTweet(maxId: Long? = null){
        val twitterApiClient = TwitterCore.getInstance().apiClient
        val statusService = twitterApiClient.statusesService

        statusService.homeTimeline(5, null, null, false, false, false, false).enqueue(object: Callback<List<Tweet>> () {
            override fun success(result: Result<List<Tweet>>?){
                result?.let { adapter.addAll(it.data) }
            }
            override fun failure(e: TwitterException){
                Toast.makeText(applicationContext, "タイムラインの読み込みに失敗", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun tweet(v: View) {
        Intent(this@TimelineActivity, TweetActivity::class.java).let {
            startActivity(it)
        }
    }
}