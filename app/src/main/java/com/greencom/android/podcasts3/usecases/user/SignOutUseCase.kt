package com.greencom.android.podcasts3.usecases.user

import com.greencom.android.podcasts3.data.user.UserRepository
import com.greencom.android.podcasts3.di.AppDispatchers
import com.greencom.android.podcasts3.di.Dispatcher
import com.greencom.android.podcasts3.utils.clean.parameterless.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    @Dispatcher(AppDispatchers.IO) dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
) : UseCase<Unit>(dispatcher) {

    override suspend fun execute(parameters: Unit) {
        userRepository.signOut()
    }

}
