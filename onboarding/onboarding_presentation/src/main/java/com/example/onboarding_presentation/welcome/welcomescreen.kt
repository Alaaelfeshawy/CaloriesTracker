package com.example.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.R
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.core_ui.dimension.LocalSpacing
import com.example.onboarding_presentation.component.ActionButton

@Composable
fun WelcomeScreen(
    navigate: (UiEvent.Navigate)->Unit
) {
    val dimen = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.welcome_text),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
        )
        ActionButton(
            modifier = Modifier.padding(dimen.spaceMedium),
            text = stringResource(R.string.next),
            onClick = {
                      navigate.invoke(UiEvent.Navigate(Route.GENDER))
            },
            textStyle = MaterialTheme.typography.h1
        )
    }
}