package jumping

import java.util.*

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

//TODO Refactor to functional programming: recursion
fun List<Int>.getMinimumLeapAmount(): Int =
    let {
        var counter = 0
        var pos = 0
        while (pos <= it.size - 3) {
            pos += if (it.elementAt(pos + 2) - it.elementAt(pos) <= 2) 2 else 1
            counter += 1
        }
        if (pos + 1 == it.size)
            counter
        else
            counter + 1
    }