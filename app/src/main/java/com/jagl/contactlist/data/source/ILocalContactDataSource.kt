package com.jagl.contactlist.data.source

import com.jagl.contactlist.data.ContactEntity
import kotlinx.coroutines.flow.Flow

interface ILocalContactDataSource {
    fun getAllContacts(): Flow<List<ContactEntity>>
    fun getContactById(id: Int): Flow<ContactEntity?>
    fun saveContact(contact: ContactEntity)
    fun updateContact(contact: ContactEntity)
    fun deleteContact(contact: ContactEntity)
}