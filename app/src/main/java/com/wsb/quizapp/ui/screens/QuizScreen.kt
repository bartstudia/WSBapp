package com.wsb.quizapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wsb.quizapp.data.Question
import com.wsb.quizapp.ui.theme.Blue
import com.wsb.quizapp.ui.theme.Green
import com.wsb.quizapp.ui.theme.Red
import com.wsb.quizapp.ui.theme.White
import com.wsb.quizapp.ui.theme.LightGray

@Composable
fun QuizScreen(
    question: Question?,
    selectedAnswer: String?,
    isAnswerSubmitted: Boolean,
    progress: Float,
    progressText: String,
    onAnswerSelected: (String) -> Unit,
    onNextQuestion: () -> Unit
) {
    if (question == null) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Header
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "M",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )
                Text(
                    text = "UCZELNIE\nWSB MERITO",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue,
                    lineHeight = 12.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Quiz Wiedzy o Uczelni",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Progress indicator
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Blue
                ) {
                    Text(
                        text = "Pytanie $progressText",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                        color = White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Progress bar
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Blue,
                    trackColor = LightGray
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Question
            Text(
                text = question.questionText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Answer options
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AnswerOption(
                    letter = "A",
                    text = question.optionA,
                    isSelected = selectedAnswer == "A",
                    isCorrect = question.correctAnswer == "A",
                    isAnswerSubmitted = isAnswerSubmitted,
                    onClick = { onAnswerSelected("A") }
                )
                AnswerOption(
                    letter = "B",
                    text = question.optionB,
                    isSelected = selectedAnswer == "B",
                    isCorrect = question.correctAnswer == "B",
                    isAnswerSubmitted = isAnswerSubmitted,
                    onClick = { onAnswerSelected("B") }
                )
                AnswerOption(
                    letter = "C",
                    text = question.optionC,
                    isSelected = selectedAnswer == "C",
                    isCorrect = question.correctAnswer == "C",
                    isAnswerSubmitted = isAnswerSubmitted,
                    onClick = { onAnswerSelected("C") }
                )
                AnswerOption(
                    letter = "D",
                    text = question.optionD,
                    isSelected = selectedAnswer == "D",
                    isCorrect = question.correctAnswer == "D",
                    isAnswerSubmitted = isAnswerSubmitted,
                    onClick = { onAnswerSelected("D") }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Next button
            if (isAnswerSubmitted) {
                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue)
                ) {
                    Text(
                        text = "Następne pytanie",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerOption(
    letter: String,
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    isAnswerSubmitted: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isAnswerSubmitted && isCorrect -> Green
        isAnswerSubmitted && isSelected && !isCorrect -> Red
        isSelected && !isAnswerSubmitted -> Green
        else -> LightGray
    }

    val textColor = when {
        isAnswerSubmitted && (isCorrect || (isSelected && !isCorrect)) -> White
        isSelected && !isAnswerSubmitted -> White
        else -> Color.Black
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isAnswerSubmitted) { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (backgroundColor == LightGray) White else backgroundColor)
                    .border(
                        width = 2.dp,
                        color = if (backgroundColor == LightGray) Color.Gray else backgroundColor,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = letter,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (backgroundColor == LightGray) Color.Gray else textColor
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                fontSize = 16.sp,
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
