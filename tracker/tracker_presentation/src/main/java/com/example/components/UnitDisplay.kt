package com.example.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core_ui.dimension.LocalSpacing

@Composable
fun UnitDisplay(
    modifier: Modifier = Modifier,
    amount :String,
    unit :String,
    amountTextColor : Color = MaterialTheme.colors.onBackground,
    unitTextColor : Color = MaterialTheme.colors.onBackground,
    amountTextSize : TextUnit = 20.sp,
    unitTextSize :TextUnit = 14.sp
) {
    val spacing = LocalSpacing.current

    Row(modifier = modifier) {
        Text(
            text = amount,
            style = MaterialTheme.typography.h1,
            fontSize = amountTextSize,
            color = amountTextColor,
            modifier = Modifier.alignBy(LastBaseline)
        )
        Spacer(modifier = modifier.width(spacing.spaceExtraSmall))
        Text(
            text = unit,
            style = MaterialTheme.typography.body1,
            fontSize = unitTextSize,
            color = unitTextColor,
            modifier = Modifier.alignBy(LastBaseline),
        )
    }

}