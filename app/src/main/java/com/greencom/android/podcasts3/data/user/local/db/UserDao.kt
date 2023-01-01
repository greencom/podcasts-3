package com.greencom.android.podcasts3.data.user.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

// TODO: Tests
@Dao
abstract class UserDao {

    @Insert
    abstract suspend fun insert(user: UserEntity)

    fun getCurrentUser(): Flow<UserEntity?> = getCurrentUserRaw().distinctUntilChanged()

    @Query("DELETE FROM ${UserEntity.TABLE_NAME}")
    abstract suspend fun clear()

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    protected abstract fun getCurrentUserRaw(): Flow<UserEntity?>

}
