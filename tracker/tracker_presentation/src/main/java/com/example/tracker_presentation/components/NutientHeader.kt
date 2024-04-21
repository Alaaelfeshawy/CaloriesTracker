package com.example.tracker_presentation.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.UnitDisplay
import com.example.core.R
import com.example.core_ui.color.CarbColor
import com.example.core_ui.color.FatColor
import com.example.core_ui.color.ProteinColor
import com.example.core_ui.dimension.LocalSpacing
import com.example.tracker_presentation.TrackerOverviewState

@Composable
fun NutrientHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = state.totalCalories, label = ""
    )
   Column(
       modifier = modifier
           .fillMaxWidth()
           .clip(
               RoundedCornerShape(
                   bottomStart = 50.dp,
                   bottomEnd = 50.dp
               )
           )
           .background(MaterialTheme.colors.primary)
           .padding(
               horizontal = spacing.spaceLarge,
               vertical = spacing.spaceExtraLarge
           )
   ) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(spacing.spaceMedium),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
           ) {
           UnitDisplay(
               amount = animatedCalorieCount.value.toString(),
               unit = stringResource(id = R.string.kcal),
               amountTextColor = MaterialTheme.colors.onPrimary,
               amountTextSize = 40.sp,
               unitTextColor = MaterialTheme.colors.onPrimary,
               modifier = Modifier.align(Alignment.Bottom)
               )
           Column {
               Text(
                   text = stringResource(id = R.string.your_goal),
                   style = MaterialTheme.typography.body2,
                   color = MaterialTheme.colors.onPrimary
               )
               UnitDisplay(
                   amount = state.caloriesGoal.toString(),
                   unit = stringResource(id = R.string.kcal),
                   amountTextColor = MaterialTheme.colors.onPrimary,
                   amountTextSize = 40.sp,
                   unitTextColor = MaterialTheme.colors.onPrimary,
               )
           }
       }
       NutrientsBar(
           modifier = Modifier
               .fillMaxWidth()
               .height(60.dp)
               .padding(spacing.spaceMedium) ,
           carb = state.totalCarbs,
           protein = state.totalProtein,
           fat = state.totalFat,
           calories = state.totalCalories,
           calorieGoal = state.caloriesGoal)
       
       Spacer(modifier = modifier.height(spacing.spaceMedium))

       Row(
           modifier = Modifier
               .fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceEvenly
       ) {
           NutrientsBarInfo(
               value = state.totalCarbs,
               goal = state.carbsGoal,
               name = stringResource(id = R.string.carbs) ,
               color = CarbColor,
               modifier = Modifier.size(90.dp)
               )

           NutrientsBarInfo(
               value = state.totalProtein,
               goal = state.proteinGoal,
               name = stringResource(id = R.string.protein) ,
               color = ProteinColor,
               modifier = Modifier.size(80.dp)
           )

           NutrientsBarInfo(
               value = state.totalFat,
               goal = state.fatGoal,
               name = stringResource(id = R.string.fat) ,
               color = FatColor,
               modifier = Modifier.size(80.dp)
           )
       }
   }
}