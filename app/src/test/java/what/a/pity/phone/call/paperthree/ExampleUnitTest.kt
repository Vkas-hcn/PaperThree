package what.a.pity.phone.call.paperthree

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        mainYesterday()
    }
    fun mainYesterday() {
        val inputString = "fb4a&facebook"
        val stringArray = inputString.split("&").toTypedArray()
        stringArray.forEach { println(it) }
    }

}