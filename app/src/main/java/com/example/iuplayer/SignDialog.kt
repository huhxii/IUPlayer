package com.example.iuplayer

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.DatePicker
import com.example.iuplayer.databinding.ActivityAccountBinding
import com.example.iuplayer.databinding.ActivityMainBinding
import com.example.iuplayer.databinding.DialogSignBinding
import com.example.iuplayer.databinding.NaviHeaderBinding

class SignDialog(val context: Context, val binding: ActivityAccountBinding) {
    val dialog = Dialog(context)
    var year: Int = 0
    var month: Int = 0
    var date: Int = 0
    lateinit var onDialogClickListener: OnDialogClickListener

    fun showDialog(){

        val binding = DialogSignBinding.inflate(LayoutInflater.from(context))

        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        binding.dialogBtnCancel.setOnClickListener {
            dialog.dismiss()
        }

        binding.dialogIvBirth.setOnClickListener {
            DatePickerDialog(binding.root.context, object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(datePicker: DatePicker?, yyyy: Int, MM: Int, dd: Int) {
                    year = yyyy
                    month = MM
                    date = dd
                    if(date < 10){
                        binding.dialogEdtBirth.setText("${year}.${month}.0${date}")
                    }else{
                        binding.dialogEdtBirth.setText("${year}.${month}.${date}")
                    }
                    binding.dialogEdtAge.setText("${2022 - yyyy + 1}세").toString()
                }
            }, 2002, 6, 1).show()
        }

        /**
        binding.dialogBtnOk.setOnClickListener {
//            val intent = Intent(binding.root.context, MainActivity::class.java)
//            intent.putExtra("nh_name", binding.dialogEdtName.text.toString())
//            intent.putExtra("nh_birth", binding.dialogEdtBirth.text.toString())
//            intent.putExtra("nh_age", binding.dialogEdtAge.text.toString())
//            intent.putExtra("nh_phone", binding.dialogEdtPhone.text.toString())

//            val accountBinding = ActivityAccountBinding.inflate(LayoutInflater.from(context))
//            accountBinding.acEdtName.text = binding.dialogEdtName.text.toString()
//            accountBinding.acEdtBirth.text = binding.dialogEdtBirth.text.toString()
//            accountBinding.acEdtAge.text = binding.dialogEdtAge.text.toString()
//            accountBinding.acEdtPhone.text = binding.dialogEdtPhone.text.toString()
//
//            dialog.dismiss()

//            naviBinding.drawEdtName.setText(binding.dialogEdtName.text)
//            naviBinding.drawEdtBirth.setText(binding.dialogEdtBirth.text)
//            naviBinding.drawEdtAge.setText(binding.dialogEdtAge.text)
//            naviBinding.drawEdtPhone.setText(binding.dialogEdtPhone.text)

//            var name = binding.dialogEdtName.text.toString()
//            var birth = binding.dialogEdtBirth.text.toString()
//            var age = binding.dialogEdtAge.text.toString()
//            var phone = binding.dialogEdtPhone.text.toString()
//            onDialogClickListener.onClicked(DataV3(name, birth, age, phone))

//            val intent = Intent(binding.root.context, Navigation::class.java)
//            intent.putExtra("name", "${binding.dialogEdtName.text}")
//            intent.putExtra("birth", "${binding.dialogEdtBirth.text}")
//            intent.putExtra("age", "${binding.dialogEdtAge.text}")
//            intent.putExtra("phone", "${binding.dialogEdtPhone.text}")
//            binding.root.context.startActivity(intent)
        }
        // **/
    }

    fun setOnClickListener(onDialogClickListener: OnDialogClickListener){
        this.onDialogClickListener = onDialogClickListener
    }

    interface OnDialogClickListener{
        fun onClicked(dataV3: DataV3)
    }

    /**
    fun viewOnClick(view: View){
        var year: Int = 0
        var month: Int = 0
        var date: Int = 0
        var name = naviBinding.drawEdtName.text.toString()
        var birth = naviBinding.drawEdtBirth.text.toString()
        var age = naviBinding.drawEdtAge.text.toString()
        var phone = naviBinding.drawEdtPhone.text.toString()
        val dataV3 = DataV3(name, birth, age, phone)
        when(view.id){
            R.id.dialog_edt_name -> {
                name = dialogSignBinding.dialogEdtName.text.toString()
            }
            R.id.dialog_iv_birth -> {
                DatePickerDialog(dialogSignBinding.root.context, object: DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(datePicker: DatePicker?, yyyy: Int, MM: Int, dd: Int) {
                        year = yyyy
                        month = MM
                        date = dd
                        Log.d("IU Player", "year: ${year}, month: ${month}, date: ${date}")
                        if(date < 10){
                            birth = dialogSignBinding.dialogEdtBirth.setText("${year}/${month}/0${date}").toString()
                        }else{
                            birth = dialogSignBinding.dialogEdtBirth.setText("${year}/${month}/${date}").toString()
                        }
                        age = dialogSignBinding.dialogEdtAge.setText("${2022 - yyyy + 1}").toString()
                    }
                }, 2002, 6, 1).show()
            }
            R.id.dialog_edt_phone -> {
                phone = dialogSignBinding.dialogEdtPhone.toString()
            }
            R.id.dialog_btn_cancel -> {
                dialog.dismiss()
            }
            R.id.dialog_btn_ok -> {
                naviBinding.root.context
                dialog.dismiss()
            }
        }
    }
    // **/
}