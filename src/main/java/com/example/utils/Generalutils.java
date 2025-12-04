package com.example.utils;

import java.util.ArrayList;
import java.util.List;
// Put shit here that can be useful for other problems
public class Generalutils {

    private Generalutils() {}

    public static List<List<Integer>> convertToListListInteger(List<String> input){
        List<List<Integer>> convertedList = new ArrayList<>();
        for (String line : input) {
            convertedList.add(line.chars().mapToObj(Character::getNumericValue).toList());
        }
        return convertedList;
    }


}
