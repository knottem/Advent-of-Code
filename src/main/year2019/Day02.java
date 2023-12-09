package year2019;

import template.Day;

public class Day02 extends Day {

    private final int[] input;

    public Day02()  {
        super("input.txt", "02", "2019");
        this.input = getInputAsIntArray();
    }

    private int[] getInputAsIntArray()  {
        String fileContent = getInput().get(0);
        String[] values = fileContent.split(",");
        int[] intArray = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            intArray[i] = Integer.parseInt(values[i]);
        }
        return intArray;
    }

    private int calculate(int[] input) {
        for (int i = 0; i < input.length; i += 4) {
            if (input[i] == 99) {
                break;
            }
            int a = input[input[i + 1]];
            int b = input[input[i + 2]];
            int c = input[i + 3];
            input[c] = (input[i] == 1) ? a + b : a * b;
        }
        return input[0];
    }

    @Override
    public long part1() {
        int[] inputCopy = input.clone();
        if (inputCopy.length > 12) {
            inputCopy[1] = 12;
            inputCopy[2] = 2;
        }
        return calculate(inputCopy);
    }

    @Override
    public long part2() {
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                int[] inputCopy = input.clone();
                inputCopy[1] = i;
                inputCopy[2] = j;
                if (calculate(inputCopy) == 19690720) {
                    return 100 * i + j;
                }
            }
        }
        return 0;
    }

}
