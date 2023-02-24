import main.year2016.day01.Day01
import org.testng.Assert.*
import org.testng.annotations.Test
import year2016.day02.Day02


class AocTests2016 {

    @Test
    fun day01Tests(){
        val aocDay01 = Day01("test.txt")
        val aocDay01test2 = Day01("test2.txt")
        assertEquals(aocDay01.part1(), 12)
        assertEquals(aocDay01test2.part2(), 4)
    }

    @Test
    fun day02Tests(){
        val aocDay02 = Day02("test.txt")
        assertEquals(aocDay02.part1(), "1985")
        assertEquals(aocDay02.part2(), "5DB3")
    }

}