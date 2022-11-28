package com.example.iuplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iuplayer.databinding.FragmentConcertBinding

class ConcertFrag : Fragment() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: FragmentConcertBinding
    lateinit var dataList : MutableList<DataV2>
    lateinit var customAdapter: ConcertCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConcertBinding.inflate(inflater, container, false)

//        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_opened, R.string.drawer_closed)
//        toggle.syncState()

        dataList = mutableListOf<DataV2>()

        dataList.add(DataV2(R.drawable.con_love_poem, R.string.con_name1, R.string.con_place1, R.string.con_date1, R.string.con_time1))
        dataList.add(DataV2(R.drawable.con_dlwlrma, R.string.con_name2, R.string.con_place2, R.string.con_date2, R.string.con_time2))
        dataList.add(DataV2(R.drawable.con_palette, R.string.con_name3, R.string.con_place3, R.string.con_date3, R.string.con_time3))
        dataList.add(DataV2(R.drawable.con_one_two_three_four, R.string.con_name4, R.string.con_place4, R.string.con_date4, R.string.con_time4))
        dataList.add(DataV2(R.drawable.con_chat_shire, R.string.con_name5, R.string.con_place5, R.string.con_date5, R.string.con_time5))
        dataList.add(DataV2(R.drawable.con_one_step, R.string.con_name6, R.string.con_place6, R.string.con_date6, R.string.con_time6))
        dataList.add(DataV2(R.drawable.con_modern_times, R.string.con_name7, R.string.con_place7, R.string.con_date7, R.string.con_time7))
        dataList.add(DataV2(R.drawable.con_real_fantasy, R.string.con_name8, R.string.con_place8, R.string.con_date8, R.string.con_time8))

        val layoutManager: LinearLayoutManager = selectLayoutManager(1)

        customAdapter = ConcertCustomAdapter(dataList, binding.root.context)
        binding.conRecyclerView.layoutManager = layoutManager
        binding.conRecyclerView.setHasFixedSize(true)
        binding.conRecyclerView.adapter = ConcertCustomAdapter(dataList, binding.root.context)
        binding.conRecyclerView.addItemDecoration(MyDecoration(binding.root.context))

        binding.ivAppbar.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=28607989&qvt=0&query=2022%20IU%20CONCERT%20The%20Golden%20Hour%20%EC%98%A4%EB%A0%8C%EC%A7%80%20%ED%83%9C%EC%96%91%20%EC%95%84%EB%9E%98")
            ContextCompat.startActivity(binding.root.context, intent, null)
        }

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