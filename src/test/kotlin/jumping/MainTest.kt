package jumping

import io.kotlintest.Spec
import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import io.mockk.*

class MainTest : WordSpec() {

    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        mockkStatic("jumping.MainKt")
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        unmockkStatic("jumping.MainKt")
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        super.afterTest(testCase, result)
        clearAllMocks()
    }

    init {

        "jumpingOnClouds" should {
            "jumpingOnClouds: should solve trivial cases" {
                val trivialCase = arrayOf(0, 0)
                every { trivialCase.isATrivialCaseArray() } returns true
                every { trivialCase.solveTrivialCase() } returns 1

                jumpingOnClouds(trivialCase)

                verifySequence {
                    jumpingOnClouds(trivialCase)
                    trivialCase.isATrivialCaseArray()
                    trivialCase.solveTrivialCase()
                }

            }

        }

        "isATrivialCaseListSize" should {
            "isATrivialCaseListSize: should return true for size 2" {
                val receivedArray = arrayOf(0, 0)

                receivedArray.isATrivialCaseArray() shouldBe true
            }

            "isATrivialCaseListSize: should return true for size 3" {
                val receivedArray = arrayOf(0, 1, 0)

                receivedArray.isATrivialCaseArray() shouldBe true
            }

            "isATrivialCaseListSize: should return true for size 4" {
                val receivedArray = arrayOf(0, 1, 0, 0)

                receivedArray.isATrivialCaseArray() shouldBe true
            }
        }

        "solveTrivialCase" should {
            "solveTrivialCase: should return 1 for size 2" {
                val receivedArray = arrayOf(0, 0)

                receivedArray.solveTrivialCase() shouldBe 1
            }

            "solveTrivialCase: should return 1 for size 3" {
                val receivedArray = arrayOf(0, 1, 0)

                receivedArray.solveTrivialCase() shouldBe 1
            }

            "solveTrivialCase: should return 2 for size 4" {
                val receivedArray = arrayOf(0, 1, 0, 0)

                receivedArray.solveTrivialCase() shouldBe 2
            }
        }

        "solveNonTrivialCase" should {
            "solveNonTrivialCase: should return 1 for size 2" {
                val receivedArray = arrayOf(0, 1, 0, 0)
                val testList = listOf(0, 1, 3, 4, 6)
                every { receivedArray.getListOfPositionsOfAllowedClouds() } returns testList
                every { testList.getMinimumLeapAmount() } returns 1

                receivedArray.solveNonTrivialCase()

                verify {
                    receivedArray.getListOfPositionsOfAllowedClouds()
                    testList.getMinimumLeapAmount()
                }
            }
        }

        "getListOfPositionsOfAllowedClouds" should {
            "getListOfPositionsOfAllowedClouds: should work properly" {
                val receivedArray = arrayOf(0, 0, 1, 0, 0, 1, 0)

                receivedArray.getListOfPositionsOfAllowedClouds() shouldBe listOf(0, 1, 3, 4, 6)
            }
        }

        "getMinimumLeapAmount" should {
            "getMinimumLeapAmount: should work properly" {
                val receivedList = listOf(0, 1, 3, 4, 6)

                receivedList.getMinimumLeapAmount() shouldBe 4
            }
        }
    }
}
