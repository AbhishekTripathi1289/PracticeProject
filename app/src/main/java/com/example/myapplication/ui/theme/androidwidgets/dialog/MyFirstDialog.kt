package com.example.myapplication.ui.theme.androidwidgets.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogCustomLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class MyFirstDialog(var context: Context)  {

    lateinit var binding: DialogCustomLayoutBinding
    lateinit var dialog : Dialog
    init {
        if(true)
        {
            dialog = BottomSheetDialog(context, R.style.BaseBottomSheetDialogWithAdjustPanWindowSoftInputMode)
            (dialog as BottomSheetDialog).behavior.state =BottomSheetBehavior.STATE_EXPANDED
        }
        else{
            dialog = Dialog(context, R.style.WhiteGroundColorSetForDialog)
        }
        binding = DialogCustomLayoutBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }
}