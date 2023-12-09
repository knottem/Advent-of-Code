package year2019;

import template.Day;

public class Day04 extends Day {

    private final int input = Integer.parseInt(getInput().get(0).substring(0, 6));
    private final int input2 = Integer.parseInt(getInput().get(0).substring(getInput().get(0).length() - 6));

    public Day04() {
        super("input.txt", "04", "2019");
    }
    private boolean validPassword(int password){
        String passwordStr = String.valueOf(password);
        boolean valid = false;
        for (int i = 1; i < passwordStr.length(); i++) {
            if(passwordStr.charAt(i - 1) > passwordStr.charAt(i)){
                return false;
            }
            if(passwordStr.charAt(i - 1) == passwordStr.charAt(i)){
                valid = true;
            }
        }
        return valid;
    }

    private boolean validPassword2(int password) {
        boolean valid = false;
        int count = 1;
        String passwordStr = String.valueOf(password);
        for (int i = 1; i < passwordStr.length(); i++) {
            if (passwordStr.charAt(i - 1) > passwordStr.charAt(i)) {
                return false;
            }
            if (passwordStr.charAt(i - 1) == passwordStr.charAt(i)) {
                count++;
            } else {
                if (count == 2) {
                    valid = true;
                }
                count = 1;
            }
        }
        if (count == 2) {
            valid = true;
        }
        return valid;
    }

    @Override
    public long part1() {
        int count = 0;
        for (int i = input; i < input2; i++) {
            if(validPassword(i)){
                count++;
            }
        }
        return count;
    }

    @Override
    public long part2() {
        int count = 0;
        for (int i = input; i < input2; i++) {
            if(validPassword2(i)){
                count++;
            }
        }
        return count;
    }
}
