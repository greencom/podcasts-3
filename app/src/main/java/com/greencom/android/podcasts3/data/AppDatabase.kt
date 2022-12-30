package com.greencom.android.podcasts3.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greencom.android.podcasts3.data.user.local.db.UserDao
import com.greencom.android.podcasts3.data.user.local.db.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
