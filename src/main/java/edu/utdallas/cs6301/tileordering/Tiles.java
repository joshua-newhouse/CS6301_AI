package edu.utdallas.cs6301.tileordering;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tiles implements StateEnumerable<Tiles> {
    private final String tiles;

    public Tiles(String tiles) {
        this.tiles = tiles;
    }

    public List<Tiles> getNextStates() {
        int blankIdx = tiles.indexOf('x');

        List<Tiles> nextStates = new ArrayList<>();
        for(int i = 0; i < tiles.length(); i++) {
            if(i == blankIdx) {
                continue;
            }

            nextStates.add(new Tiles(swap(i, blankIdx)));
        }

        return nextStates;
    }

    private String swap(int idx1, int idx2) {
        char[] chars = tiles.toCharArray();

        chars[idx1] = tiles.charAt(idx2);
        chars[idx2] = tiles.charAt(idx1);

        return new String(chars);
    }

    @Override
    public String toString() {
        return tiles;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Tiles tiles1 = (Tiles) o;
        return tiles.equals(tiles1.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tiles);
    }

    public static Tiles getGoal(int length) {
        char[] tileRow = new char[length];

        for(int bIdx = 0, wIdx = length - 1; bIdx < wIdx; bIdx++, wIdx--) {
            tileRow[bIdx] = 'B';
            tileRow[wIdx] = 'W';
        }

        tileRow[length / 2] = 'x';

        return new Tiles(String.valueOf(tileRow));
    }
}
