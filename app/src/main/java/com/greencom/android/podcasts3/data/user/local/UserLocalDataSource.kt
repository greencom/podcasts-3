package com.greencom.android.podcasts3.data.user.local

import com.greencom.android.podcasts3.data.user.local.db.UserDao
import com.greencom.android.podcasts3.data.user.local.db.UserEntity
import com.greencom.android.podcasts3.domain.user.User
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val dao: UserDao,
) {

    suspend fun saveUser(user: User) {
        val entity = UserEntity.fromUser(user)
        dao.insert(entity)
    }

}
