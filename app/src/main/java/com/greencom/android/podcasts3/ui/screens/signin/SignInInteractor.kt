package com.greencom.android.podcasts3.ui.screens.signin

import com.greencom.android.podcasts3.usecases.user.SignInUseCase
import javax.inject.Inject

class SignInInteractor @Inject constructor(
    val signIn: SignInUseCase,
)
