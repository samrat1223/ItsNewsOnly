package com.example.itsnews

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var  mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RecyclerView requires one layout manager and one adapter

        // Giving Layout Manager
                // We have linear , grid and staggered layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()

        //Now we have to make an adapter
         mAdapter = NewsListAdapter(this)

        //Linking the adapter with our recyclerView
        recyclerView.adapter = mAdapter
    }

    private fun fetchData()
    {
        val url ="http://newsapi.org/v2/everything?q=bitcoin&from=2021-01-17&sortBy=publishedAt&apiKey=701892a21a4340e186bc8583a251c82c\n"
        val jsonObjectRequests = JsonObjectRequest(

                Request.Method.GET,
                url,
                null,
                Response.Listener {
                    val newsJsonArray = it.getJSONArray("articles")
                    val newsArray = ArrayList<News>()
                            for(i in 0 until newsJsonArray.length()){
                                val newsJsonObject = newsJsonArray.getJSONObject(i)
                                val news = News(
                                        newsJsonObject.getString("title"),
                                        newsJsonObject.getString("author"),
                                        newsJsonObject.getString("url"),
                                        newsJsonObject.getString("urlToImage")
                                )
                                newsArray.add(news)
                            }
                    mAdapter.updateNews(newsArray)
                },
                Response.ErrorListener {

                }
        )
    }

    override fun onItemClicked(item: News) {
        var builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}