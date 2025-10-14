package com.wsb.quizapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions ORDER BY RANDOM() LIMIT 10")
    suspend fun getRandomQuestions(): List<Question>

    @Query("SELECT * FROM questions WHERE category = :category ORDER BY RANDOM() LIMIT 10")
    suspend fun getQuestionsByCategory(category: String): List<Question>

    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions(): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<Question>)

    @Query("DELETE FROM questions")
    suspend fun deleteAll()
}
