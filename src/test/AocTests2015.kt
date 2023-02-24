
import org.testng.Assert
import org.testng.annotations.Test
import year2015.day01.Day01

class AocTests2015 {

    @Test
    fun day01Tests(){
        val aocDay01 = Day01("test.txt")
        Assert.assertEquals(aocDay01.part1(), 2)
        Assert.assertEquals(aocDay01.part2(), 1)
    }

}