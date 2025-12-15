# WSB Quiz App - Aplikacja Quiz o Uczelni WSB Merito

Aplikacja mobilna Android do testowania wiedzy o Uczelni WSB Merito, stworzona w oparciu o Jetpack Compose.

## Funkcjonalności

- Quiz z 10 losowymi pytaniami o WSB Merito
- 20 pytań w bazie danych (10 o uczelni, 10 o studiach)
- Pytania wielokrotnego wyboru (A, B, C, D)
- Wizualna informacja zwrotna (zielony - poprawna, czerwony - błędna)
- Ekran wyników z procentowym wynikiem
- Możliwość powtórzenia quizu
- Design zgodny z wytycznymi Figma

## Technologie

- **Kotlin** - język programowania
- **Jetpack Compose** - nowoczesny UI framework
- **Room Database** - lokalna baza danych SQLite
- **ViewModel** - zarządzanie stanem aplikacji
- **Material Design 3** - komponenty UI

## Struktura Projektu

```
app/src/main/java/com/wsb/quizapp/
├── data/
│   ├── Question.kt          # Model danych pytania
│   ├── QuestionDao.kt       # Data Access Object
│   └── QuizDatabase.kt      # Konfiguracja bazy danych + dane testowe
├── viewmodel/
│   └── QuizViewModel.kt     # Logika biznesowa quizu
├── ui/
│   ├── screens/
│   │   ├── HomeScreen.kt    # Ekran startowy
│   │   ├── QuizScreen.kt    # Ekran pytań
│   │   └── ResultScreen.kt  # Ekran wyników
│   └── theme/
│       ├── Color.kt         # Paleta kolorów
│       ├── Theme.kt         # Motyw aplikacji
│       └── Type.kt          # Typografia
└── MainActivity.kt          # Główna aktywność
```

## Wymagania

- Android Studio Hedgehog (2023.1.1) lub nowszy
- Android SDK 24+ (Android 7.0+)
- Kotlin 1.9.20+
- Gradle 8.2+

## Instalacja i Uruchomienie

1. Otwórz Android Studio
2. Wybierz "Open" i wskaż folder `WSBQuizApp`
3. Poczekaj na synchronizację Gradle
4. Podłącz urządzenie Android lub uruchom emulator
5. Kliknij "Run" (zielony trójkąt) lub Shift + F10

## Struktura Bazy Danych

### Tabela: questions

| Kolumna | Typ | Opis |
|---------|-----|------|
| id | Integer | Klucz główny (auto-increment) |
| questionText | String | Treść pytania |
| optionA | String | Opcja odpowiedzi A |
| optionB | String | Opcja odpowiedzi B |
| optionC | String | Opcja odpowiedzi C |
| optionD | String | Opcja odpowiedzi D |
| correctAnswer | String | Poprawna odpowiedź (A/B/C/D) |
| category | String | Kategoria ("Studia" lub "Wiedza o uczelni") |

## Przykładowe Pytania

Aplikacja zawiera 20 pytań podzielonych na dwie kategorie:

### Wiedza o uczelni (10 pytań)
- Historia WSB
- Struktura uczelni
- Oferta edukacyjna
- Współpraca międzynarodowa

### Studia (10 pytań)
- System ECTS
- Struktura studiów
- Prawa i obowiązki studenta
- Organizacja roku akademickiego

## Dodawanie Nowych Pytań

Aby dodać nowe pytania, edytuj plik `QuizDatabase.kt` w metodzie `populateDatabase`:

```kotlin
Question(
    questionText = "Twoje pytanie?",
    optionA = "Odpowiedź A",
    optionB = "Odpowiedź B",
    optionC = "Odpowiedź C",
    optionD = "Odpowiedź D",
    correctAnswer = "A", // lub B, C, D
    category = "Wiedza o uczelni" // lub "Studia"
)
```

## Personalizacja

### Zmiana kolorów
Edytuj plik `app/src/main/java/com/wsb/quizapp/ui/theme/Color.kt`

### Dodanie logo
Umieść plik logo w `app/src/main/res/drawable/` i zaktualizuj komponenty UI

## Licencja

Projekt edukacyjny dla Uczelni WSB Merito.

## Kontakt

W razie pytań lub problemów, skontaktuj się z zespołem deweloperskim.
