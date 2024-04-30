package com.jagl.contactlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jagl.contactlist.domain.data.Contact


@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "fatherLastName")
    val fatherLastName: String,
    @ColumnInfo(name = "motherLastName")
    val motherLastName: String,
    @ColumnInfo(name = "age")
    val age: Int,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "image")
    val avatar: String,
)

fun ContactEntity.toContact(): Contact {
    return Contact(id, name, fatherLastName, motherLastName, age, phone, gender, avatar)
}

fun List<ContactEntity>.toContactList(): List<Contact> {
    return this.map { it.toContact() }
}