package com.greencom.android.podcasts3.data.user

import com.greencom.android.podcasts3.data.user.local.UserLocalDataSource
import com.greencom.android.podcasts3.data.user.remote.UserRemoteDataSource
import com.greencom.android.podcasts3.domain.user.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
) {

    suspend fun saveUser(user: User) {
        localDataSource.saveUser(user)
    }

}
