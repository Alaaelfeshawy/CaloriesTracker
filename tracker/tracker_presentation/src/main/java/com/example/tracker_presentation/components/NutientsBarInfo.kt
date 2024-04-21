package com.example.tracker_presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.components.UnitDisplay
import com.example.core.R
import com.example.core_ui.color.FatColor

@Composable
fun NutrientsBarInfo(
    modifier: Modifier = Modifier,
    value : Int,
    goal : Int ,
    name : String,
    color : Color,
    strokeWidth : Dp = 8.dp,
) {
    val background = MaterialTheme.colors.background
    val goalExceededColor = MaterialTheme.colors.error

    val angleRation = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = value) {
        angleRation.animateTo(
            targetValue = if (goal > 0) {
                value / goal.toFloat()
            } else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
        ){
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
            ) {
            val valueSize = angleRation.value * size.width
            drawArc(
                color = if(value <= goal) background else goalExceededColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            if (value<=goal){
                drawArc(
                    color = color,
                    startAngle = 90f,
                    sweepAngle = 360f * angleRation.value,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UnitDisplay(
                amount = value.toString(),
                unit = stringResource(id = R.string.grams),
                amountTextColor = if (value <= goal) background else goalExceededColor,
                unitTextColor = if (value <= goal) background else goalExceededColor
            )
            Text(
                text = name,
                color = if (value <= goal) background else goalExceededColor,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.body1
            )
        }
    }
}