package com.example.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable
import android.content.Intent as Intent


class MainActivity : AppCompatActivity(), MusicListAdapter.OnItemClickListener {

    private val musicListArray = mutableListOf(
        DataList("BadAssSong", R.raw.a4),
        DataList("AnotherOne", R.raw.a1),
        DataList("AgainAndAgain", R.raw.a5),
        DataList("Bruh", R.raw.a6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MusicListAdapter(musicListArray, this)
        val recycler = findViewById<RecyclerView>(R.id.musicList)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClick(position: Int) {


        val intent = Intent(this, PlayActivity::class.java)
        intent.putExtra("songTitle", musicListArray[position].songTitle)
        startActivity(intent)
    }
}
