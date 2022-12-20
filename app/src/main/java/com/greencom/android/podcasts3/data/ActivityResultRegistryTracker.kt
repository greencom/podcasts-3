package com.greencom.android.podcasts3.data

import androidx.activity.result.ActivityResultRegistry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityResultRegistryTracker @Inject constructor() {

    private val _current =
        MutableStateFlow<ActivityResultRegistry?>(null)
    val current = _current.asStateFlow()

    fun setRegistry(registry: ActivityResultRegistry) {
        _current.update { registry }
    }

    fun unsetRegistry(registry: ActivityResultRegistry) {
        _current.update { if (it == registry) null else it }
    }

}
