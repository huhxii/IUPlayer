package com.example.iuplayer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.iuplayer.databinding.ConListBinding

class ConcertCustomAdapter(val dataList: MutableList<DataV2>, val parentContext: Context): RecyclerView.Adapter<ConcertCustomAdapter.CustomViewHolder>() {

    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var binding = ConListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {
        val binding = customViewHolder.binding
        val data = dataList.get(position)

        binding.conIv.setImageResource(data.image)
        binding.conTvName.setText(data.name)
        binding.conTvPlace.setText(data.place)
        binding.conTvDate.setText(data.date)
        binding.conTvTime.setText(data.time)

        binding.conIv.setOnClickListener {
            when(data.image){
                R.drawable.con_love_poem -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=10240429&qvt=0&query=2019%20IU%20Tour%20Concert%20Love%20Poem%20%EC%84%9C%EC%9A%B8")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.drawable.con_dlwlrma -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=8756181&qvt=0&query=2018%20%EC%95%84%EC%9D%B4%EC%9C%A0%2010%EC%A3%BC%EB%85%84%20%ED%88%AC%EC%96%B4%20%EC%BD%98%EC%84%9C%ED%8A%B8%20dlwlrma%20%EC%84%9C%EC%9A%B8")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.drawable.con_palette -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=6369180&qvt=0&query=2017%20%EC%95%84%EC%9D%B4%EC%9C%A0%20%ED%88%AC%EC%96%B4%20%EC%BD%98%EC%84%9C%ED%8A%B8%20%EC%84%9C%EC%9A%B8")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.drawable.con_one_two_three_four -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=4483275&qvt=0&query=2016%20%EC%95%84%EC%9D%B4%EC%9C%A0%20%EC%BD%98%EC%84%9C%ED%8A%B8%20%EC%8A%A4%EB%AC%BC%EB%84%A4%20%EA%B1%B8%EC%9D%8C%20%ED%95%98%EB%82%98%20%EB%91%98%20%EC%85%8B%20%EB%84%B7")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.drawable.con_chat_shire -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=3231923&qvt=0&query=%EC%95%84%EC%9D%B4%EC%9C%A0%EC%BD%98%EC%84%9C%ED%8A%B8%EC%84%9C%EC%9A%B82015")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.drawable.con_one_step -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=9893452&qvt=0&query=%EC%95%84%EC%9D%B4%EC%9C%A0%EC%BD%98%EC%84%9C%ED%8A%B8")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.drawable.con_modern_times -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=9889321&qvt=0&query=%EC%95%84%EC%9D%B4%EC%9C%A0%EC%BD%98%EC%84%9C%ED%8A%B8")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.drawable.con_real_fantasy -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=mbg_nex.people&mra=bkkw&pkid=269&os=9877776&qvt=0&query=%EC%95%84%EC%9D%B4%EC%9C%A0%EC%BD%98%EC%84%9C%ED%8A%B8")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
            }
        }
    }
    //내부 클래스
    class CustomViewHolder(val binding: ConListBinding): RecyclerView.ViewHolder(binding.root)
}

private fun ImageView.setImageResource(image: String) { }