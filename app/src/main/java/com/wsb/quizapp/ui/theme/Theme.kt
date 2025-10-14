package com.wsb.quizapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = LightBlue,
    background = Blue,
    surface = White,
    error = Red,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = DarkGray
)

@Composable
fun WSBQuizAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
