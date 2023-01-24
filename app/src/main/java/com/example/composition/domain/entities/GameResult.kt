package com.example.composition.domain.entities

data class GameResult(
    val isWinner: Boolean,
    val numberOfRightAnswers: Int,
    val numberOfQuestions: Int,
    val gameSettings: GameSettings
)
