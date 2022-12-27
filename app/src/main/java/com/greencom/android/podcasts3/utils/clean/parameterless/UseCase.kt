package com.greencom.android.podcasts3.utils.clean.parameterless

import com.greencom.android.podcasts3.utils.clean.UseCase
import kotlinx.coroutines.CoroutineDispatcher

abstract class UseCase<out R>(
    dispatcher: CoroutineDispatcher,
) : UseCase<Unit, R>(dispatcher) {

    suspend operator fun invoke(): Result<R> = invoke(Unit)

}
