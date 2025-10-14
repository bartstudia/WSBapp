package com.wsb.quizapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wsb.quizapp.data.Question
import com.wsb.quizapp.data.QuestionDao
import kotlinx.coroutines.launch

data class QuizState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: String? = null,
    val isAnswerSubmitted: Boolean = false,
    val correctAnswersCount: Int = 0,
    val isQuizFinished: Boolean = false
)

class QuizViewModel(private val questionDao: QuestionDao) : ViewModel() {
    var state by mutableStateOf(QuizState())
        private set

    fun loadQuestions(category: String? = null) {
        viewModelScope.launch {
            val questions = if (category != null) {
                questionDao.getQuestionsByCategory(category)
            } else {
                questionDao.getRandomQuestions()
            }
            state = QuizState(questions = questions)
        }
    }

    fun selectAnswer(answer: String) {
        if (!state.isAnswerSubmitted) {
            state = state.copy(selectedAnswer = answer)
        }
    }

    fun submitAnswer() {
        if (state.selectedAnswer != null && !state.isAnswerSubmitted) {
            val currentQuestion = state.questions[state.currentQuestionIndex]
            val isCorrect = state.selectedAnswer == currentQuestion.correctAnswer

            state = state.copy(
                isAnswerSubmitted = true,
                correctAnswersCount = if (isCorrect) state.correctAnswersCount + 1 else state.correctAnswersCount
            )
        }
    }

    fun nextQuestion() {
        if (state.currentQuestionIndex < state.questions.size - 1) {
            state = state.copy(
                currentQuestionIndex = state.currentQuestionIndex + 1,
                selectedAnswer = null,
                isAnswerSubmitted = false
            )
        } else {
            state = state.copy(isQuizFinished = true)
        }
    }

    fun restartQuiz() {
        state = QuizState()
    }

    val currentQuestion: Question?
        get() = state.questions.getOrNull(state.currentQuestionIndex)

    val progress: Float
        get() = if (state.questions.isNotEmpty()) {
            (state.currentQuestionIndex + 1).toFloat() / state.questions.size
        } else 0f

    val progressText: String
        get() = "${state.currentQuestionIndex + 1}/${state.questions.size}"
}
