package com.wsb.quizapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.questionDao())
                }
            }
        }

        suspend fun populateDatabase(questionDao: QuestionDao) {
            questionDao.deleteAll()

            val questions = listOf(
                // Wiedza o uczelni
                Question(
                    questionText = "W którym roku została założona pierwsza uczelnia z grupy WSB?",
                    optionA = "1994",
                    optionB = "1998",
                    optionC = "2001",
                    optionD = "2005",
                    correctAnswer = "A",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "Jaka jest główna specjalizacja uczelni WSB Merito?",
                    optionA = "Nauki ścisłe",
                    optionB = "Biznes i zarządzanie",
                    optionC = "Nauki humanistyczne",
                    optionD = "Medycyna",
                    correctAnswer = "B",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "W ilu miastach w Polsce znajdują się kampusy WSB Merito?",
                    optionA = "3",
                    optionB = "5",
                    optionC = "8",
                    optionD = "12",
                    correctAnswer = "C",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "Jaki jest pełny skrót nazwy uczelni WSB?",
                    optionA = "Wyższa Szkoła Biznesu",
                    optionB = "Wielka Szkoła Biznesu",
                    optionC = "Warszawska Szkoła Biznesu",
                    optionD = "Wyższa Szkoła Bankowości",
                    correctAnswer = "A",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "Czy WSB Merito oferuje studia w języku angielskim?",
                    optionA = "Nie",
                    optionB = "Tak, tylko podyplomowe",
                    optionC = "Tak, na wszystkich poziomach",
                    optionD = "Tylko studia doktoranckie",
                    correctAnswer = "C",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "Co oznacza nazwa Merito w nazwie uczelni?",
                    optionA = "Zasługa, wartość",
                    optionB = "Wiedza",
                    optionC = "Przyszłość",
                    optionD = "Doskonałość",
                    correctAnswer = "A",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "Który z poniższych kierunków NIE jest oferowany na WSB Merito?",
                    optionA = "Zarządzanie",
                    optionB = "Informatyka",
                    optionC = "Medycyna",
                    optionD = "Finanse i rachunkowość",
                    correctAnswer = "C",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "Jaki system kształcenia dominuje w WSB Merito?",
                    optionA = "Tylko stacjonarne",
                    optionB = "Tylko niestacjonarne",
                    optionC = "Stacjonarne i niestacjonarne",
                    optionD = "Tylko online",
                    correctAnswer = "C",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "Czy WSB Merito współpracuje z zagranicznymi uczelniami?",
                    optionA = "Nie",
                    optionB = "Tak, w ramach programu Erasmus+",
                    optionC = "Tylko z uczelniami europejskimi",
                    optionD = "Tylko z uczelniami amerykańskimi",
                    correctAnswer = "B",
                    category = "Wiedza o uczelni"
                ),
                Question(
                    questionText = "Jaki jest główny priorytet WSB Merito w kształceniu studentów?",
                    optionA = "Teoria akademicka",
                    optionB = "Praktyczne umiejętności zawodowe",
                    optionC = "Badania naukowe",
                    optionD = "Sport",
                    correctAnswer = "B",
                    category = "Wiedza o uczelni"
                ),

                // Studia - ogólne pytania o studiowanie
                Question(
                    questionText = "Ile lat trwają studia licencjackie?",
                    optionA = "2 lata",
                    optionB = "3 lata",
                    optionC = "4 lata",
                    optionD = "5 lat",
                    correctAnswer = "B",
                    category = "Studia"
                ),
                Question(
                    questionText = "Jaki tytuł uzyskuje się po ukończeniu studiów magisterskich?",
                    optionA = "Licencjat",
                    optionB = "Inżynier",
                    optionC = "Magister",
                    optionD = "Doktor",
                    correctAnswer = "C",
                    category = "Studia"
                ),
                Question(
                    questionText = "Ile punktów ECTS trzeba zdobyć na studiach licencjackich?",
                    optionA = "120",
                    optionB = "180",
                    optionC = "210",
                    optionD = "240",
                    correctAnswer = "B",
                    category = "Studia"
                ),
                Question(
                    questionText = "Co to jest praca dyplomowa?",
                    optionA = "Egzamin końcowy",
                    optionB = "Pisemna praca naukowa",
                    optionC = "Prezentacja multimedialna",
                    optionD = "Test wiedzy",
                    correctAnswer = "B",
                    category = "Studia"
                ),
                Question(
                    questionText = "Jaki jest maksymalny czas na ukończenie studiów licencjackich?",
                    optionA = "3 lata",
                    optionB = "4 lata",
                    optionC = "6 lat",
                    optionD = "10 lat",
                    correctAnswer = "C",
                    category = "Studia"
                ),
                Question(
                    questionText = "Co oznacza skrót ECTS?",
                    optionA = "European Credit Transfer System",
                    optionB = "Education Credit Transfer System",
                    optionC = "European Course Transfer System",
                    optionD = "European Credit Teaching System",
                    correctAnswer = "A",
                    category = "Studia"
                ),
                Question(
                    questionText = "Ile sesji egzaminacyjnych jest w roku akademickim?",
                    optionA = "1",
                    optionB = "2",
                    optionC = "3",
                    optionD = "4",
                    correctAnswer = "B",
                    category = "Studia"
                ),
                Question(
                    questionText = "Co to jest syllabus?",
                    optionA = "Plan zajęć",
                    optionB = "Program przedmiotu",
                    optionC = "Lista studentów",
                    optionD = "Regulamin studiów",
                    correctAnswer = "B",
                    category = "Studia"
                ),
                Question(
                    questionText = "Jaka jest minimalna frekwencja wymagana na większości zajęć?",
                    optionA = "50%",
                    optionB = "60%",
                    optionC = "70%",
                    optionD = "80%",
                    correctAnswer = "D",
                    category = "Studia"
                ),
                Question(
                    questionText = "Co to jest dziekanat?",
                    optionA = "Biuro rektora",
                    optionB = "Biuro obsługi studenta",
                    optionC = "Biblioteka",
                    optionD = "Stołówka",
                    correctAnswer = "B",
                    category = "Studia"
                )
            )

            questionDao.insertAll(questions)
        }
    }
}
