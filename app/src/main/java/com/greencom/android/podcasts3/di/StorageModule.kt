package com.greencom.android.podcasts3.di

import android.content.Context
import androidx.room.Room
import com.greencom.android.podcasts3.data.AppDatabase
import com.greencom.android.podcasts3.data.user.local.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
            // TODO: Add migrations
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private const val APP_DATABASE_NAME = "app_database"
    }

}
