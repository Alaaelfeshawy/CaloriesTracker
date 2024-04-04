package com.example.onboarding_presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.core_ui.dimension.LocalSpacing

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text : String,
    onClick : ()->Unit,
    isEnabled : Boolean = true,
    textStyle : TextStyle = TextStyle(),
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = RoundedCornerShape(100.dp)
        ){
        Text( text = text,
            modifier = Modifier.padding(LocalSpacing.current.spaceExtraSmall),
            style = textStyle
        )
    }
}