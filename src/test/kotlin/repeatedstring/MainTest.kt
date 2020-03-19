package repeatedstring

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class MainTest : WordSpec() {
    init {
        "repeatedString" should {
            "work properly" {
                val n = 11L
                val s = "abcac"

                repeatedString(s, n) shouldBe 5
            }
        }
    }
}