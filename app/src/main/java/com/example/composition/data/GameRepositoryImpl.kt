package com.example.composition.data

import com.example.composition.domain.entities.GameSettings
import com.example.composition.domain.entities.Level
import com.example.composition.domain.entities.Question
import com.example.composition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.random.Random

object GameRepositoryImpl: GameRepository {

    private const val MIN_SUM_AMOUNT = 2
    private const val MIN_ANSWER_VALUE = 1
    private const val RANDOM_ANSWER_MULTIPLIER = 2

    override fun generateQuestion(maxSumValue: Int, numberOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_AMOUNT, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val rightAnswer = sum - visibleNumber
        val options = HashSet<Int>()
        options.add(rightAnswer)
        while (options.size < numberOfOptions) {
            val from = max(MIN_ANSWER_VALUE, sum - numberOfOptions * RANDOM_ANSWER_MULTIPLIER)
            val to = sum + numberOfOptions * RANDOM_ANSWER_MULTIPLIER
            val wrongAnswer = Random.nextInt(from, to)
            options.add(wrongAnswer)
        }
        return Question(
            sum = sum,
            visibleNumber = visibleNumber,
            options = options.toList()
        )
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level) {
            Level.TEST -> GameSettings(
                maxSumValue = 10,
                minCountOfRightAnswers = 3,
                minPercentOfRightAnswers = 50,
                gameTimeInSeconds = 8
            )
            Level.EASY -> GameSettings(
                maxSumValue = 10,
                minCountOfRightAnswers = 10,
                minPercentOfRightAnswers = 70,
                gameTimeInSeconds = 40
            )
            Level.MEDIUM -> GameSettings(
                maxSumValue = 40,
                minCountOfRightAnswers = 20,
                minPercentOfRightAnswers = 80,
                gameTimeInSeconds = 40
            )
            Level.HARD -> GameSettings(
                maxSumValue = 100,
                minCountOfRightAnswers = 25,
                minPercentOfRightAnswers = 80,
                gameTimeInSeconds = 40
            )
        }
    }
}