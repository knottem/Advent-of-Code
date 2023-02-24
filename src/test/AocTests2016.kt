import main.year2016.day01.Day01
import org.testng.Assert
import org.testng.annotations.Test


class AocTests2016 {

    @Test
    fun day01Tests(){
        val aocDay01 = Day01("test.txt")
        val aocDay01test2 = Day01("test2.txt")
        Assert.assertEquals(aocDay01.part1(), 12)
        Assert.assertEquals(aocDay01test2.part2(), 4)
    }


}