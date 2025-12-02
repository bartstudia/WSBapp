package com.wsb.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wsb.quizapp.data.QuizDatabase
import com.wsb.quizapp.ui.screens.HomeScreen
import com.wsb.quizapp.ui.screens.QuizScreen
import com.wsb.quizapp.ui.screens.ResultScreen
import com.wsb.quizapp.ui.theme.WSBQuizAppTheme
import com.wsb.quizapp.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = QuizDatabase.getDatabase(applicationContext)
        val questionDao = database.questionDao()

        setContent {
            WSBQuizAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizApp(questionDao = questionDao)
                }
            }
        }
    }
}

@Composable
fun QuizApp(questionDao: com.wsb.quizapp.data.QuestionDao) {
    val viewModel: QuizViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return QuizViewModel(questionDao) as T
            }
        }
    )

    val state = viewModel.state

    when {
        state.questions.isEmpty() && !state.isQuizFinished -> {
            HomeScreen(
                onStartQuiz = { category ->
                    viewModel.loadQuestions(category)
                }
            )
        }
        state.isQuizFinished -> {
            ResultScreen(
                correctAnswers = state.correctAnswersCount,
                totalQuestions = state.questions.size,
                onRestartQuiz = {
                    viewModel.restartQuiz()
                }
            )
        }
        else -> {
            QuizScreen(
                question = viewModel.currentQuestion,
                selectedAnswer = state.selectedAnswer,
                isAnswerSubmitted = state.isAnswerSubmitted,
                progress = viewModel.progress,
                progressText = viewModel.progressText,
                onAnswerSelected = { answer ->
                    viewModel.selectAnswer(answer)
                },
                onNextQuestion = {
                    viewModel.nextQuestion()
                }
            )
        }
    }
}
