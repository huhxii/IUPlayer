package com.example.iuplayer

import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.SeekBar
import com.example.iuplayer.databinding.ActivityPlaymusicBinding
import kotlinx.coroutines.*
import java.text.SimpleDateFormat

class PlaymusicActivity : AppCompatActivity() {
    companion object{
        val ALBUM_SIZE = 80
    }
    private lateinit var binding: ActivityPlaymusicBinding
    private var playList: MutableList<Parcelable>? = null
    private var position: Int = 0
    private var music: Music? = null
    private var mediaPlayer: MediaPlayer? = null
    private var messengerJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaymusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //인텐트 정보 가져오기
        playList = intent.getParcelableArrayListExtra("playList")
        position = intent.getIntExtra("position", 0)
        music = playList?.get(position) as Music?

        //화면에 binding
        binding.apTvTitle.text = music?.title
        binding.apTvArtist.text = music?.artist
        binding.apTvAlbumId.text = music?.albumId
        binding.apDurationFinish.text = SimpleDateFormat("mm:ss").format(music?.duration)
        binding.apDurationStart.text = "00:00"
        val bitmap = music?.getAlbumImage(this, ALBUM_SIZE)
        if(bitmap != null){
            binding.apIvAlbumImage.setImageBitmap(bitmap)
            binding.apBackIv.setImageBitmap(bitmap)
        }else{
            binding.apIvAlbumImage.setImageResource(R.drawable.ic_music_note_24)
            binding.apBackIv.setImageResource(R.drawable.ic_album_24)
        }

        //음악 등록
        mediaPlayer = MediaPlayer.create(this, music?.getMusicUri())

        //시크바 : 음악 재생위치 변경
        binding.apSeekBar.max = mediaPlayer!!.duration
        binding.apSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.d("iuplayer", "움직일때 호출")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.d("iuplayer", "멈출때 호출")
            }
        })

        binding.apIvPlaylist.setOnClickListener {
            mediaPlayer?.stop()
            messengerJob?.cancel()
            finish()
        }

        binding.apIvBackward.setOnClickListener {
            if(binding.apDurationStart.text.toString() > "00:05"){
                mediaPlayer?.stop()
                messengerJob?.cancel()
                mediaPlayer = MediaPlayer.create(this, music?.getMusicUri())
                binding.apSeekBar.progress = 0
                binding.apDurationStart.text = "00:00"
                binding.apIvPlay.setImageResource(R.drawable.ic_play_arrow_24)
            }else{

            }
        }

        binding.apIvPlay.setOnClickListener {
            if (mediaPlayer?.isPlaying == true){
                mediaPlayer?.pause()
                binding.apIvPlay.setImageResource(R.drawable.ic_play_arrow_24)
            } else {
                mediaPlayer?.start()
                binding.apIvPlay.setImageResource(R.drawable.ic_pause_24)

                val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                messengerJob = backgroundScope.launch {
                    while(mediaPlayer?.isPlaying == true) {
                        runOnUiThread {
                            var currentPosition = mediaPlayer?.currentPosition!!
                            binding.apSeekBar.progress = currentPosition
                            val currentDurateion = SimpleDateFormat("mm:ss").format(mediaPlayer!!.currentPosition)
                            binding.apDurationStart.text = currentDurateion
                        }
                        try {
                            // 1초마다 수행되도록 딜레이
                            delay(1000)
                        } catch (e: Exception) {
                            Log.d("로그", "스레드 오류 발생")
                        }
                    }
                }
            }
        }
    }
}