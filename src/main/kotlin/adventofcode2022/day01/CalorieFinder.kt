package adventofcode2022.day01

import java.io.File

class Elf(
  val foodCalories: List<Int>,
  val totalCalories: Int
)

object CalorieFinder {
  fun parseInput(rawInput: File): List<Elf> {
    val foodOnElves = mutableListOf<List<Int>>()

    rawInput.readLines().foldRight(listOf<Int>()) { line, currentFood ->
      if(line.isEmpty()) {
        foodOnElves.add(currentFood)
        listOf()
      } else {
        currentFood + line.toInt()
      }
    }

    return foodOnElves.map { Elf(it, it.sum()) }
  }

  fun findMostCalories(elves: List<Elf>): Int {
    return elves.maxBy { it.totalCalories }.totalCalories
  }

  fun findTotalCaloriesOfTopN(elves: List<Elf>, top: Int = 3): Int {
    return elves.sortedByDescending { it.totalCalories }
      .subList(0, top)
      .sumOf { it.totalCalories }
  }

  fun solve(pathToFile: String): Unit {
    val file = File(pathToFile)
    val elves = parseInput(file)

    val mostCalories = findMostCalories(elves)
    val sumOfTop3 = findTotalCaloriesOfTopN(elves)
    print("Most calories on single elf: $mostCalories")
    print("Sum of calories of top 3 elves: $sumOfTop3")
  }
}