package com.greencom.android.podcasts3.di

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.greencom.android.podcasts3.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideGoogleSignInClient(@ApplicationContext context: Context): SignInClient =
        Identity.getSignInClient(context)

    @Provides
    fun provideGoogleSignInRequest(
        googleIdTokenRequestOptions: GoogleIdTokenRequestOptions,
    ): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(googleIdTokenRequestOptions)
            .setAutoSelectEnabled(true)
            .build()
    }

    @Provides
    fun provideGoogleIdTokenRequestOptions(
        @ApplicationContext context: Context,
    ): GoogleIdTokenRequestOptions {
        val serverClientId = context.getString(R.string.google_server_client_id)
        return GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            .setServerClientId(serverClientId)
            .setFilterByAuthorizedAccounts(false)
            .build()
    }

}
