/*
 * Joshua Newhouse
 * Vamsi Somepalli
 */

package edu.utdallas.cs6301.tileordering.io;

public class TestIOService implements IOService {
    private final String testInput;

    private int stepCounter;

    public TestIOService(String input) {
        testInput = input.trim();
    }

    @Override
    public String getInputString() {
        return testInput;
    }

    @Override
    public void write(String s) {
        System.out.printf("Step %d: %s%n", stepCounter++, s.trim());
    }

    @Override
    public void writeItem(String s) {
        System.out.printf("%s%n", s.trim());
    }
}
