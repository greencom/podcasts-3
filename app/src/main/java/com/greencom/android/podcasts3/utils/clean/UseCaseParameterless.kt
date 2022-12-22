package com.greencom.android.podcasts3.utils.clean

import kotlinx.coroutines.CoroutineDispatcher

// TODO: Find a better name
abstract class UseCaseParameterless<out R>(
    dispatcher: CoroutineDispatcher,
) : UseCase<Unit, R>(dispatcher) {

    suspend operator fun invoke(): Result<R> = invoke(Unit)

}
