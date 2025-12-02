package com.wsb.quizapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wsb.quizapp.ui.theme.Blue
import com.wsb.quizapp.ui.theme.LightGray
import com.wsb.quizapp.ui.theme.White

@Composable
fun HomeScreen(
    onStartQuiz: (String?) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Logo
                    Text(
                        text = "M",
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Bold,
                        color = Blue
                    )
                    Text(
                        text = "UCZELNIE\nWSB MERITO",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Blue,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = "Quiz Wiedzy o Uczelni",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Divider
                    Divider(
                        modifier = Modifier.width(80.dp).height(4.dp),
                        color = Blue
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    // Stats
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem(icon = "📝", value = "10", label = "pytań")
                        StatItem(icon = "🎯", value = "A-D", label = "wybór")
                        StatItem(icon = "⏱", value = "∞", label = "bez limitu")
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    // Category selection
                    Text(
                        text = "Tematy pytań:",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CategoryTextChip(text = "Studia")
                        CategoryTextChip(text = "Wiedza o uczelni")
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Start button
                    Button(
                        onClick = { onStartQuiz(null) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Blue)
                    ) {
                        Text(
                            text = "Rozpocznij Quiz 🚀",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Powodzenia w teście!",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun StatItem(icon: String, value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Blue
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun CategoryTextChip(text: String) {
    Box(
        modifier = Modifier
            .height(44.dp)
            .background(
                color = LightGray,
                shape = RoundedCornerShape(22.dp)
            )
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}
