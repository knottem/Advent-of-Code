import day01.Day01
import day02.Day02
import day03.Day03
import day04.Day04
import day10.Day10
import org.testng.Assert
import org.testng.annotations.Test

class AoCTests {

    @Test
    fun day01Tests(){
        // test file
        val aocDay01 = Day01("test.txt")
        Assert.assertEquals(aocDay01.part1(), 30)
        Assert.assertEquals(aocDay01.part2(), 50)
    }

    @Test
    fun day02Tests(){
        // test file
        val aocDay02 = Day02("test.txt")
        Assert.assertEquals(aocDay02.part1(), 15)
        Assert.assertEquals(aocDay02.part2(), 12)
    }

    @Test
    fun day03Tests(){
        // test file
        val aocDay03 = Day03("test.txt")
        Assert.assertEquals(aocDay03.part1(), 157)
        Assert.assertEquals(aocDay03.part2(), 70)
    }

    @Test
    fun day04Tests(){
        // test file
        val aocDay04 = Day04("test.txt")
        Assert.assertEquals(aocDay04.part1(), 2)
        //Assert.assertEquals(aocDay04.part2(), 0)
    }

    @Test
    fun day10Tests(){
        // test file
        val aocDay10 = Day10("test.txt")
        val string : String =
                    "##..##..##..##..##..##..##..##..##..##..\n" +
                    "###...###...###...###...###...###...###.\n" +
                    "####....####....####....####....####....\n" +
                    "#####.....#####.....#####.....#####.....\n" +
                    "######......######......######......####\n" +
                    "#######.......#######.......#######....."
        Assert.assertEquals(aocDay10.part1(), 13140)
        Assert.assertEquals(aocDay10.part2(), string)
    }

}