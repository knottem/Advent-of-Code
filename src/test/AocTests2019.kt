import main.year2019.day01.Day01
import main.year2019.day02.Day02
import main.year2019.day03.Day03
import org.testng.Assert.*
import org.testng.annotations.Test
import year2019.day04.Day04

class AocTests2019 {

    @Test
    fun day01Tests(){
        val aocDay01 = Day01("test.txt")
        assertEquals(aocDay01.part1(), 33583)
        assertEquals(aocDay01.part2(), 50346)
    }

    @Test
    fun day02Tests(){
        val aocDay02 = Day02("test.txt")
        assertEquals(aocDay02.part1(), 30)
    }

    @Test
    fun day03Tests(){
        val aocDay03 = Day03("test.txt")
        assertEquals(aocDay03.part1(), 159)
        assertEquals(aocDay03.part2(), 610)
    }

    @Test
    fun day04Tests(){
        val aocDay04 = Day04("test.txt")
        assertEquals(aocDay04.part1(), 1)
        assertEquals(aocDay04.part2(), 0)
    }
}