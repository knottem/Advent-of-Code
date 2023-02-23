import main.year2020.day04.Day04
import org.testng.Assert
import org.testng.annotations.Test

class AocTests2020 {

    @Test
    fun day04Tests(){
        val aocDay04 = Day04("test.txt")
        Assert.assertEquals(aocDay04.part1(), 4)
        Assert.assertEquals(aocDay04.part2(), 3)
    }
}