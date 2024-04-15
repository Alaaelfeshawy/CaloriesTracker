package com.example.onboarding_presentation.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.domain.model.GoalType
import com.example.core.util.UiEvent
import com.example.core_ui.dimension.LocalSpacing
import com.example.onboarding_presentation.component.ActionButton
import com.example.onboarding_presentation.component.SelectableButton

@Composable
@Preview(showBackground = true)
fun GoalScreen(
    navigate: (UiEvent.Navigate)->Unit ={},
    viewModel: GoalViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel.uiEvent){
        viewModel.uiEvent.collect {
            when(it){
                is UiEvent.Navigate->navigate.invoke(it)
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
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.h3,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.spaceMedium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {

                SelectableButton(
                    text = stringResource(id = R.string.lose),
                    isSelected = viewModel.selectedGoal.value == GoalType.LoseWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
                ) {
                    viewModel.saveGoalType(GoalType.LoseWeight)

                }

                SelectableButton(
                    text = stringResource(id = R.string.keep),
                    isSelected = viewModel.selectedGoal.value == GoalType.KeepWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
                ) {
                    viewModel.saveGoalType(GoalType.KeepWeight)
                }

                SelectableButton(
                    text = stringResource(id = R.string.gain),
                    isSelected = viewModel.selectedGoal.value == GoalType.GainWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
                ) {
                    viewModel.saveGoalType(GoalType.GainWeight)
                }

            }

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