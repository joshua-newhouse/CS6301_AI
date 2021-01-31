package edu.utdallas.cs6301.tileordering.tiles;

import edu.utdallas.cs6301.tileordering.StateEnumerable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tiles implements StateEnumerable<Tiles> {
    protected static final char BLANK_MARKER = 'x';

    private final String tiles;
    private final String operation;

    private static Tiles GOAL_STATE = null;

    public Tiles(String tiles, int indexMoved) {
        this.tiles = tiles;
        this.operation = "move " + indexMoved;
    }

    public Tiles(String tiles) {
        this.tiles = tiles;
        this.operation = "";
    }

    public List<Tiles> getNextStates() {
        int blankIdx = tiles.indexOf(BLANK_MARKER);

        List<Tiles> nextStates = new ArrayList<>();
        for(int i = 0; i < tiles.length(); i++) {
            if(i == blankIdx) {
                continue;
            }

            nextStates.add(getNewTiles(swap(i, blankIdx), i));
        }

        return nextStates;
    }

    protected Tiles getNewTiles(String state) {
        return new Tiles(state);
    }

    protected Tiles getNewTiles(String state, int move) {
        return new Tiles(state, move);
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
    public int getCost(Tiles previousState) {
        return 1;
    }

    @Override
    public int getDistanceFromGoal() {
        char[] goal = GOAL_STATE.getTiles().toCharArray();
        char[] thisTiles = getTiles().toCharArray();

        int distance = 0;
        for(int i = 0; i < goal.length; i++) {
            if(goal[i] != thisTiles[i]) {
                distance++;
            }
        }

        return distance;
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

    public static Tiles getGoal(Tiles initialState) {
        int length = initialState.getTiles().length();

        if(GOAL_STATE == null) {
            char[] tileRow = new char[length];

            for(int bIdx = 0, wIdx = length - 1; bIdx < wIdx; bIdx++, wIdx--) {
                tileRow[bIdx] = 'B';
                tileRow[wIdx] = 'W';
            }

            tileRow[length / 2] = BLANK_MARKER;

            GOAL_STATE = initialState.getNewTiles(String.valueOf(tileRow));
        }

        return GOAL_STATE;
    }
}
