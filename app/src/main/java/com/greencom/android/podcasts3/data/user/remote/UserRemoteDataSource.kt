package com.greencom.android.podcasts3.data.user.remote

import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.greencom.android.podcasts3.data.ActivityResultRegistryTracker
import com.greencom.android.podcasts3.domain.user.SignInFlowCanceledException
import com.greencom.android.podcasts3.domain.user.User
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

// TODO: Tests
// TODO: Check on devices without Google services
class UserRemoteDataSource @Inject constructor(
    private val googleSignInClient: SignInClient,
    private val googleSignInRequest: BeginSignInRequest,
    private val activityResultRegistryTracker: ActivityResultRegistryTracker,
) {

    private var signInLauncher: ActivityResultLauncher<IntentSenderRequest>? = null

    suspend fun signIn(): User = suspendCoroutine { continuation ->
        val activityResultRegistry = activityResultRegistryTracker.current.value
        signInLauncher = activityResultRegistry?.register(
            KEY_SIGN_IN,
            ActivityResultContracts.StartIntentSenderForResult(),
        ) { result ->
            handleSignInResult(result, continuation)
        }

        googleSignInClient.beginSignIn(googleSignInRequest)
            .addOnSuccessListener { result ->
                val intentSenderRequest =
                    IntentSenderRequest.Builder(result.pendingIntent).build()
                signInLauncher?.launch(intentSenderRequest)
            }
            .addOnFailureListener { e ->
                Timber.e(e, "SignInClient.beginSignIn() has failed")
                continuation.resumeWithException(e)
            }
    }

    suspend fun signOut() = suspendCoroutine { continuation ->
        googleSignInClient.signOut()
            .addOnSuccessListener {
                Timber.v("User has successfully logged out")
                continuation.resume(Unit)
            }
            .addOnFailureListener { e ->
                Timber.e(e, "An error occurred while logging out")
                continuation.resumeWithException(e)
            }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun handleSignInResult(result: ActivityResult, continuation: Continuation<User>) {
        try {
            val credential =
                googleSignInClient.getSignInCredentialFromIntent(result.data)
            Timber.v("User has successfully signed in")
            val user = credential.toUser()
            continuation.resume(user)
        } catch (e: ApiException) {
            Timber.e(e, "An error occurred while signing in")
            handleSignInApiException(e, continuation)
        } catch (e: Exception) {
            Timber.e(e, "An error occurred while signing in")
            continuation.resumeWithException(e)
        } finally {
            signInLauncher?.unregister()
        }
    }

    private fun handleSignInApiException(
        exception: ApiException,
        continuation: Continuation<User>,
    ) {
        when (exception.statusCode) {
            CommonStatusCodes.CANCELED ->
                continuation.resumeWithException(SignInFlowCanceledException())
            else -> continuation.resumeWithException(exception)
        }
    }

    private fun SignInCredential.toUser(): User = User(
        id = id,
        displayName = displayName,
        avatarUrl = profilePictureUri?.toString(),
        email = id,
    )

    companion object {
        private const val KEY_SIGN_IN = "key_sign_in"
    }

}
