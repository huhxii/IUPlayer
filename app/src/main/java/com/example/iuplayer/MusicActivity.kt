package com.example.iuplayer

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iuplayer.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity() {
    companion object{
        val REQ_READ = 99
        val DB_NAME = "iuMusicDB"
        var VERSION = 1
    }
    lateinit var binding: ActivityMusicBinding
    lateinit var adapter: MusicRecyclerAdapter
    private var musicList: MutableList<Music>? = mutableListOf<Music>()
    var myList: MutableList<Music>? = mutableListOf<Music>()

    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //승인이 되었는지 점검
        if(isPermitted()){
            Log.d("finding error","onCreate isPermitted() startProcess()")
            startProcess()
        }else{
            //외부저장소 읽기 권한이 없다면, 유저에게 읽기권한 신청
            Log.d("finding error","onCreate isPermitted() else")
            ActivityCompat.requestPermissions(this, permissions, REQ_READ)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQ_READ && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.d("finding error","onCreate onRequestPermissionsResult() startProcess()")
            startProcess()
        }else{
            Log.d("finding error","onCreate onRequestPermissionsResult() else")
            Toast.makeText(this, "권한요청을 승인 하셔야 뮤직플레이어앱 실행 가능합니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun startProcess() {
        //먼저 데이타베이스에서 음원정보를 가져온다. 없으면 공유메모리에서 음원정보 가져온다.
        val dbHelper = DBHelper(this, DB_NAME, VERSION)
        musicList = dbHelper.selectMusicAll()
        //만약 데이터베이스 없으면 콘텐트리졸버를 통해서 공유메모리에서 음원정보를 가져온다.
        if(musicList == null){
            //1~3 내용 모듈화
            var playMusicList = getMusicList()
            if(playMusicList != null){
                for(i in 0..playMusicList.size -1){
                    val music = playMusicList.get(i)
                    dbHelper.insertMusic(music)
                }
                Log.d("finding error","playMusicList != null startProcess()")
                musicList = playMusicList
            }else{
                Log.d("finding error","playMusicList == null startProcess()")
            }
        }
        //5. 리사이클러 뷰에 제공
        Log.d("finding error","adapter")
        Log.d("finding error","${musicList?.get(0).toString()}")
        adapter = MusicRecyclerAdapter(this, musicList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        Log.d("finding error","뿌리기 성공")
    }

    private fun getMusicList(): MutableList<Music>? {
        //1. 공유메모리에서 음원정보주소
        val musicURL = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        //2. 음원에서 가져올 정보를 배열
        val projection = arrayOf(
            MediaStore.Audio.Media._ID, //앨범 아이디
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )
        //3. 콘텐트리졸버 쿼리문 작성
        val cursor = contentResolver.query(musicURL, projection, null, null, null) // select/from/where/values/sort
        Log.d("finding error","${cursor.toString()}")
        if(cursor?.count!! > 0){
            //4. 음원정보 가져오기
            while(cursor!!.moveToNext()){
                val id = cursor.getString(0)
                val title = cursor.getString(1).replace("'", "")
                val artist = cursor.getString(2).replace("'", "")
                val albumID = cursor.getString(3).replace("'", "")
                val duration = cursor.getInt(4)
                Log.d("finding error","${id}, ${title}, ${artist}, ${albumID}, ${duration}")
                val music = Music(id, title, artist, albumID, duration, 0)
                Log.d("finding error", "Music ${music.toString()}")
                //데이터베이스 음원정보를 입력한다.
//                musicList?.add(music)
                myList?.add(music)
                musicList = myList
                Log.d("finding error","musicList size ${musicList?.size}")
                Log.d("finding error","myList size ${myList?.size}")
            }
        }else{
            musicList = null
            Log.d("finding error","getMusicList else")
        }
        return musicList
    }

    private fun isPermitted(): Boolean{
        if(ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED){
            return false
        }else{
            return true
        }
    }

    //메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        //메뉴에서 서치항목 찾음
        val searchMenu = menu?.findItem(R.id.menu_search)
        val searchView = searchMenu?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //한글자 칠 때마다 검색기능이 발생함
            override fun onQueryTextChange(query: String?): Boolean {
                val dbHelper = DBHelper(applicationContext, MusicActivity.DB_NAME, MusicActivity.VERSION)
                if(query.isNullOrBlank()){
                    musicList?.clear() // 뮤직리스트를 싹 지운다.
                    dbHelper.selectMusicAll()?.let{ musicList?.addAll(it)}
                    adapter.notifyDataSetChanged()
                }else{
                    musicList?.clear() // 뮤직리스트를 싹 지운다.
                    dbHelper.searchMusic(query)?.let{ musicList?.addAll(it)}
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dbHelper = DBHelper(applicationContext, MusicActivity.DB_NAME, MusicActivity.VERSION)
        when(item.itemId){
            R.id.menu_like -> {
                musicList?.clear() // 뮤직리스트를 싹 지운다.
                dbHelper.selectMusicLike()?.let{ musicList?.addAll(it) }
                adapter.notifyDataSetChanged()
            }
            R.id.menu_main -> {
                musicList?.clear() // 뮤직리스트를 싹 지운다.
                dbHelper.selectMusicAll()?.let{ musicList?.addAll(it) }
                adapter.notifyDataSetChanged()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}