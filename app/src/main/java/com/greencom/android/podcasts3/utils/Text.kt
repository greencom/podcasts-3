package com.greencom.android.podcasts3.utils

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

@Composable
fun textString(text: Text): String {
    // Will be recomposed when Configuration gets updated.
    LocalConfiguration.current
    val context = LocalContext.current
    return text.getString(context)
}

// TODO: Tests
sealed class Text {

    abstract fun getString(context: Context): kotlin.String

    object Empty : Text() {
        override fun getString(context: Context): kotlin.String = ""
    }

    class Resource(
        @StringRes val resourceId: Int,
        private vararg val args: Any = emptyArray(),
    ) : Text() {

        override fun getString(context: Context): kotlin.String {
            @Suppress("SpreadOperator")
            return context.resources.getString(this.resourceId, *this.args)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Resource) return false

            if (resourceId != other.resourceId) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = resourceId
            result = 31 * result + args.contentHashCode()
            return result
        }

    }

    class PluralsResource(
        @PluralsRes val resourceId: Int,
        private val count: Int,
        private vararg val args: Any = emptyArray(),
    ) : Text() {

        override fun getString(context: Context): kotlin.String {
            @Suppress("SpreadOperator")
            return context.resources.getQuantityString(this.resourceId, this.count, *this.args)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is PluralsResource) return false

            if (resourceId != other.resourceId) return false
            if (count != other.count) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = resourceId
            result = 31 * result + count
            result = 31 * result + args.contentHashCode()
            return result
        }

    }

    class String(private val text: kotlin.String) : Text() {

        override fun getString(context: Context): kotlin.String = text

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is String) return false

            if (text != other.text) return false

            return true
        }

        override fun hashCode(): Int = text.hashCode()

    }

}
