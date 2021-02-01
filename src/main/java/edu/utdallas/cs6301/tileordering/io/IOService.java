/*
 * Joshua Newhouse
 * Vamsi Somepalli
 */

package edu.utdallas.cs6301.tileordering.io;

public interface IOService {
    default String getInputString() {
        return "";
    }

    default void write(String s) {

    }

    default void writeItem(String s) {

    }
}
