import org.testng.annotations.Test;
import year2015.day01.Day01;
import year2015.day02.Day02;

import static org.testng.Assert.assertEquals;

public class AocTests2015 {

    @Test
    final void day01Tests() {
        Day01 aocDay01 = new Day01("test.txt");
        assertEquals(aocDay01.part1(), 2);
        assertEquals(aocDay01.part2(), 1);
    }

    @Test
    final void day02Tests(){
        Day02 aocDay02 = new Day02("test.txt");
        assertEquals(aocDay02.part1(), 101);
        assertEquals(aocDay02.part2(), 48);
    }

}