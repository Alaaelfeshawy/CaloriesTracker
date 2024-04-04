package com.example.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.R
import com.example.onboarding_presentation.component.ActionButton

@Composable
fun WelcomeScreen() {
Text(text="Hello android")

//    Column(
////        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ) {
//        Text(
//            text = stringResource(R.string.welcome_text)
//        )
//        ActionButton(
//            text = stringResource(R.string.next),
//            onClick = {},
//            textStyle = TextStyle()
//        )
//    }
}