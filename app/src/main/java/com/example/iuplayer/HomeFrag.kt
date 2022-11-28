package com.example.iuplayer

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.iuplayer.databinding.DialogSignBinding
import com.example.iuplayer.databinding.FragmentHomeBinding


class HomeFrag : Fragment() {
    lateinit var dialogSignBinding: DialogSignBinding
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        dialogSignBinding = DialogSignBinding.inflate(inflater, container, false)
//        val signDialog =  SignDialog(binding.root.context, dialogSignBinding)

        binding.ivCalendar.setOnClickListener{
            DatePickerDialog(binding.root.context, object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(datePicker: DatePicker?, yyyy: Int, MM: Int, dd: Int) {
                    var year = yyyy
                    var month = MM
                    var date = dd
                    Log.d("IU Player", "year: ${year}, month: ${month}, date: ${date}")
                    if(date < 10){
                        binding.tvDate.setText("${year}/${month}/0${date}")
                    }else{
                        binding.tvDate.setText("${year}/${month}/${date}")
                    }
                }
            }, 2022, 11, 2).show()
        }

        binding.ivHome.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.youtube.com/watch?v=0-q1KafFCLU")
            ContextCompat.startActivity(binding.root.context, intent, null)
        }

//        binding.ivAccount.setOnClickListener {
//            signDialog.showDialog()
//        }
//
//        binding.tvAccount.setOnClickListener {
//            signDialog.showDialog()
//        }
//        val introImage = getView()?.findViewById<View>(R.id.home) as ImageView
//        Glide.with(this).load(com.example.iuplayer.R.raw.home).into(introImage)
        return binding.root
    }
}