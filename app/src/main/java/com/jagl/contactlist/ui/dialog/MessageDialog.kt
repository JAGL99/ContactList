package com.jagl.contactlist.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.jagl.contactlist.R
import com.jagl.contactlist.databinding.DialogMessageBinding

internal class MessageDialog(context: Context) : Dialog(context) {
    enum class Type {
        ERROR, WARNING, SUCCESS
    }

    private val binding by lazy { DialogMessageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        val isCancelable = true
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(view)
        setCanceledOnTouchOutside(isCancelable)
        window?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    context, android.R.color.transparent
                )
            )
        )
        setCancelable(isCancelable)
    }

    fun setDialog(newText: String, type: Type = Type.WARNING) {
        setType(type)
        binding.tvMessage.text = newText
    }

    private fun setType(type: Type) {
        val drawableId = when (type) {
            Type.ERROR -> R.drawable.ic_error
            Type.WARNING, Type.SUCCESS -> R.drawable.ic_warning
        }

        binding.ivIcon.setImageDrawable(
            AppCompatResources.getDrawable(context, drawableId)
        )
    }
}