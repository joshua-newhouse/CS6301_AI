package edu.utdallas.cs6301.tileordering;

import java.util.List;

public interface StateEnumerable<T> {
    List<T> getNextStates();

    int getCost(T parentState);
}
