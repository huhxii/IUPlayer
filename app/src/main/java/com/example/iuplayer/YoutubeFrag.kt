package com.example.iuplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iuplayer.databinding.FragmentYoutubeBinding

class YoutubeFrag : Fragment() {
    lateinit var binding: FragmentYoutubeBinding
    lateinit var dataList: MutableList<DataV0>
    lateinit var customAdapter: AlbumCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoutubeBinding.inflate(inflater, container, false)

        dataList = mutableListOf<DataV0>()

        dataList.add(DataV0(R.drawable.album1, R.string.album_kind1, R.string.album_name1, R.string.singer, R.string.title_song1, R.string.info1))
        dataList.add(DataV0(R.drawable.album2, R.string.album_kind2, R.string.album_name2, R.string.singer, R.string.title_song2, R.string.info2))
        dataList.add(DataV0(R.drawable.album3, R.string.album_kind3, R.string.album_name3, R.string.singer, R.string.title_song3, R.string.info3))
        dataList.add(DataV0(R.drawable.album4, R.string.album_kind4, R.string.album_name4, R.string.singer, R.string.title_song4, R.string.info4))
        dataList.add(DataV0(R.drawable.album5, R.string.album_kind5, R.string.album_name5, R.string.singer, R.string.title_song5, R.string.info5))
        dataList.add(DataV0(R.drawable.album6, R.string.album_kind6, R.string.album_name6, R.string.singer, R.string.title_song6, R.string.info6))
        dataList.add(DataV0(R.drawable.album7, R.string.album_kind7, R.string.album_name7, R.string.singer, R.string.title_song7, R.string.info7))
        dataList.add(DataV0(R.drawable.album8, R.string.album_kind8, R.string.album_name8, R.string.singer, R.string.title_song8, R.string.info8))
        dataList.add(DataV0(R.drawable.album9, R.string.album_kind9, R.string.album_name9, R.string.singer, R.string.title_song9, R.string.info9))
        dataList.add(DataV0(R.drawable.album10, R.string.album_kind10, R.string.album_name10, R.string.singer, R.string.title_song10, R.string.info10))
        dataList.add(DataV0(R.drawable.album11, R.string.album_kind11, R.string.album_name11, R.string.singer, R.string.title_song11, R.string.info11))
        dataList.add(DataV0(R.drawable.album12, R.string.album_kind12, R.string.album_name12, R.string.singer, R.string.title_song12, R.string.info12))
        dataList.add(DataV0(R.drawable.album13, R.string.album_kind13, R.string.album_name13, R.string.singer, R.string.title_song13, R.string.info13))
        dataList.add(DataV0(R.drawable.album14, R.string.album_kind14, R.string.album_name14, R.string.singer, R.string.title_song14, R.string.info14))
        dataList.add(DataV0(R.drawable.album15, R.string.album_kind15, R.string.album_name15, R.string.singer, R.string.title_song15, R.string.info15))
        dataList.add(DataV0(R.drawable.album16, R.string.album_kind16, R.string.album_name16, R.string.singer, R.string.title_song16, R.string.info16))
        dataList.add(DataV0(R.drawable.album17, R.string.album_kind17, R.string.album_name17, R.string.singer, R.string.title_song17, R.string.info17))
        dataList.add(DataV0(R.drawable.album18, R.string.album_kind18, R.string.album_name18, R.string.singer, R.string.title_song18, R.string.info18))
        dataList.add(DataV0(R.drawable.album19, R.string.album_kind19, R.string.album_name19, R.string.singer, R.string.title_song19, R.string.info19))
        dataList.add(DataV0(R.drawable.album20, R.string.album_kind20, R.string.album_name20, R.string.singer, R.string.title_song20, R.string.info20))
        dataList.add(DataV0(R.drawable.album21, R.string.album_kind21, R.string.album_name21, R.string.singer, R.string.title_song21, R.string.info21))
        dataList.add(DataV0(R.drawable.album22, R.string.album_kind22, R.string.album_name22, R.string.singer, R.string.title_song22, R.string.info22))
        dataList.add(DataV0(R.drawable.album23, R.string.album_kind23, R.string.album_name23, R.string.singer, R.string.title_song23, R.string.info23))
        dataList.add(DataV0(R.drawable.album24, R.string.album_kind24, R.string.album_name24, R.string.singer, R.string.title_song24, R.string.info24))
        dataList.add(DataV0(R.drawable.album25, R.string.album_kind25, R.string.album_name25, R.string.singer, R.string.title_song25, R.string.info25))

        val layoutManager: LinearLayoutManager = selectLayoutManager(1)

        customAdapter = AlbumCustomAdapter(dataList, binding.root.context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = AlbumCustomAdapter(dataList, binding.root.context)
        binding.recyclerView.addItemDecoration(MyDecoration(binding.root.context))

        return binding.root
    }

    private fun selectLayoutManager(i: Int): LinearLayoutManager {
        lateinit var layoutManager: LinearLayoutManager
        if(i == 1){
            layoutManager = LinearLayoutManager(binding.root.context)
        }else if(i == 2){
            layoutManager = LinearLayoutManager(binding.root.context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            return  layoutManager
        }
        return layoutManager
    }
}

