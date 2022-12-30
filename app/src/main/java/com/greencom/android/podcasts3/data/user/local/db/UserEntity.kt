package com.greencom.android.podcasts3.data.user.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id") val id: String,
) {
    companion object {
        const val TABLE_NAME = "user"
    }
}
