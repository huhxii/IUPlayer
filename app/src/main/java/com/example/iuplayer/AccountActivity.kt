package com.example.iuplayer

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.DatePicker
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.iuplayer.databinding.ActivityAccountBinding
import java.io.IOException

class AccountActivity : AppCompatActivity() {
    var year: Int = 0
    var month: Int = 0
    var date: Int = 0
    lateinit var binding: ActivityAccountBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val requestGalleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            //it.data.data : it.data == intent -> intent.data (data : 이미지 Uri)
            //비율계산
            val calRatio = calculateInSampleSize(it.data!!.data!!, resources.getDimensionPixelSize(R.dimen.imgSize), resources.getDimensionPixelSize(R.dimen.imgSize))
            //실제 이미지를 비율대로 가져온다.
            val options = BitmapFactory.Options()
            options.inSampleSize = calRatio
            try{
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                var bitmap = BitmapFactory.decodeStream(inputStream, null, options)

                //사진의 회전 정보 가져오기
                val orientation = getOrientationOfImage(it.data!!.data!!).toFloat()
                //회전된 사진을 원 위치로 돌린 비트맵 가져오기기
                val newBitmap = getRotatedBitmap(bitmap, orientation)

                bitmap?.let{
                    binding.acIvPerson.setImageBitmap(newBitmap)
                }?:let{
                    Log.d("iuplayer","갤러리앱으로부터 가져온 이미지는 null 입니다.")
                }
//                if(bitmap != null){
//                    binding.ivPicture.setImageResource(bitmap)
//                }else{
//                    Log.d("pictureprovide","갤러리앱으로부터 가져온 이미지는 null 입니다.")
//                }
            }catch(e: java.lang.Exception){
                Log.d("iuplayer","${e.printStackTrace()}")
            }
        }

        binding.acIvPerson.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        binding.acEdtBirth.setOnClickListener {
            DatePickerDialog(binding.root.context, object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(datePicker: DatePicker?, yyyy: Int, MM: Int, dd: Int) {
                    year = yyyy
                    month = MM
                    date = dd
                    if(date < 10){
                        binding.acEdtBirth.setText("${year}.${month}.0${date}")
                    }else{
                        binding.acEdtBirth.setText("${year}.${month}.${date}")
                    }
                    binding.acEdtAge.setText("${2022 - yyyy + 1}세").toString()
                }
            }, 2002, 6, 1).show()
        }

        binding.acIvHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }

    }

    //비율계산
    fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int{
        //이미지 옵션 : 아파트 옵션 베란다 축소
        val options = BitmapFactory.Options()
        //이미지 정보만 가져와서 실제사이즈와 요청사이즈 계산해서 비율조정하기 위함.
        options.inJustDecodeBounds = true

        try{
            //해당되는 이미지를 가져오기 위해서 InputStream
            var inputStream = contentResolver.openInputStream(fileUri)
            //실제이미지 정보를 options에 저장함
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()
            inputStream = null
        }catch(e: java.lang.Exception){
            Log.d("iuplayer", "${e.printStackTrace()}")
        }
        //화면 비율 계산
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if(height > reqHeight || width > reqWidth){
            val halfHeight = height / 2
            var halfWidth = width / 2
            while(halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth){
                inSampleSize *= 2
            }
        }
        return 0
    }

    // 이미지 회전 정보 가져오기
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getOrientationOfImage(uri: Uri): Int {
        // uri -> inputStream
        val inputStream = contentResolver.openInputStream(uri)
        val exif: ExifInterface? = try {
            ExifInterface(inputStream!!)
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }
        inputStream.close()

        // 회전된 각도 알아내기
        val orientation = exif?.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        if (orientation != -1) {
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> return 90
                ExifInterface.ORIENTATION_ROTATE_180 -> return 180
                ExifInterface.ORIENTATION_ROTATE_270 -> return 270
            }
        }
        return 0
    }

    // 이미지 회전하기
    @Throws(Exception::class)
    private fun getRotatedBitmap(bitmap: Bitmap?, degrees: Float): Bitmap? {
        if (bitmap == null) return null
        if (degrees == 0F) return bitmap
        val m = Matrix()
        m.setRotate(degrees, bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, m, true)
    }

//    override fun onStart() {
//        super.onStart()
//    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString("image", binding.acIvPerson.setImageURI(intent.data).toString())
        savedInstanceState.getString("acEdtName", binding.acEdtName.text.toString())
        savedInstanceState.getString("acEdtBirth", binding.acEdtBirth.text.toString())
        savedInstanceState.getString("acEdtAge", binding.acEdtAge.text.toString())
        savedInstanceState.getString("acEdtPhone", binding.acEdtPhone.text.toString())
    }

//    override fun onResume() {
//        super.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//    }
//
//    override fun onStop() {
//        super.onStop()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("image", "${binding.acIvPerson.setImageURI(intent.data).toString()}")
        outState.putString("acEdtName", "${binding.acEdtName.text.toString()}")
        outState.putString("acEdtBirth", "${binding.acEdtBirth.text.toString()}")
        outState.putString("acEdtAge", "${binding.acEdtAge.text.toString()}")
        outState.putString("acEdtPhone", "${binding.acEdtPhone.text.toString()}")
    }
}