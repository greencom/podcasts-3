package com.greencom.android.podcasts3.utils.clean.parameterless

import com.greencom.android.podcasts3.utils.clean.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<out R>(
    dispatcher: CoroutineDispatcher,
) : FlowUseCase<Unit, R>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Result<R>> = invoke(Unit)

}
