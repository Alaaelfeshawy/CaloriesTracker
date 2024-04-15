package com.example.onboarding_presentation.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.util.UiEvent
import com.example.core_ui.dimension.LocalSpacing
import com.example.onboarding_presentation.component.ActionButton
import com.example.onboarding_presentation.component.UnitTextField

@Composable
fun WeightScreen(
    navigate: (UiEvent.Navigate) -> Unit = {},
    viewModel: WeightViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.uiEvent){
        viewModel.uiEvent.collect {
            when(it){
                is UiEvent.Navigate->navigate.invoke(it)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = it.message.asString(context))
                }
                else -> Unit

            }
        }
    }
    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.h3,
            )

            UnitTextField(
                value = viewModel.weight.value,
                onValueChanged = viewModel::saveWeight ,
                unit = stringResource(id = R.string.kg)
            )

        }
        ActionButton(
            modifier = Modifier
                .padding(LocalSpacing.current.spaceMedium)
                .align(Alignment.BottomEnd)
            ,
            text = stringResource(R.string.next),
            onClick = { viewModel.onNextClick() },
            textStyle = MaterialTheme.typography.h4
        )
    }
}