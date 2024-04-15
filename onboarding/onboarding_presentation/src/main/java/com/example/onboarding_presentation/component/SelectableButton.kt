package com.example.onboarding_presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.core_ui.dimension.LocalSpacing

@Composable
fun SelectableButton (
    modifier: Modifier = Modifier,
    text : String,
    isSelected : Boolean,
    color: Color,
    selectedTextColor : Color,
    textStyle: TextStyle = MaterialTheme.typography.button,
    onClick : ()->Unit

){
    Box(
        modifier = modifier.background(
            color = if (isSelected) color else Color.Transparent,
            shape = RoundedCornerShape(100.dp)
        ).clickable {
            onClick.invoke()
        }.clip(RoundedCornerShape(100.dp))
            .border(
                width = 2.dp ,
                color = color,
                shape = RoundedCornerShape(100.dp)
                )
            .padding(LocalSpacing.current.spaceMedium),
        contentAlignment = Alignment.Center,
    ){
        Text(
            text = text,
            color = if (isSelected) selectedTextColor else color ,
            style = textStyle,)
    }
}