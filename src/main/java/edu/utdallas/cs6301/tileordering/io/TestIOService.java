package edu.utdallas.cs6301.tileordering.io;

public class TestIOService implements IOService {
    private final String testInput;

    private int stepCounter;

    public TestIOService(String input) {
        testInput = input;
    }

    @Override
    public String getInputString() {
        return testInput;
    }

    @Override
    public void write(String s) {
        System.out.printf("Step %d: %s%n", stepCounter++, s);
    }
}
