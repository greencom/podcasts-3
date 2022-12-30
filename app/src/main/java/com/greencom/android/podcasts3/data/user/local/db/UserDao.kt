package com.greencom.android.podcasts3.data.user.local.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

// TODO: Write tests
@Dao
abstract class UserDao {

    fun getCurrentUser(): Flow<UserEntity?> = getCurrentUserRaw().distinctUntilChanged()

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    protected abstract fun getCurrentUserRaw(): Flow<UserEntity?>

}
