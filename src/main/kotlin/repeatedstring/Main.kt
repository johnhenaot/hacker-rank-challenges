package repeatedstring

import java.util.*

fun main() {
    val scan = Scanner(System.`in`)

    val numberOfAs = repeatedString(scan.nextLine(), scan.nextLine().trim().toLong())

    println(numberOfAs)
}

fun repeatedString(pattern: String, numberOfCharactersToConsider: Long): Long =
    pattern.getLastRepetitionSize(numberOfCharactersToConsider)
        .let { lastRepetitionSize ->
            if (lastRepetitionSize.isTheFullPatternSize(pattern))
                numberOfCharactersToConsider.getNumberOfAsInFullRepetitions(pattern)
            else
                numberOfCharactersToConsider
                    .getNumberOfAsInFullRepetitions(pattern) +
                        pattern.getPatternSubStringInLastRepetition(lastRepetitionSize).getNumberOfAs()
        }

private fun String.getPatternSubStringInLastRepetition(lastRepetitionSize: Long) =
    substring(0, lastRepetitionSize.toInt())

private fun Long.getNumberOfAsInFullRepetitions(pattern: String) =
    getNumberOfFullRepetitions(pattern) * pattern.getNumberOfAs()

private fun Long.getNumberOfFullRepetitions(pattern: String) =
    (this / pattern.length)

private fun String.getNumberOfAs() = toCharArray().filter { it == "a".single() }.size

private fun Long.isTheFullPatternSize(pattern: String) =
    this == pattern.length.toLong()

private fun String.getLastRepetitionSize(numberOfCharactersToConsider: Long) =
    numberOfCharactersToConsider % length