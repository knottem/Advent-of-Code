
import year2023.Day10;

public class Year2023Tests {

    public void testDay10_part1() {
        Day10 day10 = new Day10("example1.txt");
        assert day10.part1() == 4;

        Day10 day10_2 = new Day10("example2.txt");
        assert day10_2.part1() == 8;

    }
}
