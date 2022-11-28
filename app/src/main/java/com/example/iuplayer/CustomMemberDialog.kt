package com.example.iuplayer

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.iuplayer.databinding.ActivityMainBinding
import com.example.iuplayer.databinding.DialogSignBinding

class CustomMemberDialog(val context: Context, val mainBinding: ActivityMainBinding) {

    private val dialog = Dialog(context)

//    private lateinit var onClickListener: OnDialogClickListener

    //    fun setOnClickListener(onClickListener: OnDialogClickListener) {
//        this.onClickListener = onClickListener
//    }
    fun showDialog() {
        val binding = DialogSignBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        binding.dialogBtnCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}

//        binding.btnSave.setOnClickListener {
//onClickListener.onClicked(CustomData(binding.nameEdit.text.toString(),"kim"))
//메인바인딩으로 하면 내부인터페이스 설계없이 바로 처리할 수 있음.
//            val tvName = binding.edtName.text.toString()
//            val tvAge =  binding.edtAge.text.toString()
//            val tvEmail = binding.edtEmail.text.toString()
//            val dataVO = DataVO(tvName,tvAge,tvEmail, R.drawable.man)
//            (context as MainActivity).refreshRecyclerView(dataVO)
//            dialog.dismiss()
//편법
//            val name = binding.edtName2.text.toString()
//            val age = binding.edtAge.text.toString()
//            val email = binding.edtEmail.text.toString()
//            val phone = binding.edtPhone2.text.toString()
//            if(Math.random().toInt()%2 == 0){
//                dataVO = DataVO(R.drawable.album1, album, title, singer, song, info)
//            }else{
//                dataVO = DataVO(name, age, email, phone, R.drawable.woman)
//            }
//
//            (context as MainActivity).refreshRecyclerView(dataVO)
//            dialog.dismiss()
//
//        }
//    }

//    interface OnDialogClickListener {
//        fun onClicked(dataVO: DataV0)
//    }
