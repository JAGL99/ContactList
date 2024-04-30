package com.jagl.contactlist.domain.usecases

import com.jagl.contactlist.data.source.ContactDataSource
import com.jagl.contactlist.data.toContactList
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllContacts @Inject constructor(
    private val dataSource: ContactDataSource
) {

    operator fun invoke() = dataSource.getAllContacts().map { it.toContactList() }
}