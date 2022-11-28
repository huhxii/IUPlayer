package com.example.iuplayer

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iuplayer.databinding.MusicItemBinding
import java.text.SimpleDateFormat

class MusicRecyclerAdapter(val context: Context, val musicList: MutableList<Music>?): RecyclerView.Adapter<MusicRecyclerAdapter.CustomViewHolder>() {
    companion object{
        val ALBUM_SIZE = 90
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = MusicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding
        val music = musicList?.get(position)

        binding.miTvArtist.text = music?.artist
        binding.miTvTitle.text = music?.title
        binding.miTvDuration.text = SimpleDateFormat("mm:ss").format(music?.duration)
        val bitmap = music?.getAlbumImage(context, MusicRecyclerAdapter.ALBUM_SIZE)
        if(bitmap != null){
            binding.miAlbum.setImageBitmap(bitmap)
        }else{
            binding.miAlbum.setImageResource(R.drawable.ic_album_24)
        }
        when(music?.likes){
            0 -> binding.miIvLike.setImageResource(R.drawable.empty_like)
            1 -> binding.miIvLike.setImageResource(R.drawable.full_like)
        }
        Log.d("finding error","data send success")
        //이벤트처리
        binding.root.setOnClickListener {
            val playList: ArrayList<Parcelable>? = musicList as ArrayList<Parcelable>

            val intent = Intent(binding.root.context, PlaymusicActivity::class.java)
            intent.putExtra("playList", playList)
            intent.putExtra("position", position)
//            intent.putExtra("music", music) // 뒤로가기 앞으로가기 안됨
            binding.root.context.startActivity(intent)
        }

        binding.miIvLike.setOnClickListener {
            if(music?.likes == 0){
                binding.miIvLike.setImageResource(R.drawable.full_like)
                music?.likes = 1
            }else{
                binding.miIvLike.setImageResource(R.drawable.empty_like)
                music?.likes = 0
            }
            if(music != null){
                val dbHelper = DBHelper(context, MusicActivity.DB_NAME, MusicActivity.VERSION)
                var flag = dbHelper.updateLike(music)
                if(flag == false){
                    Log.d("iuplayer","MusicRecyclerAdapter.onBindViewHolder error ${music.toString()}")
                }else{
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return musicList?.size?:0
    }

    class CustomViewHolder(val binding: MusicItemBinding): RecyclerView.ViewHolder(binding.root)
}