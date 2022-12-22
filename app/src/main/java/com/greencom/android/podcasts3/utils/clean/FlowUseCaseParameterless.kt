package com.greencom.android.podcasts3.utils.clean

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

// TODO: Find a better name
abstract class FlowUseCaseParameterless<out R>(
    dispatcher: CoroutineDispatcher,
) : FlowUseCase<Unit, R>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Result<R>> = invoke(Unit)

}
