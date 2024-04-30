package com.jagl.contactlist.domain.usecases

import com.jagl.contactlist.data.source.ContactDataSource
import com.jagl.contactlist.domain.data.Contact
import com.jagl.contactlist.domain.data.toContactEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteContact @Inject constructor(
    private val dataSource: ContactDataSource
) {

    suspend operator fun invoke(
        contact: Contact, dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) = withContext(dispatcher) {
        dataSource.deleteContact(contact.toContactEntity())
    }
}