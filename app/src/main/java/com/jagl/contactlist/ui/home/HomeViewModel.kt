package com.jagl.contactlist.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagl.contactlist.domain.data.toSearchContact
import com.jagl.contactlist.domain.usecases.GetAllContacts
import com.jagl.contactlist.ui.items.SearcherItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllContacts: GetAllContacts
) : ViewModel() {

    private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
    val uiState: LiveData<UiState>
        get() = _uiState


    sealed class UiState {
        data object Loading : UiState()
        data class Content(val searcherItems: List<SearcherItem>) : UiState()
        data class EmptySearch(val isInit: Boolean) : UiState()
        data class Error(val errorMessage: String) : UiState()
    }

    fun getContacts(isInit: Boolean) = viewModelScope.launch {
        getAllContacts().collectLatest {
            if (it.isEmpty()) {
                _uiState.value = UiState.EmptySearch(isInit)
            } else {
                _uiState.value = UiState.Content(it.map { it.toSearchContact() })
            }
        }.runCatching {
            _uiState.value = UiState.Error("Falla al consultar los contactos")
        }
    }

}