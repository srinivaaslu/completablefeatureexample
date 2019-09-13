package com.ing.async.future.pojo;

import java.util.List;


public class FinalResponse {

    private List<String> allGods;

    private List<String> godsStartingWithXLetter;

    private int countOfGodsStartingWithLetter;

    public FinalResponse(List<String> allGods, List<String> godsStartingWithXLetter, int countOfGodsStartingWithLetter) {
        this.allGods = allGods;
        this.godsStartingWithXLetter = godsStartingWithXLetter;
        this.countOfGodsStartingWithLetter = countOfGodsStartingWithLetter;
    }

    public List<String> getAllGods() {
        return allGods;
    }

    public void setAllGods(List<String> allGods) {
        this.allGods = allGods;
    }

    public List<String> getGodsStartingWithXLetter() {
        return godsStartingWithXLetter;
    }

    public void setGodsStartingWithXLetter(List<String> godsStartingWithXLetter) {
        this.godsStartingWithXLetter = godsStartingWithXLetter;
    }

    public int getCountOfGodsStartingWithLetter() {
        return countOfGodsStartingWithLetter;
    }

    public void setCountOfGodsStartingWithLetter(int countOfGodsStartingWithLetter) {
        this.countOfGodsStartingWithLetter = countOfGodsStartingWithLetter;
    }
}
