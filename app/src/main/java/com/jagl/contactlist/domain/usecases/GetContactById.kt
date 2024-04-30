package com.jagl.contactlist.domain.usecases

import com.jagl.contactlist.data.source.ContactDataSource
import com.jagl.contactlist.data.toContact
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetContactById @Inject constructor(
    private val dataSource: ContactDataSource
) {

    operator fun invoke(id: Int) = dataSource.getContactById(id).map { it?.toContact() }
}