package com.example.iuplayer

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iuplayer.databinding.PlayMusicPlayerListBinding
import kotlinx.coroutines.*
import java.text.SimpleDateFormat


class PlayCustomAdapter(val dataList: MutableList<DataV1>, val parentContext: Context): RecyclerView.Adapter<PlayCustomAdapter.CustomViewHolder>() {
    lateinit var binding: PlayMusicPlayerListBinding
    private var mediaPlayer: MediaPlayer? = null
    var messengerJob: Job? = null
    // 직전에 클릭됐던 Item의 position
//    var prePosition = -1

    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        binding = PlayMusicPlayerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = CustomViewHolder(binding)
        return customViewHolder
    }

    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {
        var selectedItems: SparseBooleanArray = SparseBooleanArray()
        val binding = customViewHolder.binding
        val data = dataList.get(position)

        binding.plIvAlbumCover.setImageResource(data.image)
        binding.plTvAlbumTitle.setText(data.title)
        binding.plTvSinger.setText(data.singer)
        binding.plTvTitleSong.setText(data.song)

        binding.mpBackIv.setImageResource(data.image)
        binding.mpIvAlbumCover.setImageResource(data.image)
        binding.mpTvTitleSong.setText(data.song)
        binding.mpTvTitle.setText(data.title)
        binding.mpTvSinger.setText(data.singer)

        binding.mpIvPlay.setOnClickListener {
            if (mediaPlayer?.isPlaying == true){
                mediaPlayer?.pause()
                binding.mpIvPlay.setImageResource(R.drawable.ic_play_arrow_24)
            } else {
                mediaPlayer?.start()
                binding.mpIvPlay.setImageResource(R.drawable.ic_pause_24)
                val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                messengerJob = backgroundScope.launch {
                    while(mediaPlayer?.isPlaying == true) {
                        (parentContext as PlaymusicActivity).runOnUiThread {
                            var currentPosition = mediaPlayer?.currentPosition!!
                            binding.seekBar.progress = currentPosition
                            val currentDurateion = SimpleDateFormat("mm:ss").format(mediaPlayer!!.currentPosition)
                            binding.mpLeftDuration.text = currentDurateion
                        }
                        try {
                            // 1초마다 수행되도록 딜레이
                            delay(1000)
                        } catch (e: Exception) {
                            Log.d("iuplayer", "스레드 오류 발생")
                        }
                    }//end of while
//                    binding.seekBar.progress = 0
                }
            }
        }

        binding.mpIvFastFoward.setOnClickListener {

        }

        binding.mpIvBackward.setOnLongClickListener {
            val position: Int = 0
            mediaPlayer?.stop()
            messengerJob?.cancel()
            mediaPlayer = MediaPlayer.create(binding.root.context, position)
            when(position){
                R.raw.song24 -> {
                    mediaPlayer?.stop()
                    messengerJob?.cancel()
                    mediaPlayer = MediaPlayer.create(binding.root.context, R.raw.song24)
                    binding.seekBar.progress = 0
                    binding.mpLeftDuration.text = "00:00"
                }
            }
            binding.seekBar.progress = 0
            binding.mpLeftDuration.text = "00:00"
            true
        }

        customViewHolder.itemView.setOnLongClickListener{
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataV1 = dataList.get(position)
            (binding.root.context as MainActivity).playlistFrag.refreshRecyclerViewDrop(dataV1)
            true
        }

        customViewHolder.itemView.setOnClickListener {
            if (selectedItems[position]) {
                // 펼쳐진 Item을 클릭 시
                selectedItems.delete(position)
                binding.mpConstraint.visibility = View.GONE
            } else {
                // 직전의 클릭됐던 Item의 클릭상태를 지움
//                selectedItems.delete(prePosition)
                // 클릭한 Item의 position을 저장
                selectedItems.put(position, true)
                binding.mpConstraint.visibility = View.VISIBLE
            }
//            // 해당 포지션의 변화를 알림
//            if (prePosition != -1) notifyItemChanged(prePosition)
//            notifyItemChanged(position)
//            // 클릭된 position 저장
//            prePosition = position
        }

        /**
        replyMessenger = Messenger(IncomingHandler())

        //외부 서비스와 접촉을 위한 ServiceConnection
        connection = object: ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
                messenger = Messenger(iBinder)
                val msg = Message()
                msg.what = 10
                msg.replyTo = replyMessenger
                messenger.send(msg)
                connectionMode = "connectMessenger"
            }
            //bindService 연결이 안되었을때 onServiceDisconnected 콜백을 해준다.
            override fun onServiceDisconnected(p0: ComponentName?) {
                Log.d("service", "messenger 객체를 받지못함")
                connectionMode = "none"
            }
        }
        // **/

    }
    //내부 클래스
    class CustomViewHolder(val binding: PlayMusicPlayerListBinding): RecyclerView.ViewHolder(binding.root)

    /**
    inner class IncomingHandler: Handler(
        Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                //노래정보를 보낼것이다. (음악파일의 총시간)
                100 -> {
                    Log.d("outer", "${msg.what} IncomingHandler")
                    var bundle = msg.obj as Bundle
                    bundle.getInt("duration")?.let {
                        when{
                            it > 0 -> {
                                binding.seekBar.max = it
                                //coroutine
                                val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                                messengerJob = backgroundScope.launch {
                                    while(binding.seekBar.progress < binding.seekBar.max){
                                        delay(1000)
                                        binding.seekBar.incrementProgressBy(1000)
                                    }
                                    binding.seekBar.progress = 0
                                    connectionMode = "none"
                                    val msg = Message()
                                    msg.what = 20
                                    messenger.send(msg)
                                    unbindService(connection)
                                    messengerJob?.cancel()
                                }
                            }
                            else -> {
                                connectionMode = "none"
                                unbindService(connection)
                            }
                        }
                    }
                }
                //서비스 unbind 요청
                200 -> {
                    connectionMode = "none"
                    val msg = Message()
                    msg.what = 20
                    messenger.send(msg)
                    unbindService(connection)
                }
                //노래 정보가 없다.
                else -> {
                    super.handleMessage(msg)
                }
            }
        }
    }
    // **/
}