package edu.utdallas.cs6301.tileordering.tiles;

public class MovementCostTiles extends Tiles {
    private String cost = "";

    public MovementCostTiles(String tiles, int indexMoved) {
        super(tiles, indexMoved);
    }

    public MovementCostTiles(String tiles) {
        super(tiles);
    }

    @Override
    public int getCost(Tiles previousState) {
        int xIdxPrevious = previousState.getTiles().indexOf(BLANK_TILE);
        int xIdxThis = this.getTiles().indexOf(BLANK_TILE);

        int cost = Math.abs(xIdxThis - xIdxPrevious);
        this.cost = String.format("(c=%d)", cost);

        return cost;
    }

    /**
     *   This is a better heuristic function but doesn't conform to the requirements
     **/
//    @Override
//    public int getDistanceFromGoal() {
//        int bGoalIdx = getTiles().length() / 2 - 1;
//        int wGoalIdx = getTiles().length() / 2 + 1;
//
//        char[] thisTiles = getTiles().toCharArray();
//
//        int distance = 0;
//        for(int i = 0; i < thisTiles.length; i++) {
//            if(i > bGoalIdx && thisTiles[i] == BLACK_TILE) {
//                distance += i - bGoalIdx;
//            } else if(i < wGoalIdx && thisTiles[i] == WHITE_TILE) {
//                distance += wGoalIdx - i;
//            }
//        }
//
//        return distance;
//    }

    @Override
    protected Tiles getNewTiles(String state) {
        return new MovementCostTiles(state);
    }

    @Override
    protected Tiles getNewTiles(String state, int move) {
        return new MovementCostTiles(state, move);
    }

    @Override
    public String toString() {
        return (super.toString() + " " + cost).trim();
    }
}
