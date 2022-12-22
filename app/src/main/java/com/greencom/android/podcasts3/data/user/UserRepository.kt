package com.greencom.android.podcasts3.data.user

import com.greencom.android.podcasts3.data.user.local.UserLocalDataSource
import com.greencom.android.podcasts3.data.user.remote.UserRemoteDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
) {



}
