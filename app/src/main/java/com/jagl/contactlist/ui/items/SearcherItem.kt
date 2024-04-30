package com.jagl.contactlist.ui.items

import com.jagl.contactlist.domain.data.Contact


sealed class SearcherItem {
    data class ContactItem(val contact: Contact) : SearcherItem()
    data class Empty(val isInitState: Boolean) : SearcherItem()
}