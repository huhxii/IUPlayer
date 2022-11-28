package com.example.iuplayer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.iuplayer.databinding.YoutubeListBinding

class AlbumCustomAdapter(val dataList: MutableList<DataV0>, val parentContext: Context): RecyclerView.Adapter<AlbumCustomAdapter.CustomViewHolder>() {

    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var binding = YoutubeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {

        val binding = customViewHolder.binding
        val data = dataList.get(position)

        binding.plIvAlbumCover.setImageResource(data.image)
        binding.plTvAlbum.setText(data.album)
        binding.plTvAlbumTitle.setText(data.title)
        binding.plTvSinger.setText(data.singer)
        binding.plTvTitleSong.setText(data.song)
        binding.plTvInfo.setText(data.info)

        binding.plIvAlbumCover.setOnClickListener {
            when(data.image){
                R.drawable.album1 -> { //미아
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=G6GczDUM270")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album2 -> { //Boo
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=g3TP6XZ1Baw")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album3 -> { //마쉬멜로우
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=slT80EySpKk")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album4 -> { //잔소리
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=bzdsqPOJK_I")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album5 -> { //그대네요
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=UczsdpAvpik")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album6 -> { //좋은 날
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=jeqdYqsrsA0")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album7 -> { //나만 몰랐던 이야기
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=UpuWJr821rY")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album8 -> { //너랑 나
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=NJR8Inf77Ac")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album9 -> { //하루 끝
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=gJtNx3P02Z4")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album10 -> { //분홍신
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=XaZptY3DCxk")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album11 -> { //금요일에 만나요
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=EiVmQZwJhsA")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album12 -> { //봄 사랑 벚꽃말고
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=ouR4nn1G9r4")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album13 -> { //나의 옛날 이야기
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=npttud7NkL0")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album14 -> { //소격동
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=GHu39FEFIks")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album15 -> { //마음
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=he2C4lx63M0")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album16 -> { //스물 셋
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=42Gtm4-Ax2U")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album17 -> { //팔레트
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=d9IxdwEFk1c")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album18 -> { //잠 못드는 밤 비는 내리고
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=m7mvpe1fVa4")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album19 -> { //삐삐
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=nM0xDI5R50E")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album20 -> { //Blueming
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=D1PvIWdJ8xo")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album21 -> { //첫 겨울이니까
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=1YZzSkP6KZo")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album22 -> { //에잇
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=TgOu00Mf3kI")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album23 -> { //라일락
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=v7bnOxV4jAc")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album24 -> { //strawberry moon
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=sqgxcCjD04s")
                    startActivity(binding.root.context, intent, null)
                }
                R.drawable.album25 -> { //겨울잠
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=SmQhRHS9-YA")
                    startActivity(binding.root.context, intent, null)
                }
            }
//            when(it.id){
//                R.drawable.album1 -> {
//                    val intent = Intent()
//                    intent.action = Intent.ACTION_VIEW
//                    intent.data = Uri.parse("https://www.youtube.com/watch?v=0ZukHxqOA0o&list=OLAK5uy_nWrK5vBpwOZcwEt4drAZv5PXu9Y9I72YA&index=2")
//                    startActivity(binding.root.context, intent, null)
//                }
//            }
        }

//        customViewHolder.itemView.setOnClickListener {
//            fun onClick(view : View){
//                var pos = customViewHolder.adapterPosition
//                if(pos != RecyclerView.NO_POSITION){
//                    val item = dataList.get(pos)
//
//                }
//            }
//        }

//        customViewHolder.itemView.setOnClickListener {
//            fun viewOnClick(view: View) {
//                when (view.id) {
//                    R.drawable.album1 -> {
//                        val intent = Intent()
//                        intent.action = Intent.ACTION_VIEW
//                        intent.data =
//                            Uri.parse("https://www.youtube.com/watch?v=0ZukHxqOA0o&list=OLAK5uy_nWrK5vBpwOZcwEt4drAZv5PXu9Y9I72YA&index=2")
//                        parentContext.startActivity(intent)
//                    }
//                }
//            }
//        }
    }

//    override fun onBindViewHolder(
//        holder: CustomViewHolder,
//        position: Int,
//        payloads: MutableList<Any>
//    ) {
//        super.onBindViewHolder(holder, position, payloads)
//    }

    //내부 클래스
    class CustomViewHolder(val binding: YoutubeListBinding): RecyclerView.ViewHolder(binding.root)

//    private fun ImageView.setImageResource(image: String) {
//    }
}

