package com.jagl.contactlist.ui.contactDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagl.contactlist.domain.data.Contact
import com.jagl.contactlist.domain.usecases.DeleteContact
import com.jagl.contactlist.domain.usecases.GetContactById
import com.jagl.contactlist.domain.usecases.UpdateContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    private val getContactById: GetContactById,
    private val updateContact: UpdateContact,
    private val deleteContact: DeleteContact
) : ViewModel() {


    private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
    val uiState: LiveData<UiState>
        get() = _uiState


    sealed class UiState {
        data object Loading : UiState()
        data class Content(val contact: Contact) : UiState()
        data class Error(val errorMessage: String) : UiState()
    }


    fun getContact(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        getContactById(id).collectLatest {
            if (it == null) {
                _uiState.value = UiState.Error("No se encontro información")
            } else {
                _uiState.value = UiState.Content(it)
            }
        }
    }

    fun update(newContact: Contact) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        updateContact(newContact)
        getContactById(newContact.id).collectLatest {
            if (it == null) {
                _uiState.value = UiState.Error("No se encontro información")
            } else {
                _uiState.value = UiState.Content(it)
            }
        }
    }

    fun delete(contact: Contact, onComplete: () -> Unit) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        deleteContact(contact)
    }.invokeOnCompletion { onComplete() }
}