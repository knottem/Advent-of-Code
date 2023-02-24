import org.testng.annotations.Test;
import year2015.day01.Day01;
import year2015.day02.Day02;
import year2015.day03.Day03;
import year2015.day05.Day05;

import static org.testng.Assert.assertEquals;

public class AocTests2015 {

    @Test
    final void day01Tests(){
        Day01 aocDay01 = new Day01("test.txt");
        assertEquals(aocDay01.part1(), -1);
        assertEquals(aocDay01.part2(), 5);
    }


    @Test
    final void day02Tests(){
        Day02 aocDay02 = new Day02("test.txt");
        assertEquals(aocDay02.part1(), 101);
        assertEquals(aocDay02.part2(), 48);
    }

    @Test
    final void day03Tests(){
        Day03 aocDay03 = new Day03("test.txt");
        assertEquals(aocDay03.part1(), 2);
        assertEquals(aocDay03.part2(), 11);
    }

    @Test
    final void day05Tests() {
        Day05 aocDay05 = new Day05("test.txt");
        assertEquals(aocDay05.part1(), 2);
        assertEquals(aocDay05.part2(), 1);
    }

}