package com.jagl.contactlist.ui.createContact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagl.contactlist.domain.data.Contact
import com.jagl.contactlist.domain.usecases.SaveContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateContactViewModel @Inject constructor(
    private val saveContact: SaveContact
) : ViewModel() {

    fun storeData(contact: Contact, onComplete: () -> Unit) = viewModelScope.launch {
        saveContact(contact)
    }.invokeOnCompletion { onComplete() }

}