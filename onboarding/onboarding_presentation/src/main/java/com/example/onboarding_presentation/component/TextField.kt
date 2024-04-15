package com.example.onboarding_presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.core_ui.dimension.LocalSpacing

@Composable
fun UnitTextField(
    modifier: Modifier = Modifier,
    value : String,
    onValueChanged : (String)->Unit,
    unit : String,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.primaryVariant,
        fontSize = 70.sp
    ),
    imeAction: ImeAction = ImeAction.Done
) {
    val spacing = LocalSpacing.current
    Row(
        horizontalArrangement = Arrangement.Center
    ) {

      TextField(
          value = value,
          onValueChange = onValueChanged,
          textStyle = textStyle,
          keyboardOptions = KeyboardOptions(
              keyboardType = KeyboardType.Number,
              imeAction = imeAction
          ),
          singleLine = true,
          modifier = Modifier
              .width(IntrinsicSize.Min)
              .alignBy(LastBaseline),
          colors = TextFieldDefaults.textFieldColors(
              backgroundColor = Color.Transparent
          )
          )

        Text(text = unit , modifier= Modifier.alignBy(LastBaseline))
    }
}