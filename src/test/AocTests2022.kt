import main.year2022.day05.Day05
import year2022.day01.Day01
import year2022.day02.Day02
import year2022.day03.Day03
import year2022.day04.Day04
import year2022.day10.Day10
import org.testng.Assert.*
import org.testng.annotations.Test
import year2022.day06.Day06

class AocTests2022 {

    @Test
    fun day01Tests(){
        val aocDay01 = Day01("test.txt")
        assertEquals(aocDay01.part1(), 30)
        assertEquals(aocDay01.part2(), 50)
    }

    @Test
    fun day02Tests(){
        val aocDay02 = Day02("test.txt")
        assertEquals(aocDay02.part1(), 15)
        assertEquals(aocDay02.part2(), 12)
    }

    @Test
    fun day03Tests(){
        val aocDay03 = Day03("test.txt")
        assertEquals(aocDay03.part1(), 157)
        assertEquals(aocDay03.part2(), 70)
    }

    @Test
    fun day04Tests(){
        val aocDay04 = Day04("test.txt")
        assertEquals(aocDay04.part1(), 2)
        assertEquals(aocDay04.part2(), 4)
    }

    @Test
    fun day05Tests(){
        val aocDay05 = Day05("test.txt")
        assertEquals(aocDay05.part1(), "CMZ")
        assertEquals(aocDay05.part2(), "MCD")
    }

    @Test
    fun day06Tests(){
        val aocDay06 = Day06("test.txt")
        assertEquals(aocDay06.part1(),5)
        assertEquals(aocDay06.part2(), 23)
    }

    @Test
    fun day10Tests(){
        val aocDay10 = Day10("test.txt")
        val string : String =
                    "##..##..##..##..##..##..##..##..##..##..\n" +
                    "###...###...###...###...###...###...###.\n" +
                    "####....####....####....####....####....\n" +
                    "#####.....#####.....#####.....#####.....\n" +
                    "######......######......######......####\n" +
                    "#######.......#######.......#######....."
        assertEquals(aocDay10.part1(), 13140)
        assertEquals(aocDay10.part2(), string)
    }

}