package com.example.musicplayer

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

  class MusicListAdapter(
    var songs : List<DataList>,
    private val listener : OnItemClickListener
) :RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {

      private lateinit var musicTitle: TextView

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          val view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
          return ViewHolder(view)
      }

      override fun onBindViewHolder(holder: ViewHolder, position: Int) {

          holder.itemView.apply {
              musicTitle = findViewById(R.id.musicTitle)
              musicTitle.text = songs[position].songTitle
          }
      }

      override fun getItemCount(): Int {
          return songs.size
      }

      inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
          View.OnClickListener {
          init {
              itemView.setOnClickListener(this)
          }

          override fun onClick(p0: View?) {
              val position: Int = adapterPosition
              if (position != RecyclerView.NO_POSITION) {
                  listener.onItemClick(position)
              }
          }

      }

      interface OnItemClickListener {
          fun onItemClick(position: Int)
      }
  }
