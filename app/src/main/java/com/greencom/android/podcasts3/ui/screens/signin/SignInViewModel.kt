package com.greencom.android.podcasts3.ui.screens.signin

import com.greencom.android.podcasts3.ui.screens.signin.SignInViewModel.Event
import com.greencom.android.podcasts3.ui.screens.signin.SignInViewModel.SideEffect
import com.greencom.android.podcasts3.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : BaseViewModel<Event, SideEffect>() {

    override fun onEvent(event: Event) = Unit

    sealed interface Event

    sealed interface SideEffect

}
