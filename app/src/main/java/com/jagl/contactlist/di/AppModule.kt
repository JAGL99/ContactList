package com.jagl.contactlist.di

import android.content.Context
import androidx.room.Room
import com.jagl.contactlist.data.ContactDao
import com.jagl.contactlist.data.ContactDatabase
import com.jagl.contactlist.data.source.ContactDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideContactDatabase(
        @ApplicationContext contex: Context
    ) = Room.databaseBuilder(contex, ContactDatabase::class.java, "contact_db").build()

    @Singleton
    @Provides
    fun provideContactDao(db: ContactDatabase) = db.contactDao()

    @Singleton
    @Provides
    fun provideDatasource(contactDao: ContactDao): ContactDataSource {
        return ContactDataSource(contactDao)
    }
}