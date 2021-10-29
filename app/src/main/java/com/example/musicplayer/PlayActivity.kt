package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView

class PlayActivity : AppCompatActivity() {

    companion object{
        var playing = false;
    }
    private var musicList = arrayListOf(R.raw.a1,R.raw.a4, R.raw.a5, R.raw.a6)
    private lateinit var musicPlayer: MediaPlayer
    private lateinit var playBtn: ImageButton
    private lateinit var forwardBtn: ImageButton
    private lateinit var backBtn: ImageButton
    private lateinit var titleSong : TextView
    private lateinit var progressBar: SeekBar
    private  var songPosition: Int = -1
    private lateinit var runnable : Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        songPosition = intent.getIntExtra("songPosition",-1);
        AssertionError(songPosition != -1)

        titleSong = findViewById(R.id.title)
        playBtn = findViewById(R.id.play_btn)
        forwardBtn = findViewById(R.id.forward_btn)
        backBtn = findViewById(R.id.back_btn)
        progressBar = findViewById(R.id.musicPorgress)

        if(playing == true){
            musicPlayer.reset();
            musicPlayer.release();
        }

        musicPlayer = MediaPlayer.create(this, musicList[songPosition])
        titleSong.text = MainActivity.musicListArray[songPosition].songTitle
        progressBar.progress = 0
        progressBar.max = musicPlayer.duration


        musicPlayer.start()
        PlayActivity.playing = true;
        playBtn.setOnClickListener {
            if (musicPlayer.isPlaying) {
                playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
                musicPlayer.pause()
                playing = false;

            } else {
                playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                musicPlayer.start()
            }
        }

        forwardBtn.setOnClickListener {
            if(songPosition < musicList.size - 1 ) {
                songPosition++
            }
            else{
                songPosition = 0
            }
            setSong()

        }

        backBtn.setOnClickListener{
            if(songPosition > 0){
                songPosition--
            }
            else{
                songPosition = musicList.size - 1 ;
            }
            setSong();
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

    fun setSong(){
        if(musicPlayer.isPlaying) musicPlayer.reset()
        titleSong.text = MainActivity.musicListArray[songPosition].songTitle
        musicPlayer = MediaPlayer.create(this, musicList[songPosition])
        musicPlayer.start()
    }
}

