package com.jagl.contactlist.ui.createContact

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jagl.contactlist.databinding.FragmentCreateContactBinding
import com.jagl.contactlist.domain.data.Contact
import com.jagl.contactlist.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateContactFragment : BaseFragment() {

    private val viewModel by viewModels<CreateContactViewModel>()
    private lateinit var binding: FragmentCreateContactBinding
    override fun getLayoutRes(): View {
        binding = FragmentCreateContactBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initVariables() {

    }

    override fun setUpViews() {
        binding.btnSave.setOnClickListener {
            val contact = getContact()
            viewModel.storeData(contact) {
                findNavController().popBackStack()
            }
        }
    }

    private fun getContact(): Contact = with(binding) {
        val name = tieName.text.toString()
        val fatherLastName = tieFatherLastName.text.toString()
        val motherLastName = tieMotherLastName.text.toString()
        val phone = tiePhone.text.toString()
        val age = tieAge.text.toString().toInt()
        val gender = tieGender.text.toString()
        Contact(0, name, fatherLastName, motherLastName, age, phone, gender, "")
    }

    override fun setUpObservables() {

    }


}