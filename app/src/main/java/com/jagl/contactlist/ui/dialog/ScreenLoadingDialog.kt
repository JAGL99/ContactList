package com.jagl.contactlist.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import com.jagl.contactlist.R
import com.jagl.contactlist.databinding.DialogLoadingBinding

internal class ScreenLoadingDialog(context: Context) : Dialog(
    context,
    android.R.style.Theme_Translucent_NoTitleBar
) {

    private val binding by lazy { DialogLoadingBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        val isCancelable = false
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(view)
        setCanceledOnTouchOutside(isCancelable)

        window?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    context, R.color.transparent_black
                )
            )
        )
        setCancelable(isCancelable)
    }

}