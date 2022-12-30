package com.greencom.android.podcasts3.ui.screens.signin.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts3.R
import com.greencom.android.podcasts3.ui.theme.AppTheme

@Composable
fun SignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_google_logo_24),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(R.string.sign_in_with_google))
    }
}

@Preview
@Composable
private fun Light() {
    AppTheme {
        Surface {
            SignInButton(
                onClick = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Dark() {
    AppTheme {
        Surface {
            SignInButton(
                onClick = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
