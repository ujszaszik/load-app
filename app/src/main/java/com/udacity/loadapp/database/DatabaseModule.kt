package com.udacity.loadapp.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    companion object {
        private const val DATABASE_NAME = "FileDatabase"
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FileDatabase {
        return Room.databaseBuilder(context, FileDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}