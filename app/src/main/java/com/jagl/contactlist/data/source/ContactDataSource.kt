package com.jagl.contactlist.data.source

import com.jagl.contactlist.data.ContactDao
import com.jagl.contactlist.data.ContactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactDataSource @Inject constructor(private val contactDao: ContactDao) :
    ILocalContactDataSource {
    override fun getAllContacts(): Flow<List<ContactEntity>> {
        return contactDao.getAllContacts()
    }

    override fun getContactById(id: Int): Flow<ContactEntity?> {
        return contactDao.getContactById(id)
    }

    override fun saveContact(contact: ContactEntity) {
        contactDao.saveContact(contact)
    }

    override fun updateContact(contact: ContactEntity) {
        return contactDao.updateContact(contact)
    }

    override fun deleteContact(contact: ContactEntity) {
        return contactDao.deleteContact(contact)
    }
}