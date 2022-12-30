package com.greencom.android.podcasts3.data.user.local

import com.greencom.android.podcasts3.data.user.local.db.UserDao
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val dao: UserDao,
) {

}
