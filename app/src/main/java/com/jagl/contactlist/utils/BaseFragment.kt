package com.jagl.contactlist.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.jagl.contactlist.ui.dialog.MessageDialog
import com.jagl.contactlist.ui.dialog.ScreenLoadingDialog

abstract class BaseFragment : Fragment() {

    protected var TAG: String = ""

    private lateinit var screenLoading: ScreenLoadingDialog

    private lateinit var messageDialog: MessageDialog

    protected abstract fun getLayoutRes(): View
    protected abstract fun initVariables()
    protected abstract fun setUpViews()
    protected abstract fun setUpObservables()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        screenLoading = ScreenLoadingDialog(requireContext())
        messageDialog = MessageDialog(requireContext())
        val view = getLayoutRes()
        initVariables()
        setUpViews()
        setUpObservables()

        return view
    }

    protected fun navigateToDirection(navDirections: NavDirections) =
        findNavController().navigate(navDirections)

    protected fun showLoading() {
        if (!screenLoading.isShowing) {
            screenLoading.show()
        }
    }

    protected fun dismissLoading() {
        screenLoading.dismiss()
    }

    protected fun showWarningDialog(message: String) {
        messageDialog.setDialog(message, MessageDialog.Type.WARNING)
        if (!messageDialog.isShowing) {
            messageDialog.show()
        }
    }


    protected fun showErrorDialog(message: String) {
        messageDialog.setDialog(message, MessageDialog.Type.ERROR)
        if (!messageDialog.isShowing) {
            messageDialog.show()
        }
    }

    override fun onDestroy() {
        if (messageDialog.isShowing) messageDialog.dismiss()
        if (screenLoading.isShowing) screenLoading.dismiss()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        screenLoading.dismiss()
    }
}