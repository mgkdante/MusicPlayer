package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var musicList = arrayListOf(R.raw.a1,R.raw.a4, R.raw.a5, R.raw.a6)

    private lateinit var musicPlayer: MediaPlayer
    private lateinit var playBtn: ImageButton
    private lateinit var forwardBtn: ImageButton
    private lateinit var backBtn: ImageButton
    private lateinit var titleSong : TextView
    private lateinit var progressBar: SeekBar

    private lateinit var runnable : Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var songPosition = 0

        titleSong = findViewById(R.id.title)
        playBtn = findViewById(R.id.play_btn)
        forwardBtn = findViewById(R.id.forward_btn)
        backBtn = findViewById(R.id.back_btn)
        progressBar = findViewById(R.id.musicPorgress)
        musicPlayer = MediaPlayer.create(this, musicList[songPosition])

        progressBar.progress = 0
        progressBar.max = musicPlayer.duration

        musicPlayer.start()

        playBtn.setOnClickListener {
            if (musicPlayer.isPlaying) {
                playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
                musicPlayer.pause()

            } else {
                playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                musicPlayer.start()
            }
        }

        forwardBtn.setOnClickListener {
            if(songPosition < musicList.size) {
                songPosition++
            }
            else{
                songPosition = 0
            }
            musicPlayer.reset()
            musicPlayer = MediaPlayer.create(this, musicList[songPosition])
            musicPlayer.start()
        }

        backBtn.setOnClickListener{
            if(songPosition > 0){
                songPosition--
            }
            else{
                songPosition = musicList.size
            }
            musicPlayer.reset()
            musicPlayer = MediaPlayer.create(this, musicList[songPosition])
            musicPlayer.start()
        }

        progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {
                if (p2) {
                    musicPlayer.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        runnable = Runnable {
            progressBar.progress = musicPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }
}

