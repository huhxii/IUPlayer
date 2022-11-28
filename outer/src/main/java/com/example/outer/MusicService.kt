//package com.example.outer
//
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.media.MediaPlayer
//import android.os.*
//import android.util.Log
//
//class MusicService: Service() {
//    //sercive -> outer메세지 전달하는 메신저
//    lateinit var messenger: Messenger
//
//    //sercive <- outer메세지 전달하는 메신저
//    lateinit var replyMessenger: Messenger
//
//    //outer 서비스에서 해야될 일 (음악 재생)
//    lateinit var player: MediaPlayer
//
//    //바인더 생성하는 클래스(메신저 바인더)
//    inner class IncomingHandler(
//        context: Context,
//        val applicationContext: Context = context.applicationContext
//    ) : Handler(
//        Looper.getMainLooper()
//    ) {
//        override fun handleMessage(msg: Message) {
//            when (msg.what) {
//                //노래를 재생하고, 노래 정보 요청
//                10 -> {
//                    Log.d("outer", "${msg.what} IncomingHandler")
//                    //메신저 전달 받음
//                    replyMessenger = msg.replyTo
//                    try {
//                        if (!player.isPlaying) {
//                            player = MediaPlayer.create(this@MusicService, R.raw.strawberry_moon)
//                            player.start()
//                            //serviceMesseger 액티비티로 음악정보 보냄
//                            val message = Message()
//                            message.what = 100
//                            val bundle = Bundle()
//                            bundle.putInt("duration", player.duration)
//                            message.obj = bundle
//                            replyMessenger.send(message)
//                        }
//                    } catch (e: java.lang.Exception) {
//                        Log.d("outer", "${msg.what} = ${msg.obj}")
//                    }
//                }
//                //노래 종료 요청
//                20 -> {
//                    Log.d("outer", "${msg.what} = ${msg.obj}")
//                    if (player.isPlaying) {
//                        player.stop()
//                    }
//                }
//                else -> {
//                    super.handleMessage(msg)
//                }
//            }
//        }
//    }
//
//    override fun onCreate() {
//        Log.d("outer", "outer onCreate()")
//        player = MediaPlayer()
//    }
//
//    override fun onBind(intent: Intent): IBinder {
//        Log.d("outer", "outer onBind()")
//        messenger = Messenger(IncomingHandler(this))
//        return messenger.binder
//    }
//
//    override fun onUnbind(intent: Intent?): Boolean {
//        return super.onUnbind(intent)
//        Log.d("outer", "outer onUnbind()")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("outer", "outer onDestroy()")
//        player.release()
//    }
//}