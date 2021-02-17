package com.example.itsnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//Now we have to extend this class with RecyclerView to make it an adapter
//inside adapter<-> - we put a view holder class
class NewsListAdapter(private val items : ArrayList<String> , private val listener : NewsItemClicked): RecyclerView.Adapter<NewsViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // This method is called when an activity is created

        // NewsViewHolder takes a itemView which is converted to View ,
        // Now to convert itemView which is in XML to View format we use LayoutInflater class
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemnews, parent,false)
        val viewHolder = NewsViewHolder(view)
        //Adding onClick Listener
        view.setOnClickListener(){
                listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
        //This LayoutInflater class takes R.layout.itemews xml and converts it to view .
        //Then we pass view to NewsViewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    //It binds data corresponding to the item together inside the holder
        //Position parameter gives the location of the data which you have to bind
        val currItem = items[position]
        holder.titleView.text = currItem
    }

    override fun getItemCount(): Int {
        //This tells how many items are there inside this list
        return items.size
    }
}


//Making viewHolder Class which extends recyclerView's viewHolder class
//Because inside the recyclerview there are multiple nos of views
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Taking itemnews.xml's itemView i.e. ConstraintLayout inside this itemView
    val titleView : TextView = itemView.findViewById(R.id.title)
}

// Creating a call Back function using interfaces which will tell the activity that one item has been clicked . And all the responsibilities
// for clicking any item goes to activity and not the adapter

interface  NewsItemClicked {
    fun onItemClicked(item:String)
}