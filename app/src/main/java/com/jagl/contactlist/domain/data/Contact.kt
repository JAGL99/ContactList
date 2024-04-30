package com.jagl.contactlist.domain.data

import com.jagl.contactlist.data.ContactEntity
import com.jagl.contactlist.ui.items.SearcherItem.ContactItem

data class Contact(
    val id: Int,
    val name: String,
    val fatherLastName: String,
    val motherLastName: String,
    val age: Int,
    val phone: String,
    val gender: String,
    val avatar: String,
)

fun Contact.toSearchContact(): ContactItem = ContactItem(this)

fun Contact.toContactEntity(): ContactEntity {
    return ContactEntity(id, name, fatherLastName, motherLastName, age, phone, gender, avatar)
}
