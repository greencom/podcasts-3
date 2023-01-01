package com.greencom.android.podcasts3.data.user.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id") val id: String,
    @ColumnInfo(name = "user_display_name") val displayName: String?,
    @ColumnInfo(name = "user_avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "user_email") val email: String?,
) {
    companion object {
        const val TABLE_NAME = "user"
    }
}
