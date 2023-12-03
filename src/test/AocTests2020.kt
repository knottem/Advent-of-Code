import main.year2020.Day01
import main.year2020.Day04
import org.testng.Assert
import org.testng.annotations.Test

class AocTests2020 {

    @Test
    fun day01Tests(){
        val aocDay01 = Day01("test.txt")
        Assert.assertEquals(aocDay01.part1(), 514579)
        Assert.assertEquals(aocDay01.part1V2(), 514579)
        Assert.assertEquals(aocDay01.part2(), 241861950)
    }

    @Test
    fun day04Tests(){
        val aocDay04 = Day04("test.txt")
        Assert.assertEquals(aocDay04.part1(), 4)
        Assert.assertEquals(aocDay04.part2(), 3)
    }
}