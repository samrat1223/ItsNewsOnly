package com.example.itsnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RecyclerView requires one layout manager and one adapter

        // Giving Layout Manager
                // We have linear , grid and staggered layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = fetchData()

        //Now we have to make an adapter
        val adapter = NewsListAdapter(items, this)

        //Linking the adapter with our recyclerView
        recyclerView.adapter = adapter
    }

    private fun fetchData(): ArrayList<String>
    {
        val list = ArrayList<String>()
        for(i in 0 until 100) {
            list.add("Item $i")
        }
        return list
    }

    override fun onItemClicked(item: String) {
        Toast.makeText(this,"clicked item is $item",Toast.LENGTH_LONG).show()
    }
}