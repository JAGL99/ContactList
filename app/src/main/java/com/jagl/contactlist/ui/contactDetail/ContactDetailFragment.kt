package com.jagl.contactlist.ui.contactDetail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jagl.contactlist.databinding.FragmentContactDetailBinding
import com.jagl.contactlist.domain.data.Contact
import com.jagl.contactlist.ui.contactDetail.ContactDetailViewModel.UiState
import com.jagl.contactlist.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailFragment : BaseFragment() {

    private val args by navArgs<ContactDetailFragmentArgs>()
    private lateinit var binding: FragmentContactDetailBinding
    private val viewModel by viewModels<ContactDetailViewModel>()


    override fun getLayoutRes(): View {
        binding = FragmentContactDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initVariables() {
        viewModel.getContact(args.id)
    }

    override fun setUpViews() {
        binding.btnUpdate.setOnClickListener {
            val newContact = getContact()
            viewModel.update(newContact)
        }
        binding.btnDelete.setOnClickListener {
            val contact = getContact()
            viewModel.delete(contact) {
                findNavController().popBackStack()
            }
        }
    }

    override fun setUpObservables() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer(::updateUI))
    }

    private fun updateUI(state: UiState) {
        when (state) {
            UiState.Loading -> showLoading()
            is UiState.Error -> showErrorDialog(state.errorMessage)
            is UiState.Content -> with(binding) {
                state.contact.let {
                    tieName.setText(it.name)
                    tiePhone.setText(it.phone)
                    tieGender.setText(it.gender)
                    tieAge.setText(it.age.toString())
                    tieFatherLastName.setText(it.fatherLastName)
                    tieMotherLastName.setText(it.motherLastName)
                }

            }
        }
        if (state !is UiState.Loading) dismissLoading()
    }

    private fun getContact(): Contact = with(binding) {
        val name = tieName.text.toString()
        val fatherLastName = tieFatherLastName.text.toString()
        val motherLastName = tieMotherLastName.text.toString()
        val phone = tiePhone.text.toString()
        val age = tieAge.text.toString().toInt()
        val gender = tieGender.text.toString()
        Contact(args.id, name, fatherLastName, motherLastName, age, phone, gender, "")
    }
}