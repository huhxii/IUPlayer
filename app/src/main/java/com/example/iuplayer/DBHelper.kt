package com.example.iuplayer

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context, dbName: String, version: Int): SQLiteOpenHelper(context, dbName, null, version) {
    //DBHelper 처음 객체가 만들어질 때 딱 한번만 실행된다.
    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            create table iumusicTBL(
                id text primary key,
                title text,
                artist text,
                albumId text,
                duration Int,
                likes Int
            )
        """.trimIndent()
        db?.execSQL(query)
    }
    //버전이 변경되었을 때 불러지는 함수
    override fun onUpgrade(db: SQLiteDatabase?, newVersion: Int, oldVersion: Int) {
        val query = """
            drop table if exists iumusicTBL
        """.trimIndent()
        db?.execSQL(query)
    }

    fun selectMusicAll(): MutableList<Music>? {
        var musicList: MutableList<Music>? = mutableListOf<Music>()
        var cursor: Cursor? = null
        val query = """
            select * from iumusicTBL
        """.trimIndent()
        val db = this.readableDatabase
        try{
            cursor = db.rawQuery(query, null)
            if(cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getString(0)
                    val title = cursor.getString(1)
                    val artist = cursor.getString(2)
                    val albumId = cursor.getString(3)
                    val duration = cursor.getInt(4)
                    val likes = cursor.getInt(5)
                    val music = Music(id, title, artist, albumId, duration, likes)
                    Log.d("finding error","selectMusicAll if(cursor.count > 0)")
                    musicList?.add(music)
                }
            }else{
                musicList = null
                Log.d("finding error","selectMusicAll else")
            }
        }catch (e: Exception){
            Log.d("finding error","DBHelper.selectMusicAll() ${e.toString()}")
        }finally {
            cursor?.close()
        }
        return musicList
    }

    fun insertMusic(music: Music): Boolean {
        var flag = false
        val query = """
            insert into iumusicTBL(id, title, artist, albumId, duration, likes) 
            values ('${music.id}', '${music.title}', '${music.artist}', '${music.albumId}', ${music.duration}, ${music.likes})
        """.trimIndent()
        val db = this.writableDatabase
        try{
            Log.d("finding error","insertMusic()")
            db.execSQL(query)
            flag = true
        }catch (e: Exception){
            Log.d("finding error","insertMusic() ${e.toString()}")
            flag = false
        }
        return flag
    }

    fun updateLike(music: Music): Boolean {
        var flag = false
        val query = """
            update iumusicTBL set likes = ${music.likes} where id = '${music.id}'
        """.trimIndent()
        val db = this.writableDatabase
        try{
            db.execSQL(query)
            flag = true
        }catch (e: Exception){
            Log.d("iuplayer","DBHelper.updateLike() ${e.printStackTrace()}")
            flag = false
        }
        return flag
    }

    fun searchMusic(query: String?): MutableList<Music>? { // MutableList<Music>? = 리턴값
        var musicList: MutableList<Music>? = mutableListOf<Music>()
        var cursor: Cursor? = null
        val query = """
            select * from iumusicTBL where title like '${query}%' or artist like '${query}%'  
                    """.trimIndent() // like = 유사한, % = 포함
        val db = this.readableDatabase

        //DB쪽이라 오류가 발생할 수 있음. 그래서 try catch 사용
        try {
            cursor = db.rawQuery(query, null)
            if(cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getString(0)
                    val title = cursor.getString(1)
                    val artist = cursor.getString(2)
                    val albumID = cursor.getString(3)
                    val duration = cursor.getInt(4)
                    val likes = cursor.getInt(5)
                    val music = Music(id, title, artist, albumID,duration,likes)
                    musicList?.add(music)
                }
            }else{
                musicList = null
            }
        }catch (e : Exception){
            Log.d("iuplayer", "DBHelper.searchMusic() ${e.printStackTrace()}")
            musicList = null
        }finally {
            cursor?.close()
        }
        return musicList
    }

    fun selectMusicLike(): MutableList<Music>? {
        var musicList: MutableList<Music>? = mutableListOf<Music>()
        var cursor: Cursor? = null
        val query = """
            select * from iumusicTBL where likes = 1  
                    """.trimIndent() // like = 유사한, % = 포함
        val db = this.readableDatabase

        //DB쪽이라 오류가 발생할 수 있음. 그래서 try catch 사용
        try {
            cursor = db.rawQuery(query, null)
            if(cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getString(0)
                    val title = cursor.getString(1)
                    val artist = cursor.getString(2)
                    val albumID = cursor.getString(3)
                    val duration = cursor.getInt(4)
                    val likes = cursor.getInt(5)
                    val music = Music(id, title, artist, albumID,duration,likes)
                    musicList?.add(music)
                }
            }else{
                musicList = null
            }
        }catch (e : Exception){
            Log.d("iuplayer", "DBHelper.selectMusicLike() ${e.printStackTrace()}")
            musicList = null
        }finally {
            cursor?.close()
        }
        return musicList
    }
}