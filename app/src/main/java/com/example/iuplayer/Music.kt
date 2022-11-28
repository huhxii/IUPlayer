package com.example.iuplayer

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Parcel
import android.os.ParcelFileDescriptor
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(val id: String, val title: String?, val artist: String?, val albumId: String?, val duration: Int?, var likes: Int?)
    : Parcelable{
    //Serializable -> Parcelable로 하는 이유 : 속도처리, 용량처리
    companion object : Parceler<Music>{
        override fun create(parcel: Parcel): Music {
            Log.d("finding error", "Music Data Class")
            return Music(parcel)
        }

        override fun Music.write(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(title)
            parcel.writeString(artist)
            parcel.writeString(albumId)
            parcel.writeInt(duration!!)
            parcel.writeInt(likes!!)
            Log.d("finding error", "Music.write(parcel: Parcel, flags: Int)")
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )

    //앨범 Uri 가져오기
    fun getAlbumUri(): Uri{
        Log.d("finding error", "getAlbumUri()")
        return Uri.parse("content://media/external/audio/albumart/"+albumId)
    }

    //음악 Uri 가져오기
    fun getMusicUri(): Uri{
        Log.d("finding error", "getMusicUri()")
        return Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id) //음악위치를 가져온다
    }

    //음악 비트맵을 가져와서 원하는 사이즈로 비트맵 만들기
    fun getAlbumImage(context: Context, albumImageSize: Int): Bitmap?{//음악 파일에 앨범이 없을 수도 있으니 Bitmap에 nullException(?)을 넣어준다
        val contentResolver: ContentResolver = context.contentResolver
        val uri = getAlbumUri() //앨범 Uri(경로) 가져오기
        //비트맵 오션
        val options = BitmapFactory.Options()

        if(uri != null){
            var parcelFileDescriptor: ParcelFileDescriptor? = null
            try{ //외부에서 가져오니까 try,catch사용
                parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
                var bitmap = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor!!.fileDescriptor, null, options)
                //비트맵을 가져왔는데 우리가 원하는 사이즈가 아닐경우를 위해서 처리
                if(bitmap != null){
                    val tempBitmap = Bitmap.createScaledBitmap(bitmap, albumImageSize, albumImageSize, true)
                    bitmap.recycle()
                    bitmap = tempBitmap
                }
                return bitmap
            }catch (e: java.lang.Exception){
                Log.d("finding error", "getAlbumImage() ${e.toString()}")
            }finally {
                try{
                    parcelFileDescriptor?.close()
                }catch (e: Exception){
                    Log.d("finding error", "getAlbumImage() parcelFileDescriptor ${e.printStackTrace()}")
                }
            }
        }
        return null
    }
}