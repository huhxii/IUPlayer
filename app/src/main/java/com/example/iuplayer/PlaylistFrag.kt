package com.example.iuplayer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iuplayer.databinding.FragmentPlaylistBinding

class PlaylistFrag : Fragment() {
    lateinit var binding: FragmentPlaylistBinding
    lateinit var dataList: MutableList<DataV1>
    lateinit var customAdapter: PlayCustomAdapter
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        dataList = mutableListOf<DataV1>()

        binding.plFab.setOnClickListener{
            val items = arrayOf<String>("미아", "Boo", "마쉬멜로우", "잔소리", "그대네요", "좋은날", "나만 몰랐던 이야기", "너랑나",
            "하루 끝", "분홍신", "금요일에 만나요", "봄 사랑 벚꽃말고", "나의 옛날 이야기", "소격동", "마음", "스물셋", "팔레트", "잠 못 드는 밤 비는 내리고",
            "삐삐", "Blueming", "첫 겨울이니까", "에잇", "라일락", "strawberry moon", "겨울잠", "다른노래")

            val eventHandler = object : DialogInterface.OnClickListener {
                override fun onClick(userDialog: DialogInterface?, position: Int) {
//                    if (position == DialogInterface.BUTTON_POSITIVE) { Toast.makeText(binding.root.context, "앨범을 선택하세요", Toast.LENGTH_SHORT).show() }
                }
            }

            AlertDialog.Builder(binding.root.context).run {
                setTitle("앨범 선택")
                setIcon(R.drawable.ic_album_24)
                setItems(items, object : DialogInterface.OnClickListener {
                    override fun onClick(dialogInterface: DialogInterface?, index: Int) {
                        when(items[index]){
                            "미아" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album1, R.string.album_kind1, R.string.album_name1, R.string.singer, R.string.title_song1))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "Boo" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album2, R.string.album_kind2, R.string.album_name2, R.string.singer, R.string.title_song2))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "마쉬멜로우" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album3, R.string.album_kind3, R.string.album_name3, R.string.singer, R.string.title_song3))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "잔소리" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album4, R.string.album_kind4, R.string.album_name4, R.string.singer, R.string.title_song4))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "그대네요" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album5, R.string.album_kind5, R.string.album_name5, R.string.singer, R.string.title_song5))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "좋은날" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album6, R.string.album_kind6, R.string.album_name6, R.string.singer, R.string.title_song6))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "나만 몰랐던 이야기" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album7, R.string.album_kind7, R.string.album_name7, R.string.singer, R.string.title_song7))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "너랑나" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album8, R.string.album_kind8, R.string.album_name8, R.string.singer, R.string.title_song8,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "하루 끝" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album9, R.string.album_kind9, R.string.album_name9, R.string.singer, R.string.title_song9,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "분홍신" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album10, R.string.album_kind10, R.string.album_name10, R.string.singer, R.string.title_song10,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "금요일에 만나요" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album11, R.string.album_kind11, R.string.album_name11, R.string.singer, R.string.title_song11,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "봄 사랑 벚꽃말고" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album12, R.string.album_kind12, R.string.album_name12, R.string.singer, R.string.title_song12,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "나의 옛날 이야기" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album13, R.string.album_kind13, R.string.album_name13, R.string.singer, R.string.title_song13,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "소격동" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album14, R.string.album_kind14, R.string.album_name14, R.string.singer, R.string.title_song14,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "마음" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album15, R.string.album_kind15, R.string.album_name15, R.string.singer, R.string.title_song15,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "스물셋" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album16, R.string.album_kind16, R.string.album_name16, R.string.singer, R.string.title_song16,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "팔레트" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album17, R.string.album_kind17, R.string.album_name17, R.string.singer, R.string.title_song17,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "잠 못 드는 밤 비는 내리고" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album18, R.string.album_kind18, R.string.album_name18, R.string.singer, R.string.title_song18,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "삐삐" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album19, R.string.album_kind19, R.string.album_name19, R.string.singer, R.string.title_song19,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "Blueming" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album20, R.string.album_kind20, R.string.album_name20, R.string.singer, R.string.title_song20,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "첫 겨울이니까" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]를 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album21, R.string.album_kind21, R.string.album_name21, R.string.singer, R.string.title_song21,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "에잇" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album22, R.string.album_kind22, R.string.album_name22, R.string.singer, R.string.title_song22,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "라일락" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album23, R.string.album_kind23, R.string.album_name23, R.string.singer, R.string.title_song23,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "strawberry moon" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album24, R.string.album_kind24, R.string.album_name24, R.string.singer, R.string.title_song24,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)

                                mediaPlayer = MediaPlayer.create(binding.root.context, R.raw.song24)
                            }
                            "겨울잠" -> {
//                                Toast.makeText(binding.root.context, "[${items[index]}]을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                                dataList.add(DataV1(R.drawable.album25, R.string.album_kind25, R.string.album_name25, R.string.singer, R.string.title_song25,))
                                val layoutManager: LinearLayoutManager = selectLayoutManager(1)

                                customAdapter = PlayCustomAdapter(dataList, binding.root.context)
                                binding.plRecyclerView.layoutManager = layoutManager
                                binding.plRecyclerView.setHasFixedSize(true)
                                binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
                            }
                            "다른노래" -> {
                                activity?.let{
                                    val intent = Intent(this@PlaylistFrag.context, MusicActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                        // **/
                    }
                })
                setPositiveButton("닫기", eventHandler)
                show()
            }
        }
        return binding.root
    }

    private fun selectLayoutManager(i: Int): LinearLayoutManager {
        lateinit var layoutManager: LinearLayoutManager
        if (i == 1) {
            layoutManager = LinearLayoutManager(binding.root.context)
        } else if (i == 2) {
            layoutManager = LinearLayoutManager(binding.root.context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            return layoutManager
        }
        return layoutManager
    }

    fun refreshRecyclerViewDrop(dataV1: DataV1) {
        Toast.makeText(binding.root.context, "${getText(dataV1.song)} 삭제 ", Toast.LENGTH_SHORT).show()
        dataList.remove(dataV1)
        //화면 안가려 졌지만 가려진것처럼 헷갈리게 함
        customAdapter.notifyDataSetChanged()

//        binding.plRecyclerView.setHasFixedSize(true)
        binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
        binding.plRecyclerView.addItemDecoration(MyDecoration(binding.root.context))
    }
    /**
    fun bindingRecyclerView() {
        val layoutManager: LinearLayoutManager = selectLayoutManager(1)

        customAdapter = PlayCustomAdapter(dataList, binding.root.context)
        binding.plRecyclerView.layoutManager = layoutManager
        binding.plRecyclerView.setHasFixedSize(true)
        binding.plRecyclerView.adapter = PlayCustomAdapter(dataList, binding.root.context)
        binding.plRecyclerView.addItemDecoration(MyDecoration(binding.root.context))
    }
    // **/
}