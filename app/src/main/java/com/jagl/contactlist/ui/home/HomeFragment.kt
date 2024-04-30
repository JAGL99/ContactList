package com.jagl.contactlist.ui.home

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jagl.contactlist.databinding.FragmentHomeBinding
import com.jagl.contactlist.domain.data.Contact
import com.jagl.contactlist.ui.home.HomeViewModel.UiState
import com.jagl.contactlist.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {


    private lateinit var adapter: ContactAdapter
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun getLayoutRes(): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initVariables() {
        adapter = ContactAdapter { goToDetail(it.contact) }
        binding.recyclerView.adapter = adapter
    }

    override fun setUpViews() {
        viewModel.getContacts(true)
        binding.etSearcher.addTextChangedListener {
            makeSearch(it.toString())
        }
        binding.fabAddContact.setOnClickListener {
            navigateToDirection(
                HomeFragmentDirections.actionHomeFragmentToCreateContactFragment()
            )
        }
    }

    private fun makeSearch(query: String) {
        viewModel.getContacts(false, query)
    }

    override fun setUpObservables() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer(::updateUI))
    }

    private fun updateUI(state: UiState) {
        when (state) {
            UiState.Loading -> showLoading()
            is UiState.EmptySearch -> adapter.setEmpty(state.isInit)
            is UiState.Error -> showErrorDialog(state.errorMessage)
            is UiState.Content -> adapter.updateItems(state.searcherItems)
        }
        if (state !is UiState.Loading) dismissLoading()
    }

    private fun goToDetail(contact: Contact) {
        navigateToDirection(
            HomeFragmentDirections.actionHomeFragmentToContactDetailFragment(contact.id)
        )
    }

}