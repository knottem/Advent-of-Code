import org.testng.Assert
import org.testng.annotations.Test
import year2019.day04.Day04

class AocTests2019 {

    @Test
    fun day04Tests(){
        // test file
        val aocDay04 = Day04("test.txt")
        Assert.assertEquals(aocDay04.part1(), 1)
        Assert.assertEquals(aocDay04.part2(), 0)
    }
}