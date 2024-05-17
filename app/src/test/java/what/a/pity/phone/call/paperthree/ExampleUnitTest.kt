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
       println(getClassNameList())
        println(getClassNameList2())

    }
    fun getClassNameList(): List<String> {
        val list = mutableListOf("Nature", "Beautiful", "Art", "Animals", "City", "Cool")
        var checkTheType: String = "Nature"
        if (list.contains(checkTheType)) {
            list.remove(checkTheType)
            list.add(0, checkTheType)
        }
        return list
    }

    fun getClassNameList2(): List<String> {
        val list = mutableListOf("Nature", "Beautiful", "Art", "Animals", "City", "Cool")
        var checkTheType: String = "Animals"
        if (list.contains(checkTheType)) {
            list.remove(checkTheType)
            list.add(0, checkTheType)
        }
        return list
    }

}