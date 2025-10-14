package com.wsb.quizapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wsb.quizapp.ui.theme.Blue
import com.wsb.quizapp.ui.theme.Green
import com.wsb.quizapp.ui.theme.Red
import com.wsb.quizapp.ui.theme.White

@Composable
fun ResultScreen(
    correctAnswers: Int,
    totalQuestions: Int,
    onRestartQuiz: () -> Unit
) {
    val percentage = (correctAnswers.toFloat() / totalQuestions * 100).toInt()
    val message = when {
        percentage >= 80 -> "Świetna robota! 🎉"
        percentage >= 60 -> "Dobrze Ci poszło! 👍"
        percentage >= 40 -> "Nieźle! 😊"
        else -> "Trzeba się douczyć! 📚"
    }

    val messageColor = when {
        percentage >= 80 -> Green
        percentage >= 40 -> Blue
        else -> Red
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo
                Text(
                    text = "M",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )
                Text(
                    text = "UCZELNIE\nWSB MERITO",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Result icon
                Text(
                    text = if (percentage >= 60) "🎉" else "📚",
                    fontSize = 64.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Message
                Text(
                    text = message,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = messageColor,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Quiz zakończony!",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Score
                Text(
                    text = "Twój wynik:",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$correctAnswers/$totalQuestions",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Progress bar
                LinearProgressIndicator(
                    progress = correctAnswers.toFloat() / totalQuestions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp),
                    color = Blue,
                    trackColor = Color.LightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$percentage%",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Restart button
                Button(
                    onClick = onRestartQuiz,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue)
                ) {
                    Text(
                        text = "Rozpocznij ponownie",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Dziękujemy za udział w quizie!",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
