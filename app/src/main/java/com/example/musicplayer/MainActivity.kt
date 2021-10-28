package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private lateinit var musicPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        musicPlayer = MediaPlayer.create(this, R.raw.a6)
        musicPlayer.setVolume(0.5f, 0.5f)
    }

    fun playBtnClick(view: View) {
        val playBtn : ImageButton = findViewById(R.id.play_btn)
       if(musicPlayer.isPlaying){
           playBtn.setOnClickListener(){
               musicPlayer.pause()
               playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
            }
       }
        else{
            musicPlayer.start()
            playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24)
       }

    }

}