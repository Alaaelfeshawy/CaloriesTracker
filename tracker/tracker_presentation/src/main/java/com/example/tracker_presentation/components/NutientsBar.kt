package com.example.tracker_presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.example.core_ui.color.FatColor

@Composable
fun NutrientsBar(
    carb : Int,
    protein : Int,
    fat : Int,
    calories : Int,
    calorieGoal : Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error

    val carbWithRatio = remember {
        Animatable(0f)
    }
    val fatWithRatio = remember {
        Animatable(0f)
    }
    val proteinWithRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carb) {
        carbWithRatio.animateTo(
            targetValue = (carb *4f) / calorieGoal
        )
    }
    LaunchedEffect(key1 = protein) {
        proteinWithRatio.animateTo(
            targetValue =(protein *4f) / calorieGoal
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWithRatio.animateTo(
            targetValue =(fat *9f) / calorieGoal
        )
    }

    Canvas(modifier = modifier) {
        val fatSize = fatWithRatio.value * size.width
        val proteinSize = proteinWithRatio.value * size.width
        val carbSize = carbWithRatio.value * size.width

        if (calories <= calorieGoal){
           drawRoundRect(
               color = background,
               size = size,
               cornerRadius = CornerRadius(100f)
           )
           drawRoundRect(
               color = FatColor,
               size = Size(
                   width = carbSize + proteinSize + fatSize,
                   height = size.height
               ),
               cornerRadius = CornerRadius(100f)
           )
            drawRoundRect(
               color = FatColor,
               size = Size(
                   width = carbSize + proteinSize,
                   height = size.height
               ),
               cornerRadius = CornerRadius(100f)
           )
            drawRoundRect(
               color = FatColor,
               size = Size(
                   width = carbSize,
                   height = size.height
               ),
               cornerRadius = CornerRadius(100f)
           )

       }else{
           drawRoundRect(
               color = caloriesExceedColor,
               size = size,
               cornerRadius = CornerRadius(100f)
           )
       }
    }

}