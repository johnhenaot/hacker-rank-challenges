package jumpingontheclouds

import java.util.*

val INITIAL_PATH_SKIPPING_SECOND_CLOUD = listOf(0, 0, 1)
val INITIAL_PATH_TO_SECOND_CLOUD = listOf(0, 1)

fun main() {
    val scan = Scanner(System.`in`)

    val c = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = jumpingOnClouds(c)

    println(result)
}

fun jumpingOnClouds(c: Array<Int>): Int =
    if (c.isATrivialCaseArray())
        c.solveTrivialCase()
    else
        c.solveNonTrivialCase()

fun Array<Int>.isATrivialCaseArray(): Boolean =
    size <= 4

fun Array<Int>.solveTrivialCase(): Int =
    when (size) {
        2 -> 1
        3 -> 1
        else -> 2
    }

fun Array<Int>.solveNonTrivialCase(): Int =
    getListOfPositionsOfAllowedClouds()
        .getMinimumLeapAmount()

fun Array<Int>.getListOfPositionsOfAllowedClouds(): List<Int> =
    indices.filter {
        elementAt(it) == 0
    }

fun List<Int>.getMinimumLeapAmount(): Int =
    findTheOptimalPath().sum()

fun List<Int>.findTheOptimalPath(): List<Int> =
    if (canSkipSecondCloud())
        moveToNextOptimalCloud(INITIAL_PATH_SKIPPING_SECOND_CLOUD)
    else
        moveToNextOptimalCloud(INITIAL_PATH_TO_SECOND_CLOUD)

fun List<Int>.canSkipSecondCloud() = elementAt(2) == 2

fun List<Int>.moveToNextOptimalCloud(currentPath: List<Int>): List<Int> =
    when (getMissingClouds(currentPath)) {
        0 -> currentPath
        1 -> currentPath.goToNextCloud()
        else -> moveToNextOptimalCloud(findNextOptimalCloud(currentPath))
    }

fun List<Int>.findNextOptimalCloud(currentPath: List<Int>): List<Int> {
    return if (canSkipNextCloud(currentPath))
        currentPath.skipNextCloud()
    else currentPath.goToNextCloud()
}

fun List<Int>.canSkipNextCloud(currentPath: List<Int>) =
    elementAt(currentPath.lastIndex + 2) - elementAt(currentPath.lastIndex) == 2

fun List<Int>.skipNextCloud() = plus(listOf(0, 1))

fun List<Int>.goToNextCloud() = plus(1)

fun List<Int>.getMissingClouds(currentPath: List<Int>) =
    size - currentPath.size