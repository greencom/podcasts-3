package com.greencom.android.podcasts3.ui.screens.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<SignInViewModel>()

    SignInScreenContent()
}

@Composable
private fun SignInScreenContent() {

}
