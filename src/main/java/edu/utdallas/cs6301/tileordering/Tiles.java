package edu.utdallas.cs6301.tileordering;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tiles implements StateEnumerable<Tiles> {
    private final String tiles;
    private final String operation;

    public Tiles(String tiles, int indexMoved) {
        this.tiles = tiles;
        this.operation = "move " + indexMoved;
    }

    public Tiles(String tiles) {
        this.tiles = tiles;
        this.operation = "";
    }

    public List<Tiles> getNextStates() {
        int blankIdx = tiles.indexOf('x');

        List<Tiles> nextStates = new ArrayList<>();
        for(int i = 0; i < tiles.length(); i++) {
            if(i == blankIdx) {
                continue;
            }

            nextStates.add(new Tiles(swap(i, blankIdx), i));
        }

        return nextStates;
    }

    private String swap(int idx1, int idx2) {
        char[] chars = tiles.toCharArray();

        chars[idx1] = tiles.charAt(idx2);
        chars[idx2] = tiles.charAt(idx1);

        return new String(chars);
    }

    public String getTiles() {
        return tiles;
    }

    @Override
    public int getCost(Tiles parentState) {
        return 1;
    }

    @Override
    public String toString() {
        return operation + " " + tiles;
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
        return tiles.equals(tiles1.getTiles());
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
